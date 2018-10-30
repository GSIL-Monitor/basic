package com.wgb.controller.mb.wxms;

import com.alibaba.fastjson.JSON;
import com.sun.xml.bind.v2.TODO;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.pays.admin.ApiPayService;
import com.wgb.service.dubbo.wxms.web.ApitCsAuthorService;
import com.wgb.service.dubbo.wxms.web.ApitCsTheThirdPartService;
import com.wgb.service.dubbo.wxms.web.ApitXcxCodeModelService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.HttpClientUtil;
import com.wgb.weixin.mp.aes.AesException;
import com.wgb.weixin.mp.aes.WXBizMsgCrypt;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.MMSRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wgb on 2018/6/13 0013.
 */
@Controller
@RequestMapping("")
public class WxOpenPTController extends MBBaseController {
    @Autowired
    private ApitCsTheThirdPartService apitCsTheThirdPartService;

    @Autowired
    private ApitCsAuthorService apitCsAuthorService;
    @Autowired
    private ApitXcxCodeModelService apitXcxCodeModelService;
    @Autowired
    private ApiPayService apiPayService;


    /**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(WxOpenPTController.class);

    /**
     * checkSignature
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/entry/checkSignature")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response)
            throws AesException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);
        PrintWriter pw = response.getWriter();
        pw.append(echostr);
        pw.flush();

    }

    /**
     * 接收微信服务器的推送
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/entry/getComponentVerifyTicket")
    public void getComponentVerifyTicket(HttpServletRequest request, HttpServletResponse response)
            throws AesException, IOException {
        //处理微信推送的消息,解析出的消息为一个Map结构
        Map<String, Object> xmlmap = processSendMsg(request);
        logger.info("解密后的map:" + xmlmap);

        //根据InfoType来确定是哪张业务，授权、取消授权、更新授权
        String InfoType = MapUtils.getString(xmlmap, "InfoType");
        logger.info("微信推送的业务类型InfoType:" + InfoType);

        //微信定期推送的消息
        if (StringUtils.equals(InfoType, "component_verify_ticket")) {
            processAuthorizeEvent(xmlmap);
        }
        //微信取消授权
        if (StringUtils.equals(InfoType, Contants.UNAUTHORIZED)) {
            processCancelAuthorization(xmlmap);
        }

        PrintWriter out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    /*
    * 解析推送消息
    *
    * */

    public Map<String, Object> processSendMsg(HttpServletRequest request) throws AesException, IOException {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> xmlmap = new HashMap<>();
        String postStr = request.toString();
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String signature = request.getParameter("signature");
        String msgSignature = request.getParameter("msg_signature");
        logger.info("noce:{},timestamp:{}", nonce, timestamp);
        logger.info("signature:{},msgSignature:{}", signature, msgSignature);

        if (!StringUtils.isNotBlank(msgSignature))
            return null;// 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息

        StringBuilder sb = new StringBuilder();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        String xml = sb.toString();
        logger.info("解密前:{}", xml);

        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(Contants.MESSAGE_TOKEN, Contants.MSG_KEY, Contants.COMMONT_APPID);
        logger.info("微信推送消息的解码器："+wxBizMsgCrypt);
        xml = wxBizMsgCrypt.DecryptMsg(msgSignature, timestamp, nonce, xml);
        logger.info("解密后:{}", xml);
        try {
            xmlmap = CommonUtil.getMapFromXml(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlmap;
    }

    /*
    * 处理取消授权的操作
    * */
    public void processCancelAuthorization(Map<String, Object> params) {
        //处理参数
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> resultmap = getParammap(params);
        // 调API更改wx_authorization_msg表中的状态，同时删除公众号对应的权限集
        zlRpcResult = apitCsAuthorService.UnbundGzh(resultmap);
        logger.info("解绑第三方的结果：" + zlRpcResult);
                /*1、解绑虚拟开放平台和公众号的联系
                * 2、api查询开放平台的open_appid
                * 3、调第三方接口解绑第三方平台==暂时不用
                * 4、api更新第三方开放平台表
                * */
        //调第三方接口解除绑定
        String authorizerappid = MapUtils.getString(resultmap, "authorizerappid");
        Map<String, Object> delmap = new HashMap<>();
        zlRpcResult = apitCsAuthorService.queryOpenPlateMsg(resultmap);
        String openappid = MapUtils.getString(zlRpcResult.getMap(), "openappid");
        delmap.put("open_appid", openappid);
        delmap.put("appid", authorizerappid);
        JSONObject jsonObject = JSONObject.fromObject(delmap);
        String jsonParam = jsonObject.toString(); //作为参数的json字符串
        //从redis获取一下access_token  key=shopcode_authorizeraccesstoken
       /* String access_token = RedisFactory.getPassportClient().get(authorizerappid+"_"+"authorizeraccesstoken");
        if(StringUtils.isEmpty(access_token)){
            access_token = get_access_token(authorizerappid);
            logger.info("reid中的access_token为空走第三方查询的access_token："+access_token);
        }
        String umBlindUrl = Contants.UNBIND_THETHIRDPADT.replace("ACCESS_TOKEN",access_token);
        JSONObject unblinmap = JSONObject.fromObject(resultmap);
        String unblindjson = unblinmap.toString();
        String unblindresult = HttpClientUtil.httpsRequest(umBlindUrl, "POST", unblindjson);
        logger.info("调用第三方接口解绑微信开放平台的结果："+unblindresult);*/
        zlRpcResult = apitCsAuthorService.UnbundGzh(resultmap);
        // zlRpcResult = apitCsAuthorService.delOpenPlateMsg(resultmap);
        logger.info("解绑开放平台的结果：" + zlRpcResult);

    }


    /**
     * 处理微信的事件的推送
     *
     * @param params
     * @throws IOException
     */
    public void processAuthorizeEvent(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        logger.info("xml解析后获得的map:" + params);
        String componentverifyticket = MapUtils.getString(params, "ComponentVerifyTicket");
        logger.info("获取的componentverifyticket=" + componentverifyticket);
        Map<String, Object> parammap = new HashMap();
        parammap.put("commontappid", Contants.COMMONT_APPID);
        parammap.put("componentverifyticket", componentverifyticket);
        //插入Componentverifyticket之前先查下表里是否有了数据有了就更新
        zlRpcResult = apitCsTheThirdPartService.queryComponentverifyticket(parammap);
        logger.info("查询第三方令牌表的情况：" + zlRpcResult.getMap());
        if (zlRpcResult.success()) {
            Map<String, Object> resultmap = zlRpcResult.getMap();
            String dbcomponentverifyticket = MapUtils.getString(resultmap, "componentverifyticket");
            if (StringUtils.isEmpty(dbcomponentverifyticket)) { //第一次被微信回调
                //第一次获取componentverifyticket，componentaccesstoken和preauthcode

                //获取componentaccesstoken
                getComponentaccesstoken(parammap);
                logger.info("第一处的parammap：" + parammap);
                //获取预授权码
                getPreAuthCode(parammap);
                logger.info("第二处的parammap：" + parammap);
                apitCsTheThirdPartService.addComponentverifyticket(parammap);
            }
            //第N此被回调，componentverifyticket没过期，则componentaccesstoken需要判断下有没有过期，过期则更新，再获取预授权码
            if (StringUtils.isNotEmpty(dbcomponentverifyticket) && StringUtils.equals(componentverifyticket, dbcomponentverifyticket)) {
                String expretime = MapUtils.getString(resultmap, "expretime");
                if (checkExpretime(expretime)) {//说明没过期
                    parammap.put("componentaccesstoken", MapUtils.getString(resultmap, "componentaccesstoken"));
                    //授权码更新一下
                    getPreAuthCode(parammap);
                    logger.info("第三处的parammap：" + parammap);
                    //更新数据库
                    apitCsTheThirdPartService.updateComponentverifyticket(parammap);
                } else {                        //过期了
                    // 先获取最新的Componentaccesstoken，要更新过期时间
                    getComponentaccesstoken(parammap);
                    logger.info("第四处的parammap：" + parammap);
                    //获取预授权码
                    getPreAuthCode(parammap);
                    logger.info("第五处的parammap：" + parammap);
                    //更新数据库
                    apitCsTheThirdPartService.updateComponentverifyticket(parammap);

                }

            }
            //第N此被回调，componentverifyticket过期了，则componentaccesstoken肯定过期了重取
            if (StringUtils.isNotEmpty(dbcomponentverifyticket) && !StringUtils.equals(componentverifyticket, dbcomponentverifyticket)) {
                //更新componentverifyticket，则也得更新对应的componentaccesstoken和preauthcode；
                //dbcomponentverifyticket 数据库中旧的componentverifyticket
                // 先获取最新的Componentaccesstoken，要更新过期时间
                getComponentaccesstoken(parammap);
                //获取预授权码
                getPreAuthCode(parammap);
                //更新数据库
                logger.info("令牌过期时更新数据库的参数：" + parammap);
                apitCsTheThirdPartService.updateComponentverifyticket(parammap);

            }
        }
    }

    //校验一下commomnent_acessotken是否过期
    public boolean checkExpretime(String expretime) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date olddate = spf.parse(expretime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        String nowTime = spf.format(date);
        if (nowTime.compareTo(expretime) < 0) {
            return true;
        } else {
            return false;
        }
    }

    //获取第三方平台component_access_token
    public void getComponentaccesstoken(Map<String, Object> params) {
        Map<String, Object> commonmap = new HashMap<>();
        logger.info("获取component_access_token的参数params：" + params);
        commonmap.put("component_appid", Contants.COMMONT_APPID);
        commonmap.put("component_appsecret", Contants.COMMONT_APPSECRET);
        commonmap.put("component_verify_ticket", MapUtils.getString(params, "componentverifyticket"));
        JSONObject jsonObject = JSONObject.fromObject(commonmap);
        String jsonParam = jsonObject.toString(); //作为参数的json字符串
        logger.info("调第三方接口的Json字符串：" + jsonParam);
        String jsonresult = HttpClientUtil.httpsRequest(Contants.COMMONT_ACCESSTOKEN_URL, "POST", jsonParam);
        logger.info("调第三方接口的返回的结果的Json字符串：" + jsonresult);
        JSONObject jsonn = JSONObject.fromObject(jsonresult);
        String componentaccesstoken = jsonn.getString("component_access_token");
        logger.info("最终获取到的component_access_token： " + componentaccesstoken);
        params.put("componentaccesstoken", componentaccesstoken);
        //获取一下过期时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeLose = System.currentTimeMillis() + 2 * 60 * 60 * 1000;
        String expretime = sdf.format(new Date(timeLose));
        params.put("expretime", expretime);
        logger.info("commonentaccesstoken的过期时间expretime：" + expretime);
    }

    //获取第三方平台的预授权码
    public void getPreAuthCode(Map<String, Object> params) {
        Map<String, Object> authmap = new HashMap<>();
        logger.info("获取component_access_token的参数params：" + params);
        String componentaccesstoken = MapUtils.getString(params, "componentaccesstoken");
        logger.info("获取的componentaccesstoken：" + componentaccesstoken);
        authmap.put("component_appid", Contants.COMMONT_APPID);
        JSONObject jsonObject = JSONObject.fromObject(authmap);
        String jsonParam = jsonObject.toString(); //作为参数的json字符串
        logger.info("调第三方接口的Json字符串：" + jsonParam);

        String preauthcodeUrl = Contants.COMMONT_PREAUTHCODE_URL;

        preauthcodeUrl = preauthcodeUrl.replace("COMPONENTACCESSTOKEN", componentaccesstoken);

        logger.info("最新的获取预授权码的URL: " + preauthcodeUrl);

        String jsonresult = HttpClientUtil.httpsRequest(preauthcodeUrl, "POST", jsonParam);
        logger.info("调第三方接口的返回的结果的Json字符串：" + jsonresult);
        JSONObject jsonn = JSONObject.fromObject(jsonresult);

        String preauthcode = jsonn.getString("pre_auth_code");
        logger.info("最终获取到的pre_auth_code： " + preauthcode);

        params.put("preauthcode", preauthcode);

    }


    /**
     * 授权页面点击授权回调接口
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/entry/authPageCallback")
    public void authPageCallback(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //获得授权码
        String authorization_code = request.getParameter("auth_code");
        String bandtype = request.getParameter("bandtype");
        String shopcode = request.getParameter("shopcode");
        String component_appid = Contants.COMMONT_APPID;
        Map<String, Object> resultmap = new HashMap<>();
        resultmap.put("commontappid", component_appid);
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //获取component_access_token
        zlRpcResult = apitCsTheThirdPartService.queryComponentverifyticket(resultmap);
        logger.info("授权的时候查的配置表：" + zlRpcResult);
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        String component_access_token = MapUtils.getString(zlRpcResult.getMap(), "componentaccesstoken");

        //String component_access_token = "312323423";
        //使用授权码换取公众号或小程序的接口调用凭据和授权信息
        Map<String, Object> authorization_info = queryauth(authorization_code, component_appid, component_access_token);
        authorization_info.put("authorization_code", authorization_code);
        logger.info("用授权码换取公众号或小程序的接口调用凭据和授权信息:"+authorization_info);
        //保存被授权方相关信息
        saveMessage(authorization_info, component_access_token, component_appid, shopcode, bandtype);
        logger.info("保存被授权方相关信息成功");
        //将授权方绑定至开放平台
        bundToOpenPlate(shopcode, authorization_info, bandtype);
        logger.info("将授权方绑定至开放平台成功");
        //如果是小程序 需要修改小程序服务器地址
        if (Contants.COMPONENT_TYPE_XCX.equals(bandtype)) {
            changeXcxDomain(authorization_info);
            logger.info("修改小程序服务器地址");
        }

    }

    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
     *
     * @param authorization_code     预授权码
     * @param component_appid        第三方平台appid
     * @param component_access_token 第三方平台access token
     * @return
     */
    Map<String, Object> queryauth(String authorization_code, String component_appid, String component_access_token) {
        String httpUrl = Contants.API_QUERY_AUTH_URL;
        httpUrl = httpUrl.replace("COM_ACC_TOKEN", component_access_token);
        Map<String, Object> urlparams = new HashMap<>();
        urlparams.put("component_appid", component_appid);
        urlparams.put("authorization_code", authorization_code);
        String json = JSONObject.fromObject(urlparams).toString();
        String result = HttpClientUtil.httpsRequest(httpUrl, "POST", json
               /* "{\"component_appid\":\""  + component_appid + "\",\"authorization_code\":\"" +authorization_code + "\"}"*/);
        Map<String, Object> resultmap = JSONObject.fromObject(result);
        Map<String, Object> authorization_info = (Map<String, Object>) MapUtils.getObject(resultmap, "authorization_info");
        return authorization_info;
    }

    /**
     * 保存 公众号被授权方相关信息
     *
     * @param authorization_info     接口调用凭据和授权信息
     * @param component_access_token 第三方平台access token
     * @param component_appid        第三方平台appid
     */
    void saveMessage(Map<String, Object> authorization_info, String component_access_token, String component_appid, String shopcode, String bandtype) {
        //授权方appid
        String authorizer_appid = MapUtils.getString(authorization_info, "authorizer_appid");
        //授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
        String authorizer_access_token = MapUtils.getString(authorization_info, "authorizer_access_token");
        //有效期
        String expires_in = MapUtils.getString(authorization_info, "expires_in");
        //接口调用凭据刷新令牌
        String authorizer_refresh_token = MapUtils.getString(authorization_info, "authorizer_refresh_token");
        //获取公众号的
        String httpUrl = Contants.GET_GZH_INFO_URL;
        httpUrl = httpUrl.replace("COM_ACC_TOKEN", component_access_token);
        String result = HttpClientUtil.httpsRequest(httpUrl, "POST",
                "{\"component_appid\":\"" + component_appid + "\",\"authorizer_appid\":\"" + authorizer_appid + "\"}");
        Map<String, Object> resultmap = JSONObject.fromObject(result);
        Map<String, Object> authorizer_info = MapUtils.getMap(resultmap, "authorizer_info");
        authorizer_info.put("authorizer_access_token", authorizer_access_token);
        authorizer_info.put("authorizer_refresh_token", authorizer_refresh_token);
        authorizer_info.put("commontappid", component_appid);
        authorizer_info.put("authorizerappid", authorizer_appid);
        authorizer_info.put("authorizationcode", MapUtils.getString(authorization_info, "authorization_code"));
        Map<String, Object> params = new HashMap<>();
        params.put("appid", authorizer_appid);
        authorizer_info.put("shopcode", shopcode);
        authorizer_info.put("bandtype", bandtype);
        //封装保存至数据库的被授权方信息 并保存
        apitCsAuthorService.addAuthorMsg(modifyMsgParams(authorizer_info));
        //更新pay系统的配置表
        Map<String, Object> payparams = new HashMap<>();
        payparams.put("wxpubappid", authorizer_appid);
        payparams.put("shopcode", shopcode);
        //1-授权  0-未授权
        payparams.put("isauth", "1");
        //bandtype 1-公众号  2-小程序 --传给金科一定要0
        if(StringUtils.equals(bandtype,"2")){
            payparams.put("bandtype", "0");
        }else {
            payparams.put("bandtype", bandtype);
        }
        logger.info("准备更新pay系统的配置表");
        apiPayService.updatePlateformAuth(payparams);
        logger.info("更新pay系统的配置表成功");
        authorization_info = (Map<String, Object>) MapUtils.getObject(resultmap, "authorization_info");
        List<Map<String, Object>> func_info = (List<Map<String, Object>>) MapUtils.getObject(authorization_info, "func_info");
        //封装参数 并保存
        apitCsAuthorService.addAuthorFunc(modifyparams(func_info, authorizer_info));
    }

    /**
     * 修改小程序服务器地址
     *
     * @param authorization_info 接口调用凭据和授权信息
     */
    void changeXcxDomain(Map<String, Object> authorization_info) {
        String domain = "http://lspre.zhonglunnet.com";
        String authorizer_appid = MapUtils.getString(authorization_info, "authorizer_appid");
        String httpUrl = Contants.MODIFY_DOMAIN_URL;
        httpUrl = httpUrl.replace("TOKEN", get_access_token(authorizer_appid));
        Map<String, Object> httpMap = new HashMap<>();
        httpMap.put("action", "add");
        String[] domains = {domain, domain};
        httpMap.put("requestdomain", domains);
        httpMap.put("wsrequestdomain", domains);
        httpMap.put("uploaddomain", domains);
        httpMap.put("downloaddomain", domains);
        //设置小程序服务器域名
        String result = HttpClientUtil.httpsRequest(httpUrl, "POST", JSONObject.fromObject(httpMap).toString());
        Map<String, Object> resultmap = JSON.parseObject(result);
        String errcode = MapUtils.getString(resultmap, "errcode");
        if (errcode.equals("0")) {
            //设置小程序业务域名
            httpUrl = Contants.SETWEBVIEWDOMAIN_URL;
            httpUrl = httpUrl.replace("TOKEN", get_access_token(authorizer_appid));
            httpMap.clear();
            httpMap.put("action", "add");
            httpMap.put("webviewdomain", domains);
            result = HttpClientUtil.httpsRequest(httpUrl, "POST", JSONObject.fromObject(httpMap).toString());
        }
    }


    /**
     * 将公众号绑定至开放平台
     *
     * @param shopcode           商户编码
     * @param authorization_info 授权信息
     */

    void bundToOpenPlate(String shopcode, Map<String, Object> authorization_info,String bandtype) {
        //授权方appid
        String authorizer_appid = MapUtils.getString(authorization_info, "authorizer_appid");
        //授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
        String authorizer_access_token = MapUtils.getString(authorization_info, "authorizer_access_token");
        //去本地查看该商户是否存在开放平台
        Map<String, Object> openplateParams = new HashMap<>();
        openplateParams.put("shopcode", shopcode);
        Map<String, Object> openplateMsg = new HashMap<>();
        ZLRpcResult zlRpcResult = apitCsAuthorService.queryOpenPlateMsg(openplateParams);
        if (zlRpcResult.success()) {
            openplateMsg = zlRpcResult.getMap();
        } else {
            throw new ServiceException("获取开放平台信息失败");
        }
        //如果存在 走绑定接口
        Map<String, Object> urlParams = new HashMap<>();
        if (MapUtils.isNotEmpty(openplateMsg)) {
            String httpUrl = Contants.OPEN_BIND_URL;
            String openappid = MapUtils.getString(openplateMsg, "openappid");
            httpUrl = httpUrl.replace("TOKEN", authorizer_access_token);
            urlParams.put("appid", authorizer_appid);
            urlParams.put("open_appid", MapUtils.getString(openplateMsg, "openappid"));
            String httpreult = HttpClientUtil.httpsRequest(httpUrl, "POST", JSONObject.fromObject(urlParams).toString());
            Map<String, Object> resultmap = JSON.parseObject(httpreult);
            String errcode = MapUtils.getString(resultmap, "errcode");
            //保存信息 至本地表
            if (StringUtils.equals(errcode, "0")) {
                openplateMsg.clear();
                openplateMsg.put("shopcode", shopcode);
                openplateMsg.put("openappid", openappid);
                openplateMsg.put("authorizerappid", authorizer_appid);
                openplateMsg.put("flag", bandtype);
                apitCsAuthorService.addOpenPlateMsg(openplateMsg);
            }
        } else {
            String httpUrl = Contants.OPEN_BIND_CREATE_URL;
            httpUrl = httpUrl.replace("TOKEN", authorizer_access_token);
            urlParams.put("appid", authorizer_appid);
            String httpreult = HttpClientUtil.httpsRequest(httpUrl, "POST", JSONObject.fromObject(urlParams).toString());
            Map<String, Object> resultmap = JSON.parseObject(httpreult);
            String errcode = MapUtils.getString(resultmap, "errcode");
            //保存信息 至本地表
            if (StringUtils.equals(errcode, "0")) {
                Map<String, Object> nopenplateMsg = new HashMap<>();
                nopenplateMsg.put("shopcode", shopcode);
                nopenplateMsg.put("openappid", MapUtils.getString(resultmap, "open_appid"));
                nopenplateMsg.put("authorizerappid", authorizer_appid);
                nopenplateMsg.put("flag", bandtype);
                apitCsAuthorService.addOpenPlateMsg(nopenplateMsg);
            }
        }
    }

    Map<String, Object> modifyMsgParams(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("shopcode", MapUtils.getString(params, "shopcode"));
        result.put("commontappid", MapUtils.getString(params, "commontappid"));
        result.put("authorizerappid", MapUtils.getString(params, "authorizerappid"));
        result.put("authorizeraccesstoken", MapUtils.getString(params, "authorizer_access_token"));
        result.put("authorizationcode", MapUtils.getString(params, "authorizationcode"));
        result.put("authorizerrefreshtoken", MapUtils.getString(params, "authorizer_refresh_token"));
        result.put("nickname", MapUtils.getString(params, "nick_name"));
        result.put("headimg", MapUtils.getString(params, "head_img"));
        result.put("servicetypeinfo", MapUtils.getString(MapUtils.getMap(params, "service_type_info"), "id"));
        result.put("verifytypeinfo", MapUtils.getString(MapUtils.getMap(params, "verify_type_info"), "id"));
        result.put("username", MapUtils.getString(params, "user_name"));
        result.put("principalname", MapUtils.getString(params, "principal_name"));
        result.put("alias", MapUtils.getString(params, "alias"));
        result.put("qrcodeurl", MapUtils.getString(params, "qrcode_url"));
        result.put("signature", MapUtils.getString(params, "signature", ""));
        result.put("flag", MapUtils.getString(params, "bandtype"));
        return result;
    }


    List<Map<String, Object>> modifyparams(List<Map<String, Object>> params, Map<String, Object> configinfo) {
        List<Map<String, Object>> results = new ArrayList<>();
        for (Map<String, Object> param : params) {
            Map<String, Object> result = new HashMap<>();
            Map<String, Object> funcscope_category = MapUtils.getMap(param, "funcscope_category");
            int id = MapUtils.getIntValue(funcscope_category, "id");
            result.put("funcid", id);
            result.put("shopcode", MapUtils.getString(configinfo, "shopcode"));
            result.put("commontappid", MapUtils.getString(configinfo, "commontappid"));
            result.put("authorizerappid", MapUtils.getString(configinfo, "authorizerappid"));
            switch (id) {
                case 1:
                    result.put("funcname", "消息管理权限");
                    break;
                case 2:
                    result.put("funcname", "用户管理权限");
                    break;
                case 3:
                    result.put("funcname", "帐号服务权限");
                    break;
                case 4:
                    result.put("funcname", "网页服务权限");
                    break;
                case 5:
                    result.put("funcname", "微信小店权限");
                    break;
                case 6:
                    result.put("funcname", "微信多客服权限");
                    break;
                case 7:
                    result.put("funcname", "群发与通知权限");
                    break;
                case 8:
                    result.put("funcname", "微信卡券权限");
                    break;
                case 9:
                    result.put("funcname", "微信扫一扫权限");
                    break;
                case 10:
                    result.put("funcname", "微信连WIFI权限");
                    break;
                case 11:
                    result.put("funcname", "素材管理权限 ");
                    break;
                case 12:
                    result.put("funcname", "微信摇周边权限 ");
                    break;
                case 13:
                    result.put("funcname", "微信门店权限");
                    break;
                case 14:
                    result.put("funcname", "微信支付权限");
                    break;
                case 15:
                    result.put("funcname", "自定义菜单权限");
                    break;
                case 16:
                    result.put("funcname", "获取认证状态及信息");
                    break;
                case 17:
                    result.put("funcname", "帐号管理权限");
                    break;
                case 18:
                    result.put("funcname", "开发管理与数据分析权限");
                    break;
                case 19:
                    result.put("funcname", "客服消息管理权限");
                    break;
                case 20:
                    result.put("funcname", "微信登录权限");
                    break;
                case 21:
                    result.put("funcname", "数据分析权限");
                    break;
                case 22:
                    result.put("funcname", "城市服务接口权限");
                    break;
                case 23:
                    result.put("funcname", "广告管理权限");
                    break;
                case 24:
                    result.put("funcname", "开放平台帐号管理权限");
                    break;
                case 25:
                    result.put("funcname", "开放平台帐号管理权限");
                    break;
                case 26:
                    result.put("funcname", "微信电子发票权限");
                    break;
            }
            if (StringUtils.isEmpty(MapUtils.getString(result, "funcname"))) {
                result.put("funcname", "未知权限");
            }
            results.add(result);
        }
        return results;
    }
    //解绑公众号


    //处理一下解除绑定调接口的参数
    public Map<String, Object> getParammap(Map<String, Object> params) {
        Map<String, Object> parammap = new HashMap<>();
       /* Map<String , Object> configinfo = apiPayService.getConfigByAppid(params).getMap();
        String shopcode = MapUtils.getString(configinfo,"shopcode");
        logger.info("通过公众号的appid查询到对应的shopcode:"+shopcode);
        parammap.put("shopcode",shopcode);*/
        parammap.put("commontappid", MapUtils.getString(params, "AppId"));//第三方平台的appid
        parammap.put("authorizerappid", MapUtils.getString(params, "AuthorizerAppid"));
        parammap.put("del", "1");
        return parammap;
    }

    /*
    *
    *小程序代码审核后的回调
    * */
    @RequestMapping(value = "/entry/{APPID}/callback.action")
    public void sendMsfAfterAuthorize(@PathVariable("APPID") String APPID, HttpServletRequest request, HttpServletResponse response) {
        try {
            ZLRpcResult zlRpcResult = new ZLRpcResult();
            //判断一下APPID  公众号或者小程序的authorizerappid
            if (StringUtils.isEmpty(APPID)) {
                logger.info("开发小程序的APPID:" + APPID);
                throw new ServiceException(ServiceException.PARAM_ERROR);
            }
            //Map<String, Object> xmlmap = processSendMsg(request);
            String xml = CommonUtil.getXML(request);
            logger.info("审核完成后的推送消息：" + xml);
            Map<String, Object> xmlmap = null;
            try {
                 xmlmap = CommonUtil.getMapFromXml(xml);
                logger.info("审核完成后的xml中map：" + xmlmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //判断一下是小程序的代码提交后的消息推送，其他暂时不处理
            String Event = MapUtils.getString(xmlmap,"Event");
            if(StringUtils.equals(Event,Contants.XCX_DMAUDIT_FAILED) || StringUtils.equals(Event,Contants.XCX_DMAUDIT_SUCESS)) {

                //更新状态的API接口
                Map<String, Object> result = getApiMap(xmlmap,APPID);
                //小程序原始id
                String tousername = MapUtils.getString(xmlmap, "ToUserName");
                logger.info("小程序的原始id:" + tousername);
                //调api获取authorizerappid
                Map<String,Object> resulmap = new HashMap<>();
                resulmap.put("username",tousername);
                zlRpcResult = apitCsAuthorService.queryMsgByUserName(resulmap);
                String authorizerappid = null;
                if (zlRpcResult.success()) {
                    authorizerappid = MapUtils.getString(zlRpcResult.getMap(), "authorizerappid");
                    logger.info("调api接口查询的authorizerappid:" + authorizerappid);
                }
                result.put("shopcode",MapUtils.getString(zlRpcResult.getMap(),"shopcode"));
                result.put("authorizerappid", authorizerappid);
                logger.info("审核发布后回调更新表的参数："+result);
                apitXcxCodeModelService.updateCodeModellog(result);
            }

            // processAfterAuthorize(request);
//            PrintWriter out = response.getWriter();
//            out.write("success");
//            out.flush();
//            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    //更新审核后的回调的参数
    public Map<String, Object> getApiMap(Map<String, Object> params,String appid) {
        Map<String, Object> resultmap = new HashMap<>();
        resultmap.put("tousername", MapUtils.getString(params, "ToUserName"));
        resultmap.put("fromusername", MapUtils.getString(params, "FromUserName"));
        resultmap.put("event", MapUtils.getString(params, "Event"));
        String reason = MapUtils.getString(params, "reason");
        if (StringUtils.isNotEmpty(reason)) {
            resultmap.put("reason", reason);
            resultmap.put("status", 2);
        } else {
            resultmap.put("status", 1);
            //  发布代码
            String access_token = get_access_token(appid);
            String issure_url = Contants.ISSURE_XCX;
            issure_url = issure_url.replace("TOKEN",access_token);
            Map<String, Object> jsonmap = new HashMap<>();
            String json = JSONObject.fromObject(jsonmap).toString();
            String jsonresult = HttpClientUtil.httpsRequest(issure_url, "POST", json);
            logger.info("审核成功后发布小程序的情况：" + jsonresult);
            JSONObject jsonn = JSONObject.fromObject(jsonresult);
            //代码发布的状态码
            String errcode = jsonn.getString("errcode");
            logger.info("发布代码的返回码：" + errcode);
            if (StringUtils.equals("0", errcode)) { //审核成功发布成功
                resultmap.put("isshowflag", "0");
            } else {
                resultmap.put("isshowflag", "1"); //审核成功发布失败
            }
        }
        return resultmap;
    }


    //获取第三方平台component_access_token
    @RequestMapping("/entry/testAuth.action")
    @ResponseBody
    public String testAuth(HttpServletRequest request) {
        return HttpClientUtil.httpsRequest(
                "https://api.weixin.qq.com/cgi-bin/component/api_component_token",
                "POST",
                "{\"component_appid\":\"wx039158dd1b0e5f81\",\"component_appsecret\":\"0728cfcf952e15c4a527d52ed2cac1dc\",\"component_verify_ticket\":\"ticket@@@xZy7CVFxWKPkEYsNmN1WkDHXmCdGRvX7rxMOMWnRPwexDxwQPc4nwad7CBKR871syQg09ykPPiEwTI5nQKy0Uw\"}");
    }

    //获取预授权码pre_auth_code        到此第一步完成获得预授权码
    @RequestMapping("/entry/testPreAuthCode.action")
    @ResponseBody
    public String testPreAuthCode(HttpServletRequest request) {
        return HttpClientUtil.httpsRequest(
                "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=13_u3oudbC_VFRQHwOueUrtr4gE3wJyQClA6OUgbO-wHhBDFmvu812kpe_6PkPb2PiaxCzf6x6DjQQvHdJR3LkU3SqMYRI3tSE3erYtNOKpcQDeUF893oD6bH6eGk1crzDWegSYJhWv-cVbo_ONCTJhACAOFV",
                "POST",
                "{\"component_appid\":\"wx039158dd1b0e5f81\"}");
    }

    @RequestMapping("/entry/testAuthPage.action")
    public String testAuthPage(HttpServletRequest request) {
        return "test_auth";
    }

    // 使用授权码换取公众号或小程序的接口调用凭据和授权信息
    //并换取authorizer_access_token和authorizer_refresh_token
    @RequestMapping("/entry/testQueryAuth.action")
    @ResponseBody
    public String testQueryAuth(HttpServletRequest request) {
        return HttpClientUtil.httpsRequest(
                "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=10_fUv5FgPiFt5ayVb2VUpS3pTX0M3Nn2NubDEraFlxVrX7qPLquk5Y7Ho7MHcwUearUWOUpZFNMzDv8CqgxJsezNuxVhDywGU7Dh-5H5LSYRR9JwnHGcKQo8q_poVj5DNFu73aw4VD2PmdJ51PLPBhAFAQUG",
                "POST",
                "{\"component_appid\":\"wx039158dd1b0e5f81\",\"authorization_code\":\"queryauthcode@@@gJfOOOzaYLam1u0gxb8G2hpFxozTtNTUjzjyf5ptyo9krBtO9CyrvOR4s_d6cZ0fZFh7CW0KAZkH_v8GTJui9g\"}");
    }

    @RequestMapping("/entry/createOpenPTAccount.action")
    @ResponseBody
    public String createOpenPTAccount(HttpServletRequest request) {
        return HttpClientUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/open/create?access_token=10_67XzegAqWs5wgCpsEAOT-OcsaLOfywrlYt2YpwDjmScRDaHXtFf74gra-qG57QFVCuf7yN2Ty_yjVmtjNBsMdMrcFXM8dmebLu9Z3krrzqcbPzIpzAbTVQhqZDKhNfahcI2bTyT5uoTcVco0RONdAIDGDC",
                "POST",
                "{\"appid\":\"wxab4bacd11755f9f3\"}");
    }

    @RequestMapping("/entry/bindOpenPTAccount.action")
    @ResponseBody
    public String bindOpenPTAccount(HttpServletRequest request) {
        return HttpClientUtil.httpsRequest(
                "https://api.weixin.qq.com/cgi-bin/open/bind?access_token=10_5nDeiyhbaQqpswmajS0to7XbKB-3Ash-bqv-biUOjVk_2RFs6ieJLu9yefsEojeYSOkCbanHSdbYoElv7kFNh5t1lzSFbJ6Z1v6059mTCz4R9_rLi-Rn_us6W1et7p74umOQNhjSxB3Km0UgHQYbALDOXF",
                "POST",
                "{\"appid\":\"wx555f316690b3294a\",\"open_appid\":\"wx8660fa27528f58bc\"}");
    }


    @RequestMapping("/entry/gettemplatedraftlist.action")
    @ResponseBody
    public String gettemplatedraftlist(HttpServletRequest request) {
        String url = "https://api.weixin.qq.com/wxa/gettemplatedraftlist?access_token=TOKEN";

        return HttpClientUtil.httpsRequest(
                "https://api.weixin.qq.com/cgi-bin/open/bind?access_token=10_5nDeiyhbaQqpswmajS0to7XbKB-3Ash-bqv-biUOjVk_2RFs6ieJLu9yefsEojeYSOkCbanHSdbYoElv7kFNh5t1lzSFbJ6Z1v6059mTCz4R9_rLi-Rn_us6W1et7p74umOQNhjSxB3Km0UgHQYbALDOXF",
                "POST",
                "{\"appid\":\"wx555f316690b3294a\",\"open_appid\":\"wx8660fa27528f58bc\"}");
    }


}
