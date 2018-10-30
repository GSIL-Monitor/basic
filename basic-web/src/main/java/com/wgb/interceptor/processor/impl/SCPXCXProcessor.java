package com.wgb.interceptor.processor.impl;

import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.dubbo.scpms.web.ApitScpXcxUserService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by yjw on 2018/8/8 11609.
 */
public class SCPXCXProcessor extends AdapterProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitScpXcxUserService apitScpXcxUserService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        //判断account是否正确
        ZLRpcResult rpcResult = apitScpXcxUserService.getXcxUserInfo(params);
        Map<String, Object> user = rpcResult.getMap();
        if (MapUtils.isEmpty(user)) {
            throw new ServiceException(ServiceException.SESSION_TIME_OUT);
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

        //登陆者所属供应商的编码
        requestParams.put(Contants.LOGIN_SCP_XCX_USER_CODE, MapUtils.getString(userInfo, "usercode"));

        //登陆者用户名称
        requestParams.put(Contants.LOGIN_SCP_XCX_USER_OPENID, MapUtils.getString(userInfo, "openid"));

        //登陆者的账号
        requestParams.put(Contants.LOGIN_SCP_XCX_USER_TELEPHONE, MapUtils.getString(userInfo, "telephone"));

        //登陆者所属商户的编码
        requestParams.put(Contants.LOGIN_USER_SHOP_CODE, MapUtils.getString(userInfo, "shopcode"));

        //登陆者所属的门店编码
        requestParams.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(userInfo, "branchcode"));

        //登陆者的门店名称
        requestParams.put(Contants.LOGIN_USER_BRANCH_NAME, MapUtils.getString(userInfo, "branchname"));

        //登陆者的商户名称
        requestParams.put(Contants.LOGIN_USER_SHOP_NAME, MapUtils.getString(userInfo, "shopname"));

        //登陆者的供应商类型
        requestParams.put(Contants.LOGIN_SCP_XCX_USER_TYPE, MapUtils.getString(userInfo, "usertype"));

        return requestParams;
    }

}
