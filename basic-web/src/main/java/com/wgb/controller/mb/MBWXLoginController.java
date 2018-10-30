package com.wgb.controller.mb;

import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.pays.admin.ApiPayService;
import com.wgb.service.dubbo.urms.admin.ApiShopService;
import com.wgb.service.dubbo.wxms.web.ApitCsAuthorService;
import com.wgb.service.dubbo.wxms.web.ApitCsTheThirdPartService;
import com.wgb.service.dubbo.wxms.web.ApitVisitLogService;
import com.wgb.service.dubbo.wxms.web.ApitWxShopMemberService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2018/8/6 0006.
 */
@Controller
@RequestMapping("/login")
public class MBWXLoginController extends MBBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitWxShopMemberService apitWxShopMemberService;

    @Autowired
    private ApiPayService apiPayService;

    @Autowired
    private ApiShopService apiShopService;

    @Autowired
    private ApitVisitLogService apitVisitLogService;
    @Autowired
    private ApitCsAuthorService apitCsAuthorService;

    @Autowired
    private ApitCsTheThirdPartService apitCsTheThirdPartService;
    /**
     * @param params
     * @return
     */
    private Map<String, Object> getMemberInfo(Map<String, Object> params) {

        String code = MapUtils.getString(params, "code");
        String shopcode = MapUtils.getString(params, "shopcode");
        /*ZLRpcResult rpcResult = apiPayService.getPayConfig(params);
        Map<String, Object> payConfig = rpcResult.getMap();
        String wxPubAppId = MapUtils.getString(payConfig, "wxpubappid");
        String wxPubAppSecret = MapUtils.getString(payConfig, "wxpubappsecret");*/
        //获取公众号appid
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("shopcode", shopcode);
        param.put("flag", Contants.COMPONENT_TYPE_GZH);
        ZLRpcResult rpcResult = apitCsAuthorService.queryGzhMsg(param);
        Map<String, Object> authorMsg = rpcResult.getMap();
        String authorizerAppid = MapUtils.getString(authorMsg, "authorizerappid");
        logger.info("查询微信系统查询公众号的appid,位置getMemberInfo方法："+authorizerAppid);

        Map<String, Object> memberInfo = null;

        //必须配置微信公众号参数
        if (StringUtils.isNotEmpty(authorizerAppid) /*&& StringUtils.isNotEmpty(wxPubAppSecret)*/) {

            logger.info("授权公众号的AppId:" + authorizerAppid );

            // 获取网页授权的access_token
            Map<String, Object> weixinOauth2Token = getAuthorizer2AccessToken(authorizerAppid, code);

            logger.info("获取access_token map为:" + weixinOauth2Token);

            if (MapUtils.isNotEmpty(weixinOauth2Token)) {

                // 用户标识
                String openId = MapUtils.getString(weixinOauth2Token, "openid");

                logger.info("获取openid:" + openId);

                Map<String, Object> p1 = new HashMap<String, Object>();
                p1.put("openid", openId);
                p1.put("shopcode", shopcode);

                // 根据shopcode和membercode查询用户数据库
                ZLRpcResult rpcResult1 = apitWxShopMemberService.getLoginMemberInfo(p1);
                memberInfo = rpcResult1.getMap();

                logger.info("从shopmemeber表里查询的用户:" + memberInfo);

                if (MapUtils.isEmpty(memberInfo)) {

                    // 网页授权接口访问凭证
                    String accessToken = MapUtils.getString(weixinOauth2Token, "access_token");
                    // 获取用户信息
                    memberInfo = WeixinCommon.getUserInfo(accessToken, openId);

                    logger.info("从微信第三方接口查询的用户:" + memberInfo);
                    if (MapUtils.isNotEmpty(memberInfo)) {
                        memberInfo.put("shopcode", shopcode);
                        ZLRpcResult rpcResult2 = apitWxShopMemberService.saveLoginMemberInfo(memberInfo);
                        memberInfo = rpcResult2.getMap();
                        logger.info("微信第三方获取的用户信息已经保存到shopmember表里:" + memberInfo);
                    }
                }
            }
        }

        return memberInfo;
    }

    //前端判断完需要走微信授权调用
    @RequestMapping("/entry/towxlogin")
    public void towxlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("进入授权步骤！");
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String source = MapUtils.getString(params, "source");
        logger.info("   获取微信授权的参数！" + params);
        String redirectUri = CommonUtil.getHttpFullUrl(request, request.getContextPath() + "/login/entry/wxlogin.action?shopcode=" + shopcode + "&redirect=" + URLEncoder.encode(source, "UTF-8"));

        //String  redirectUri = CommonUtil.getHttpFullUrl(request, request.getContextPath() + "/login/entry/wxlogin.action?shopcode=" + shopcode + "&redirect=" + URLEncoder.encode(source, "UTF-8"));
        logger.info("重定向的redirectUri：" + redirectUri);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("shopcode", shopcode);
       /*   ZLRpcResult rpcResult = apiPayService.getPayConfig(param);
        Map<String, Object> payConfig = rpcResult.getMap();
        logger.info("没有获取到用户信息，准备走重定向获取的payconfig:" + payConfig);
        String wxPubAppId = MapUtils.getString(payConfig, "wxpubappid");
        String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxPubAppId + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") + "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
*/

        param.put("flag",Contants.COMPONENT_TYPE_GZH);
        logger.info("towxlogin  queryGzhMsg" + param);
        ZLRpcResult rpcResult = apitCsAuthorService.queryGzhMsg(param);
        Map<String, Object> authorMsg = rpcResult.getMap();
        String authorizerAppid = MapUtils.getString(authorMsg, "authorizerappid");
        logger.info("查询微信系统查询公众号的appid"+authorizerAppid);
        String oauth2Url = Contants.GET_CODE;
        oauth2Url= oauth2Url.replace("SCOPE","snsapi_userinfo");
        oauth2Url = oauth2Url.replace("AUTHAPPID",authorizerAppid);
        oauth2Url =oauth2Url.replace("REDIRECT_URI",URLEncoder.encode(redirectUri, "UTF-8"));
        oauth2Url = oauth2Url.replace("COMPONENT_APPID",Contants.COMMONT_APPID);
        logger.info("没有获取到用户信息，准备走重定向url(开放平台):" + oauth2Url);
        response.sendRedirect(oauth2Url);
        return;
    }

    //重定向进入到这步
    @RequestMapping("/entry/wxlogin")

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> params = getPubParams();
        String code = MapUtils.getString(params, "code");
        String shopcode = MapUtils.getString(params, "shopcode");
        String redirect = MapUtils.getString(params, "redirect");
        logger.info("授权成功前的重定向路径：" + redirect);
        logger.info("微信客户端登录接口，code:" + code);
        logger.info("微信客户端登录接口，shopcode:" + shopcode);
        logger.info("微信客户端登录接口，redirect:" + redirect);

        if (StringUtils.isEmpty(shopcode) || StringUtils.isEmpty(redirect)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            Map<String, Object> memberInfo = null;

            logger.info("微信客户端登录接口，获取会员信息");
            try {
                memberInfo = getMemberInfo(params);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }

            logger.info("微信客户端登录接口获取会员信息---用户信息为空“抛操作异常”：" + memberInfo);

            if (MapUtils.isEmpty(memberInfo)) {
                throw new ServiceException(ServiceException.OPER_ERROR);
            } else {

                ZLRpcResult rpcResult = apiShopService.queryShopByShopCode(params);
                logger.info("调用RPC查出的信息，调用失败则报“系统异常”：" + rpcResult.getMap());
                logger.info("rpcResult.success():" + rpcResult.success());
                if (rpcResult.success()) {
                    Map<String, Object> shopInfo = rpcResult.getMap();
                    memberInfo.put("commoditymode", MapUtils.getString(shopInfo, "commoditymode"));
                    memberInfo.put("membermode", MapUtils.getString(shopInfo, "membermode"));
                    memberInfo.put("shopname", MapUtils.getString(shopInfo, "shopname"));
                } else {
                    throw new ServiceException(ServiceException.SYS_ERROR);
                }

                String openid = MapUtils.getString(memberInfo, "openid");
                logger.info("走授权的时候，往cookie存的值openid:" + openid);
                //添加sid到服务根路径
                Cookie cookie = new Cookie(HttpRequestConstant.MB_TOKEN_ID, openid);
                cookie.setMaxAge(2592000);
                cookie.setPath("/");
               // cookie.setDomain("wxscpre.zhonglunnet.com");
                response.addCookie(cookie);
                logger.info("走授权的时候，往cookie存的值mb_id:" + cookie.getName());

                //前端判断"shopcode_" + openid取到的shopcode和url传的shopcode是否一致，不一致说
                //明切换公众号了走授权接口
                cookie = new Cookie("shopcode_" + openid, shopcode);
                cookie.setMaxAge(2592000);
                cookie.setPath("/");
                //cookie.setDomain("wxscpre.zhonglunnet.com");
                logger.info("走授权的时候，往cookie存的值shop_opendid的值:" + cookie.getValue());
                response.addCookie(cookie);

                //String membercode = MapUtils.getString(memberInfo, "membercode");
                //String account = shopcode + "_" + membercode;
                //logger.info("走授权的时候，生成的account:"+account);
                //RedisFactory.getPassportClient().set(HttpRequestConstant.MB_WX_ACCOUNT_PREFIX + token, account);

                //用户授权登录后，判断一下是否有相同的unoid的用户且为会员的
                logger.info("用户登录授权后准备走check");
                if(StringUtils.equals(MapUtils.getString(memberInfo,"whthermember"),"0")){
                    checkWhetherMember(memberInfo);
                }
                logger.info("用户登录授权后走checkc成功");
                // 记录访问日志
                Map<String, Object> logParams = new HashMap<>();
                logParams.put("shopcode", MapUtils.getString(memberInfo, "shopcode"));
                logParams.put("openid", MapUtils.getString(memberInfo, "openid"));
                apitVisitLogService.addLog(logParams);

                //这是我们在页面看到的请求
                logger.info("授权成功后的重定向路径：" + redirect);
                response.sendRedirect(redirect);
            }
        } else {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }

    }
      public void checkWhetherMember(Map<String,Object> params){
          ZLRpcResult zlRpcResult = new ZLRpcResult();
          //用户的openid
          String openid = MapUtils.getString(params,"openid");
          logger.info("要给自己同步会员的openid:"+openid);
          String unionid = MapUtils.getString(params,"unionid");
          logger.info("要用来查询对应用户的unionid:"+unionid);
          Map<String,Object> param = new HashMap<>();
          param.put("shopcode",MapUtils.getString(params,"shopcode"));
          param.put("unionid",unionid);
          if(StringUtils.isNotEmpty(unionid)){
              zlRpcResult =  apitWxShopMemberService.queryShopMemberByUnionid(param);
              logger.info("查询对应unionid已经是会员的用户信息:"+zlRpcResult.getMap());
              Map<String,Object> shopmember = zlRpcResult.getMap();
              if(zlRpcResult.success() && MapUtils.isNotEmpty(shopmember)){
                  //把电话和是否是会员字段更新
                  shopmember.put("openid",openid);
                  logger.info("准备更新自己的会员信息："+shopmember);
                  apitWxShopMemberService.updateShopMemberByUnionid(shopmember);
                  logger.info("更新自己成为会员成功");
              }
          }
      }

    //授权进行重定向
    @RequestMapping("/entry/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> params = getParams();
        String bandtype = MapUtils.getString(params, "bandtype");
        Map<String, Object> resultmap = new HashMap<>();
        String shopcode = MapUtils.getString(params, "shopcode");
        resultmap.put("commontappid", Contants.COMMONT_APPID);
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //预授权码
        zlRpcResult = apitCsTheThirdPartService.queryComponentverifyticket(resultmap);
        logger.info("授权的时候查的配置表：" + zlRpcResult);
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        String redirect_uri = SystemConfig.WX_THIRDPLATFORM_CALLBCK;
        redirect_uri = redirect_uri.replace("pshopcode", shopcode);
        redirect_uri = redirect_uri.replace("pbandtype", bandtype);
        try {
            redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String pre_auth_code = MapUtils.getString(zlRpcResult.getMap(), "preauthcode");
        request.setAttribute("componentappid",Contants.COMMONT_APPID);
        request.setAttribute("preauthcode", pre_auth_code);
        request.setAttribute("redirecturi", redirect_uri);
        request.setAttribute("authtype", bandtype);
        return "jumpPage" ;
    }
}
