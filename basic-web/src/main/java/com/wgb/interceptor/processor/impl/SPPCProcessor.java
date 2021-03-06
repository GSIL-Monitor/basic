package com.wgb.interceptor.processor.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.dubbo.scpms.web.ApitScpLoginService;
import com.wgb.service.dubbo.srvms.web.ApitSrvUserService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.HttpRequestConstant;
import com.wgb.util.HttpRequestUtil;
import com.wgb.util.ParamsUtil;
import com.wgb.util.SystemConfig;
import com.wgb.util.SystemStartEnv;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by yjw on 2018/8/8 11609.
 */
public class SPPCProcessor extends AdapterProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private ApitSrvUserService apitSrvUserService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        String test = MapUtils.getString(params, HttpRequestConstant.ZL_REQUEST_TEST, "");
        String account;
        if (StringUtils.isNotEmpty(test) && !SystemConfig.SYSTEM_START_ENV.equalsIgnoreCase(SystemStartEnv.prod.toString())) {
            account = test;
        } else {
            //cookie
            Map<String, Object> cookieMap = HttpRequestUtil.getRequestCookies(request);
            //token
            String token = MapUtils.getString(cookieMap, HttpRequestConstant.SP_TOKEN_ID);
            if (StringUtils.isEmpty(token)) {
                token = MapUtils.getString(params, "token");
                if (StringUtils.isEmpty(token)) {
                    throw new ServiceException(ServiceException.SESSION_TIME_OUT);
                }
            }
            // 获取account
            account = RedisFactory.getPassportClient().get(HttpRequestConstant.SP_PC_ACCOUNT_PREFIX + token, SESSION_TIME_SECONDS);
            if (StringUtils.isEmpty(account)) { // redis中没有查询到 account 信息 ,清除
                HttpRequestUtil.removeRootCookies(request, response, HttpRequestConstant.SP_TOKEN_ID);
                throw new ServiceException(ServiceException.SESSION_TIME_OUT);
            }
        }
        params.put("account", account);
        // 校验account，获取登录用户信息
        ZLRpcResult rpcResult = apitSrvUserService.getCurUserInfo(params);
        // 当前用户是否有权限访问

        Map<String, Object> user = rpcResult.getMap();
        if (MapUtils.isEmpty(user)) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        user.put("account" ,account);
        HttpRequestUtil.setParams(request, getParams(request, user));
        return true;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getParams(HttpServletRequest request, Map<String, Object> userInfo) {
        Map<String, Object> requestParams = ParamsUtil.handleServletParameter(request);
        requestParams.put(Contants.USER_IP, CommonUtil.getRemortIP(request));
        requestParams.put(Contants.LOGIN_USER_ID, MapUtils.getString(userInfo, "id"));
        requestParams.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(userInfo, "fullname"));
        requestParams.put(Contants.LOGIN_USER_SERVER_CODE, MapUtils.getString(userInfo, "servercode"));
        requestParams.put(Contants.LOGIN_USER_SERVER_NAME, MapUtils.getString(userInfo, "servername"));
        requestParams.put(Contants.LOGIN_USER_IS_REQUIRED, MapUtils.getString(userInfo, "required"));
        requestParams.put(Contants.LOGIN_USER_SERVER_ACCOUNT, MapUtils.getString(userInfo, "account"));
        return requestParams;
    }
}
