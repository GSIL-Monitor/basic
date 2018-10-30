package com.wgb.controller.sp;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.srvms.web.ApitSrvUserService;
import com.wgb.util.HttpRequestConstant;
import com.wgb.util.HttpRequestUtil;
import com.wgb.util.MD5Util;
import com.wgb.util.UUIDGenerator;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fxs
 * @create 2018-10-09 14:17
 **/
@Controller
@RequestMapping("srv/entry")
public class SPSrvLoginController extends SPBaseController{

    @Autowired
    private ApitSrvUserService apitSrvUserService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SPSrvLoginController.class);

    /**
     *  用户登录
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ZLResult login(HttpServletRequest request ,HttpServletResponse response){
        Map<String, Object> params = getParams();
        String type = MapUtils.getString(params, "type");
        Map<String ,Object> userInfoParams = new HashMap<>();
        if (StringUtils.isNotEmpty(type)) { // 快捷登录
            userInfoParams.put("account", MapUtils.getString(params, "account",""));
            userInfoParams.put("password", MapUtils.getString(params, "password1",""));
            Object obj = quickLogin(userInfoParams, response);
            if (obj instanceof  Map){
                return ZLResult.Success((Map<String ,Object>)obj);
            }
            return ZLResult.Error(obj.toString());
        }
        // 普通登录
        userInfoParams.put("username", MapUtils.getString(params, "username",""));
        userInfoParams.put("password", MapUtils.getString(params, "password2",""));
        userInfoParams.put("servercode", MapUtils.getString(params, "serverid",""));
        Object obj = normalLogin(userInfoParams, response);
        if (obj instanceof  Map){
            return ZLResult.Success((Map<String ,Object>)obj);
        }
        return ZLResult.Error(obj.toString());
    }

    /**
     *  退出登录
     * @param request
     * @return
     */
    @RequestMapping("/loginOut")
    @ResponseBody
    public ZLResult loginOut(HttpServletRequest request ,HttpServletResponse response){
        Map<String, Object> cookieMap = HttpRequestUtil.getRequestCookies(request);
        // 删除cookie
        String token = MapUtils.getString(cookieMap, HttpRequestConstant.SP_TOKEN_ID);
        HttpRequestUtil.removeRootCookies(request, response, HttpRequestConstant.SP_TOKEN_ID);
        //  删除redis
        RedisFactory.getPassportClient().expire(HttpRequestConstant.SP_PC_ACCOUNT_PREFIX + token, 0);
        return ZLResult.Success();
    }

    /**
     * 普通登录
     * @param params
     * @return
     */
    private Object quickLogin(Map<String ,Object> params ,HttpServletResponse resp){
        boolean checkResult =  checkLoginParams(params);
        if (!checkResult){ return "用户名或密码不能为空!";}
        // 获取快捷登录用户信息
        Map<String ,Object> userInfo = getLoginUserInfo(params);
        // 校验用户，放置用户account进redis中
        String message = setUserInfoToRedis(userInfo ,resp);
        if (StringUtils.isBlank(message)){return userInfo;}
        return message;
    }

    /**
     * 普通登录
     * @param params
     * @return
     */
    private Object normalLogin(Map<String ,Object> params ,HttpServletResponse resp){
        boolean checkResult =  checkLoginParams(params);
        if (!checkResult){return "用户名或密码或服务商ID不能为空!";}
        // 获取普通登录登录用户信息
        Map<String ,Object> userInfo = getLoginUserInfo(params);
        // 校验用户，放置用户account进redis中
        String message = setUserInfoToRedis(userInfo ,resp);
        if (StringUtils.isBlank(message)){return userInfo;}
        return message;
    }

    /**
     * 设置帐号toRedis
     * @param userInfo
     * @param resp
     * @return
     */
    private String setUserInfoToRedis(Map<String, Object> userInfo, HttpServletResponse resp) {
        // 校验用户
        String message = checkUserInfo(userInfo);
        if (StringUtils.isNotBlank(message)){return message;}
        // 生成token 放置redis
        String account = MapUtils.getString(userInfo, "account");
        String token = UUIDGenerator.getUUID();

        Cookie cookie = new Cookie(HttpRequestConstant.SP_TOKEN_ID, token);
        cookie.setMaxAge(2592000);
        cookie.setPath("/");
        resp.addCookie(cookie);
        RedisFactory.getPassportClient().set(HttpRequestConstant.SP_PC_ACCOUNT_PREFIX + token, account);
        return null;
    }

    /**
     * 校验登录用户
     * @param userInfo
     * @return
     */
    private String checkUserInfo(Map<String, Object> userInfo) {
        if (MapUtils.isEmpty(userInfo)) {
            return  "账号不存在或密码错误";
        }
        String flag = MapUtils.getString(userInfo, "flag");
        if (flag.equals("0")) {
            return "账号已被禁用!";
        }
        return null;
    }

    /**
     * 校验登录用户帐号密码
     * @param params
     * @return
     */
    private boolean checkLoginParams(Map<String, Object> params) {
        String account = MapUtils.getString(params, "account" ,"");
        String password = MapUtils.getString(params, "password","");
        String username = MapUtils.getString(params, "username","");
        String servercode = MapUtils.getString(params, "servercode","");
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            return true;
        }else if(StringUtils.isNotBlank(servercode) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            return true;
        }
        return false;
    }

    /**
     * 获取登录用户信息
     * @param params
     * @return
     */
    private Map<String ,Object> getLoginUserInfo(Map<String, Object> params) {
        // 密码加密
        params.put("password" ,MD5Util.GetMD5Code(MapUtils.getString(params,"password")));
        ZLRpcResult rpcResult = apitSrvUserService.getLoginUserInfo(params);
        if (rpcResult.success()){
            return rpcResult.getMap();
        }
        LOGGER.error("查询登录用户信息系统异常！", rpcResult.getErrorMsg());
        throw new ServiceException("查询登录用户信息系统异常");
    }
}
