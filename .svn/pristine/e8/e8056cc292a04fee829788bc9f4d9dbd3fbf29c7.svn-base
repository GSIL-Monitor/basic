package com.wgb.controller.mb;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.pays.admin.ApiXcxConfigService;
import com.wgb.service.dubbo.wxms.web.*;
import com.wgb.util.Contants;
import com.wgb.util.HttpClientUtil;
import com.wgb.util.HttpRequestConstant;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
@Controller
@RequestMapping("/xcxsc/login")
public class MBXCXLoginController extends MBXCXBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiXcxConfigService apiXcxConfigService;

    @Autowired
    private ApitXCXMemberService apitLoginMemberService;

    @Autowired
    private ApitWxShopMemberService apitWxShopMemberService;

    @Autowired
    private ApitWxMemberService memberService;

    @Autowired
    private ApitCsAuthorService apitCsAuthorService;

    @Autowired
    private ApitCsTheThirdPartService apitCsTheThirdPartService;

    /**
     * 获取xcxopenid  ，登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/entry/getUserMemberInfo")
    @ResponseBody
    public Object getUserMemberInfo(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        String js_code = MapUtils.getString(params, "code");
        ZLRpcResult rpcResult = null;
      /*  try {
            //查询微信配置信息
            rpcResult = apiXcxConfigService.queryConfig(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!rpcResult.success()) {
            return ZLResult.Error("这人写的接口调不通了,就是这个人!!!");
        }
        Map<String, Object> xcxConfig = rpcResult.getMap();
        if (MapUtils.isEmpty(xcxConfig)) {
            return ZLResult.Error("请联系运营同学配置微信参数!");
        }
        String appid = MapUtils.getString(xcxConfig, "wxpubappid");
        String secret = MapUtils.getString(xcxConfig, "wxpubappsecret");
        String js_code = MapUtils.getString(params, "code");

        //访问微信获取openid
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + js_code +
                "&grant_type=authorization_code";*/
        try {
            //查询小程序微信配置信息
            params.put("flag", Contants.COMPONENT_TYPE_XCX);
            rpcResult = apitCsAuthorService.queryGzhMsg(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!rpcResult.success()) {
            return ZLResult.Error("这人写的接口调不通了,就是这个人!!!");
        }
        Map<String, Object> authorMsg = rpcResult.getMap();
        if (MapUtils.isEmpty(authorMsg)) {
            return ZLResult.Error("请联系相关人员，完成小程序授权!");
        }
        try {
            //查询第三方平台相关信息
            params.put("commontappid",Contants.COMMONT_APPID);
            rpcResult = apitCsTheThirdPartService.queryComponentverifyticket(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!rpcResult.success()) {
            return ZLResult.Error("这人写的接口调不通了,就是这个人,打死他!!!");
        }
        Map<String, Object> thirdpartmsg = rpcResult.getMap();
        if (MapUtils.isEmpty(authorMsg)) {
            return ZLResult.Error("请联系相关人员，完成小程序授权!");
        }
        String requestUrl = Contants.JSCODE_SESSION;
        //小程序的AppID
        requestUrl = requestUrl.replace("AUTHAPPID",MapUtils.getString(authorMsg,"authorizerappid"));
        //登录时获取的 code
        requestUrl = requestUrl.replace("JSCODE",js_code);
        //第三方平台appid
        requestUrl = requestUrl.replace("COMPONENT_APPID",Contants.COMMONT_APPID);
        //第三方平台的 component_access_token
        requestUrl = requestUrl.replace("ACCESS_TOKEN",MapUtils.getString(thirdpartmsg,"componentaccesstoken"));
        String httpResult = HttpClientUtil.httpGetRequest(requestUrl);
        JSONObject data = JSONObject.fromObject(httpResult);
        String xcxopenid = MapUtils.getString(data, "openid");
        if (StringUtils.isEmpty(xcxopenid)) {
            return ZLResult.Error("权限验证失败,请联系客服!");
        }
        params.put("xcxopenid", xcxopenid);
        params.put("unionid", MapUtils.getString(data, "unionid"));
        ZLRpcResult rpcResult1 = apitLoginMemberService.getLoginMemberInfo(params);
        if (rpcResult1.success()) {
        Map<String, Object> userInfo = rpcResult1.getMap();
        userInfo.put("xcxopenid", xcxopenid);
        userInfo.put("unionid",MapUtils.getString(data, "unionid"));
        return userInfo;
    } else {
        throw new ServiceException(rpcResult1.getErrorCode());
    }
}

    /**
     *  点击个人中心调用更新用户数据
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/entry/login")
    @ResponseBody
    public ZLResult login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> params = getPubParams();
        //获取微信用户信息jsonString
        String user = MapUtils.getString(params, "userInfo");
        if (StringUtils.isEmpty(user)) {
            throw new ServiceException("没有获取用户数据");
        }

        ZLRpcResult rpcResult = apitLoginMemberService.updateMemberInfo(params);
        if (rpcResult.success()) {
            Map<String, Object> userInfo = rpcResult.getMap();
            //更新redis中的用户信息
            updaterMemberInfoInRedis(params);

            return ZLResult.Success(userInfo);
        } else {
            throw new ServiceException(rpcResult.getErrorCode());
        }
    }


    /**
     * 更新用户门店
     *
     * @param request
     * @return
     */
    @RequestMapping("/entry/updateBranch")
    @ResponseBody
    public ZLResult updateBranch(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        apitLoginMemberService.updateBranch(params);
        return ZLResult.Success();
    }

    /**
     * 绑定手机后 会员激活调用该接口获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ZLResult getUserInfo(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitLoginMemberService.getUserInfo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 发送验证码
     *
     * @param request
     * @return
     */
    @RequestMapping("/entry/sendYzm")
    @ResponseBody
    public ZLResult sendYzm(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitLoginMemberService.sendYzm(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 微信用户绑定手机号
     *
     * @param request
     * @return
     */
    @RequestMapping("/bandMobile")
    @ResponseBody
    public ZLResult bandMobile(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
             zlRpcResult = apitLoginMemberService.bandMobile(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        try {
            zlRpcResult = memberService.insertmember(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        updaterMemberInfoInRedis(params);
        memberService.synchronizeGzhMsg(params);
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 更新redis中的用户信息
     */
    public void updaterMemberInfoInRedis(Map<String,Object> params){
        String xcxopenid = MapUtils.getString(params, "xcxopenid");

        //存储在缓存服务器上的key
        String userKey = HttpRequestConstant.MB_XCX_USR_PREFIX + xcxopenid;
        Map<String, Object> memberInfo = new HashMap<String, Object>();

        //通过统一数据服务器获取用户数据
        ZLRpcResult rpcResult = apitWxShopMemberService.getLoginMemberInfo(params);
        memberInfo = rpcResult.getMap();

        //如果统一数据服务器不存在该数据
        if (MapUtils.isEmpty(memberInfo)) {

        }
        //更新用户数据到redis中
        RedisFactory.getPassportClient().setMapToJedis(memberInfo, userKey);
    }


}
