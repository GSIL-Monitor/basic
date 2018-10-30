package com.wgb.controller.mt.wxshop;

import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCsTheThirdPartService;
import com.wgb.service.dubbo.wxms.web.ApitCsAuthorService;
import com.wgb.service.dubbo.wxms.web.ApitXcxCodeModelService;
import com.wgb.util.Contants;
import com.wgb.util.HttpClientUtil;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.MMSRecord;
import org.bouncycastle.asn1.cmp.OOBCertHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 10057 on 2018/9/12.
 */
@Controller
@RequestMapping("/wxshop/opentp")
public class MTWxOpenPTController extends MBBaseController {
    private static final Logger logger = LoggerFactory.getLogger(MTWxOpenPTController.class);
    @Autowired
    private ApitCsAuthorService apitCsAuthorService;
    @Autowired
    private ApitCsTheThirdPartService apitCsTheThirdPartService;
    @Autowired
    private ApitXcxCodeModelService apitXcxCodeModelService;

    /**
     * 获取跳转至公众号后台设置页面的数据查询接口
     */
    @RequestMapping("/queryGzhManagePage")
    @ResponseBody
    public ZLResult queryGzhManagePage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("flag", Contants.COMPONENT_TYPE_GZH);
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsAuthorService.queryGzhManagePage(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * 获取跳转至公众号后台设置页面的数据查询接口
     */
    @RequestMapping("/queryXcxManagePage")
    @ResponseBody
    public ZLResult queryXcxManagePage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("flag", Contants.COMPONENT_TYPE_XCX);
        Map<String, Object> resultmap = new HashMap<>();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsAuthorService.queryGzhManagePage(params);
            resultmap = zlRpcResult.getMap();
            if (MapUtils.isNotEmpty(resultmap) && StringUtils.equals("1", MapUtils.getString(resultmap, "wheatherband"))) {
                String commontappid = Contants.COMMONT_APPID;
                List<Map<String, Object>> list = getXcxCodeModel(commontappid);
                resultmap.put("xcxcodemodel", list);
                //加个check看看是否已经提交过代码
                checkWhetherTosubmit(resultmap);
            }
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(resultmap);
    }
    //校验是否已经选择过代码提交
    public void checkWhetherTosubmit(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> authorMsg = MapUtils.getMap(params, "authorMsg");
        List<Map<String, Object>> xcxcodemodel = (List<Map<String, Object>>) MapUtils.getObject(params, "xcxcodemodel");
        //获取授权小程序的authorizerappid
        String authorizerappid = MapUtils.getString(authorMsg, "authorizerappid");
        logger.info("检验是否选择过代码版本的校验参数authorizerappid:"+authorizerappid);
        Map<String, Object> xcxcodemodelmap = xcxcodemodel.get(0);
        String userversion = MapUtils.getString(xcxcodemodelmap,"user_version");
        logger.info("检验是否选择过代码版本的校验参数userversion:"+userversion);
        String templateid = MapUtils.getString(xcxcodemodelmap,"template_id");
        logger.info("检验是否选择过代码版本的校验参数templateid:"+templateid);
        Map<String,Object> resultmap = new HashMap<>();
        resultmap.put("authorizerappid",authorizerappid);
        resultmap.put("userversion",userversion);
        resultmap.put("shopcode",MapUtils.getString(authorMsg,"shopcode"));
        //resultmap.put("templateid",templateid);
        //调API查询此版本的发布记录数量
        zlRpcResult = apitXcxCodeModelService.getCodeModelCount(resultmap);
        int count = (int) zlRpcResult.getData();
        if(count<=0){
            params.put("chosencodestatus",0); //未提交过代码
        }else{
            params.put("chosencodestatus",1); //已经提交过代码
        }
    }
    /**
     * 审核成功后发布失败重新发布
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/addCodeModelCommitPardon")
    @ResponseBody
    public ZLResult addCodeModelCommitPardon(HttpServletRequest request) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String errorcode = null;
        try {
            Map<String, Object> params = getParams();
            params.put("templateid",MapUtils.getString(params,"template_id"));
            params.put("userversion",MapUtils.getString(params,"user_version"));
            String authorizerappid = MapUtils.getString(params, "authorizerappid");
            logger.info("重新发布代码的authorizerappid：" + authorizerappid);
            String access_token = get_access_token(authorizerappid);
            logger.info("重新发布代码获取的access_token:" + access_token);
            //  发布代码
            String issure_url = Contants.ISSURE_XCX;
            issure_url = issure_url.replace("TOKEN",access_token);
            //String json = "";
            Map<String, Object> jsonmap = new HashMap<>();
            String json = JSONObject.fromObject(jsonmap).toString();
            String jsonresult = HttpClientUtil.httpsRequest(issure_url, "POST", json);
            logger.info("代码发布的结果jsonresult：" + jsonresult);
            JSONObject jsonresul = JSONObject.fromObject(jsonresult);
            errorcode = jsonresul.getString("errcode");
            if(StringUtils.equals(errorcode,Contants.COMMIT_ERRCODE)){ //发布代码成功
                params.put("isshowflag",0);
                apitXcxCodeModelService.updateCodeModellog(params);
            }


        } catch (ServiceException e){

        }catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(StringUtils.equals(errorcode,Contants.COMMIT_FAILED_DISSATISFY)){
            throw new ServiceException("审核状态未满足发布");
        }
        if(StringUtils.equals(errorcode,Contants.COMMIT_FAILED_UNAUDIT)){
            throw new ServiceException("没有审核版本");
        }
        if(StringUtils.equals(errorcode,Contants.COMMIT_FAILED_SYSTEMBUSY)){
            throw new ServiceException("系统繁忙");
        }
        if(StringUtils.equals(errorcode,Contants.APP_ALREADY_RELEASED)){
            throw new ServiceException("此版本小程序已经发布过！");
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }


    /**
     * 查询代码提交审核和发布的状态
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/queryCodeModelCommitStatus")
    @ResponseBody
    public ZLResult queryCodeModelCommitStatus(HttpServletRequest request) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String,Object> resultmap = new HashMap<>();
        try {
            Map<String, Object> params = getParams();
            // 系统调用
            params.put("templateid",MapUtils.getString(params,"template_id"));
            params.put("userversion",MapUtils.getString(params,"user_version"));
            zlRpcResult = apitXcxCodeModelService.queryCodeModelCommitStatus(params);
            resultmap = zlRpcResult.getMap();
            logger.info("查询小程序代码模板审核发布的状态："+zlRpcResult);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(resultmap);
    }

    /**
     * 获取跳转至授权二维码参数
     */
    @RequestMapping("/getQRCodeParams")
    @ResponseBody
    public ZLResult getQRCodeParams(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String bandtype = MapUtils.getString(params, "bandtype");
        Map<String, Object> resultmap = new HashMap<>();
        String shopcode = MapUtils.getString(params, "shopcode");
        resultmap.put("commontappid", Contants.COMMONT_APPID);
        //预授权码
        zlRpcResult = apitCsTheThirdPartService.queryComponentverifyticket(resultmap);
        logger.info("授权的时候查的配置表：" + zlRpcResult);
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        String redirect_uri = "http://lspre.zhonglunnet.com/entry/authPageCallback.action?shopcode=pshopcode&bandtype=pbandtype";
        redirect_uri = redirect_uri.replace("pshopcode", shopcode);
        redirect_uri = redirect_uri.replace("pbandtype", bandtype);
        try {
            redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String pre_auth_code = MapUtils.getString(zlRpcResult.getMap(), "preauthcode");
        resultmap.put("pre_auth_code", pre_auth_code);
        //回调地址
        resultmap.put("redirect_uri", redirect_uri);
        //发送地址
        resultmap.put("send_url", Contants.COMMONT_GETQRCODE);
        resultmap.put("component_appid", Contants.COMMONT_APPID);
        //要授权的帐号类型
        if (StringUtils.equals(bandtype, Contants.COMPONENT_TYPE_GZH)) {
            resultmap.put("auth_type", Contants.COMPONENT_TYPE_GZH);
        }
        if (StringUtils.equals(bandtype, Contants.COMPONENT_TYPE_XCX)) {
            resultmap.put("auth_type", Contants.COMPONENT_TYPE_XCX);
        }

        return ZLResult.Success(resultmap);
    }

    /**
     * 公众号在商户后台进行第三方平台的解绑
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/deleteTheThirdPart")
    @ResponseBody
    public ZLResult deleteTheThirdPart(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> resultmap = new HashMap<>();
        //String shopcode = MapUtils.getString(params,"shopcode");
        String authorizerappid = MapUtils.getString(params, "authorizerappid");
        if (StringUtils.isEmpty(authorizerappid)) {
            logger.info("解除绑定的参数authorizerappid:" + authorizerappid);
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        //通过authorizerappid调API查询开放平台的open_appid
        Map<String, Object> authmap = new HashMap<>();
        authmap.put("authorizerappid", authorizerappid);
     /*   zlRpcResult = apitCsAuthorService.queryOpenPlateMsg(authmap);
        String openappid = MapUtils.getString(zlRpcResult.getMap(),"openappid");
        logger.info("开放平台的openappid："+openappid);
        resultmap.put("open_appid", openappid);
        resultmap.put("appid",authorizerappid);*/
        /*JSONObject jsonObject = JSONObject.fromObject(resultmap);
        String jsonParam = jsonObject.toString(); //作为参数的json字符串*/
        //从redis获取一下access_token  key=shopcode_authorizeraccesstoken
      /*  String access_token = RedisFactory.getPassportClient().get(authorizerappid+"_"+"authorizeraccesstoken");
        if(StringUtils.isEmpty(access_token)){
            access_token = get_access_token(authorizerappid);
            logger.info("reid中的access_token为空走第三方查询的access_token："+access_token);
        }
         String umBlindUrl = Contants.UNBIND_THETHIRDPADT.replace("ACCESS_TOKEN",access_token);
         JSONObject unblinmap = JSONObject.fromObject(resultmap);
         String unblindjson = unblinmap.toString();
         String unblindresult = HttpClientUtil.httpsRequest(umBlindUrl, "POST", unblindjson);
         logger.info("调用第三方接口解绑微信授权的结果："+unblindresult);*/
        //API更新授权表
         /*authmap.put("commontappid",Contants.COMMONT_APPID);
         authmap.put("del","1");*/
        zlRpcResult = apitCsAuthorService.UnbundGzh(authmap);
        logger.info("解绑第三方的结果：" + zlRpcResult);
        //  zlRpcResult = apitCsAuthorService.delOpenPlateMsg(resultmap);
        logger.info("解绑开放平台的结果：" + zlRpcResult);
        return ZLResult.Success();
    }

    /**
     * 选择小程序代码模板上传代码+代码审核提交
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/updateLoadcode")
    @ResponseBody
    public ZLResult updateLoadcode(HttpServletRequest request) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> params = getParams();
        String uploadcodeurl = Contants.UPLOADCODE_URL;
        //手动清一下redis
        // RedisFactory.getPassportClient().del(MapUtils.getString(params, "authorizerappid") + "_" + "authorizeraccesstoken");
        String token = get_access_token(MapUtils.getString(params, "authorizerappid"));
        logger.info("获取的授权方的authorizeraccesstoken："+token);
        uploadcodeurl = uploadcodeurl.replace("TOKEN", token);
        //调第三方接口的参数  ext_json
        Map<String, Object> newparms = getNewParams(params);
        String jsonmap = JSONObject.fromObject(newparms).toString();
        String jsonresult = HttpClientUtil.httpsRequest(uploadcodeurl, "POST", jsonmap);
        logger.info("小程序上传代码的结果：" + jsonresult);
        //代码审核提交是否成功
        Map<String, Object> reslut = auditXcxCode(token);
        logger.info("提交审核的情况，有没有提交上：" + reslut);
        String auditerrocode = MapUtils.getString(reslut,"errcode");
        if(StringUtils.equals(Contants.AUDIT_ERROR_CODE,auditerrocode)){
            throw new ServiceException("代码已经提交审核，请不要重复提交！");
        }
        //选择模板提交审核后，保存一份记录表
        //封装要保存到表里的参数
        Map<String, Object> result = getApiResultMap(params);
        //加个判断是不是第一次发版，加上版本号查询
        zlRpcResult = apitXcxCodeModelService.getCodeModelCount(result);
        int count = (int) zlRpcResult.getData();
        logger.info("查询已经提交的审核记录的数量："+count);
        if (StringUtils.equals(MapUtils.getString(reslut, "errcode"), "0") && count<=0) {
            result.put("auditid", MapUtils.getString(reslut, "auditid"));
            apitXcxCodeModelService.addXcxCodeModelLog(result);//此时的状态是的代码已提交审核中
        }
        return ZLResult.Success();
    }


    //处理保存到数据库的参数
    public Map<String, Object> getApiResultMap(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("shopcode", MapUtils.getString(params, "shopcode"));
        result.put("authorizerappid", MapUtils.getString(params, "authorizerappid"));
        result.put("templateid", MapUtils.getString(params, "template_id"));
        result.put("userversion", MapUtils.getString(params, "user_version"));
        result.put("userdesc", MapUtils.getString(params, "user_desc"));
        return result;
    }


    //小程序代码提交审核
    public Map<String, Object> auditXcxCode(String token) {
        String auditxcxcode_url = Contants.AUDIT_XCX_CODE;
        auditxcxcode_url = auditxcxcode_url.replace("TOKEN", token);
        //参数结构
        Map<String, Object> parammap = new HashMap<>();
        List item_list = new ArrayList();
        Map<String, Object> param = new HashMap<>();
        //获取类目
        // List<Map<String,Object>> categorylist = getCategory(token);
        //获取页面配置
        //List<Map<String,Object>> pagesetlist = getPagesetSet(token);
        //获取配置参数
        getparam(param);
        item_list.add(param);
        parammap.put("item_list", item_list);
        String jsonparam = JSONObject.fromObject(parammap).toString();
        String jsonresult = HttpClientUtil.httpsRequest(auditxcxcode_url, "POST", jsonparam);
        logger.info("代码提交审核的状态：jsonresult" + jsonresult);
        Map<String, Object> resultmap = JSON.parseObject(jsonresult);
        return resultmap;
    }

    //获取新的param
    public void getparam(Map<String, Object> param) {
        param.put("address", Contants.XCX_HOME_PAGE);
        param.put("tag", Contants.TAG);
        param.put("first_class", Contants.FIRST_CLASS);
        param.put("second_class", Contants.SECOND_CLASS);
        param.put("first_id", 1);
        param.put("second_id", 2);
        param.put("title", "首页");
    }

    //获取类目
    public List<Map<String, Object>> getCategory(String token) {
        String category_url = Contants.CHOSEN_CATEGORY;
        category_url = category_url.replace("TOKEN", token);
        String jsonresult = HttpClientUtil.httpGetRequest(category_url);
        logger.info("获取的小程序的类目：" + jsonresult);
        JSONObject jsonn = JSONObject.fromObject(jsonresult);
        List<Map<String, Object>> category_list = (List<Map<String, Object>>) jsonn.get("category_list");
        return category_list;
    }

    //获取页面配置
    public List<Map<String, Object>> getPagesetSet(String token) {
        String pageset_url = Contants.PAGE_SET;
        pageset_url = pageset_url.replace("TOKEN", token);
        String jsonresult = HttpClientUtil.httpGetRequest(pageset_url);
        logger.info("获取的小程序的页面配置：" + jsonresult);
        JSONObject jsonn = JSONObject.fromObject(jsonresult);
        List<Map<String, Object>> page_list = (List<Map<String, Object>>) jsonn.get("page_list");
        return page_list;
    }

    //封装参数
    public Map<String, Object> getNewParams(Map<String, Object> params) {
        Map<String, Object> newparams = new HashMap<>();
        newparams.put("template_id", MapUtils.getString(params, "template_id"));
        newparams.put("user_version", MapUtils.getString(params, "user_version"));
        newparams.put("user_desc", MapUtils.getString(params, "user_desc"));
        //ext_json需为string类型
        Map<String, Object> extjsonmap = new HashMap<>();
        extjsonmap.put("extAppid", MapUtils.getString(params, "authorizerappid"));

        //自定义字段仅允许在这里定义，可在小程序中调用
        Map<String, Object> ext = new HashMap<>();
        ext.put("shopcode", MapUtils.getString(params, "shopcode"));

      /*  //页面配置
        Map<String,Object>  extPages = new HashMap<>();
           //index
            Map<String,Object> index = new HashMap<>();
             //........
            Map<String,Object> search_index = new HashMap<>();
            //...
        extPages.put("index",index);
        extPages.put("search/index",search_index);

          //pages 数组
        String pagesArr[] = {"index","search/index"};

        //window
        Map<String,Object> window = new HashMap<>();
        //....

        //networkTimeout
        Map<String,Object> networkTimeout = new HashMap<>();
        //....

        //tabBar
        Map<String,Object> tabBar =new HashMap<>();
        //...
*/
        extjsonmap.put("ext", ext);
      /*  extjsonmap.put("extPages",extPages);
        extjsonmap.put("pages",pagesArr);
        extjsonmap.put("window",window);
        extjsonmap.put("networkTimeout",networkTimeout);
        extjsonmap.put("tabBar",tabBar);*/
        String ext_json = JSONObject.fromObject(extjsonmap).toString();
        newparams.put("ext_json", ext_json);
        return newparams;
    }

    //查询小程序的代码模板
    public List<Map<String, Object>> getXcxCodeModel(String commontappid) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> parammap = new HashMap<>();
        //获取小程序模板的Url
        String getmodelurl = Contants.GET_XCX_MODEL;
        parammap.put("commontappid", commontappid);
        zlRpcResult = apitCsTheThirdPartService.queryComponentverifyticket(parammap);
        Map<String, Object> resultmap = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> zlwsctyList = new ArrayList<>();
        List<Map<String, Object>> zlwlcsList = new ArrayList<>();
        List<Map<String, Object>> resultlist = new ArrayList<>();
        if (zlRpcResult.success()) {
            String componentaccesstoken = MapUtils.getString(zlRpcResult.getMap(), "componentaccesstoken");
            logger.info("配置表里查询的componentaccesstoken:" + componentaccesstoken);
            getmodelurl = getmodelurl.replace("TOKEN", componentaccesstoken);
            String jsonresult = HttpClientUtil.httpGetRequest(getmodelurl);
            logger.info("查询小程序模板的结果：" + jsonresult);
            JSONObject json = JSONObject.fromObject(jsonresult);
            list = (List<Map<String, Object>>) json.get("template_list");
            //遍历list,按版本放到新的list中
            for (Map<String, Object> codemode : list) {
                String source_miniprogram_appid = MapUtils.getString(codemode, "source_miniprogram_appid");
                logger.info("开发的小程序的Appid:" + source_miniprogram_appid);
                //中仑微商城体验号
                if (StringUtils.equals(source_miniprogram_appid, Contants.ZL_WSC_TY)) {
                    zlwsctyList.add(codemode);
                }
                //中仑网络测试号
                if (StringUtils.equals(source_miniprogram_appid, Contants.ZL_WLCS)) {
                    zlwlcsList.add(codemode);

                }
            }
        }

        //根据create_time进行排序
        Collections.sort(zlwsctyList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return MapUtils.getString(o2, "create_time").compareTo(MapUtils.getString(o1, "create_time"));
            }
        });

        Collections.sort(zlwlcsList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
                return MapUtils.getString(o2, "create_time").compareTo(MapUtils.getString(o1, "create_time"));
            }
        });
        logger.info("zlwsctyList:" + zlwsctyList);
        logger.info("zlwlcsList:" + zlwlcsList);

        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        if(CollectionUtils.isNotEmpty(zlwsctyList)){
            resultlist.add(zlwsctyList.get(0));

        }
        if(CollectionUtils.isNotEmpty(zlwlcsList)){
            resultlist.add(zlwlcsList.get(0));
        }
        return resultlist;
    }


}
