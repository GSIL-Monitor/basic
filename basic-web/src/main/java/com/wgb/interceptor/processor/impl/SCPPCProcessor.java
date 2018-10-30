package com.wgb.interceptor.processor.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.dubbo.scpms.web.ApitScpLoginService;
import com.wgb.util.*;
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
public class SCPPCProcessor extends AdapterProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private ApitScpLoginService apitScpLoginService;

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
            String token = MapUtils.getString(cookieMap, HttpRequestConstant.SCP_TOKEN_ID);

            if (StringUtils.isEmpty(token)) {
                token = MapUtils.getString(params, "token");
                if (StringUtils.isEmpty(token)) {
                    throw new ServiceException(ServiceException.SESSION_TIME_OUT);
                }
            }

            //account
            account = RedisFactory.getPassportClient().get(HttpRequestConstant.SCP_PC_ACCOUNT_PREFIX + token, SESSION_TIME_SECONDS);
            if (StringUtils.isEmpty(account)) {
                HttpRequestUtil.removeRootCookies(request, response, HttpRequestConstant.SCP_TOKEN_ID);
                throw new ServiceException(ServiceException.SESSION_TIME_OUT);
            }
        }
        params.put("account", account);
        //判断account是否正确
        ZLRpcResult rpcResult = apitScpLoginService.getLoginUser(params);
        Map<String, Object> user = rpcResult.getMap();
        if (MapUtils.isEmpty(user)) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        HttpRequestUtil.setParams(request, getParams(request, user));

        return true;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getParams(HttpServletRequest request, Map<String, Object> userInfo) {
        Map<String, Object> requestParams = ParamsUtil.handleServletParameter(request);

        //登陆者的IP
        requestParams.put(Contants.USER_IP, CommonUtil.getRemortIP(request));

        //登陆者的ID
        requestParams.put(Contants.LOGIN_USER_ID, MapUtils.getString(userInfo, "id"));

        //登陆者所属供应商的编码
        requestParams.put(Contants.LOGIN_USER_SCP_CODE, MapUtils.getString(userInfo, "scpcode"));

        //登陆者用户名称
        requestParams.put(Contants.LOGIN_USER_NAME, MapUtils.getString(userInfo, "contact"));

        //登陆者的账号
        requestParams.put(Contants.LOGIN_USER_ACCOUNT, MapUtils.getString(userInfo, "account"));

        //登陆者的供应商名称
        requestParams.put(Contants.LOGIN_USER_SCP_NAME, MapUtils.getString(userInfo, "scpname"));

        return requestParams;
    }

}
