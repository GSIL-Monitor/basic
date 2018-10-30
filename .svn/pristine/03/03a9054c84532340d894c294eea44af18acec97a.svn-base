package com.wgb.interceptor.processor.impl;

import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.LoginInfoService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.HttpRequestUtil;
import com.wgb.util.ParamsUtil;
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
public class LMXCXProcessor extends AdapterProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginInfoService loginInfoService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) {

        //token必须提供
        String accessCode = request.getParameter("access_code");
        if (StringUtils.isEmpty(accessCode)) {
            throw new ServiceException("登陆过期");
        }

        Map<String, Object> userInfo = loginInfoService.getLmsUserInfo(accessCode);

        if (MapUtils.isEmpty(userInfo)) {
            throw new ServiceException("登陆过期");
        }
        HttpRequestUtil.setParams(request, getParams(request, userInfo));

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
        //登陆者所属商户编码
        requestParams.put(Contants.LOGIN_USER_SHOP_CODE, MapUtils.getString(userInfo, "shopcode"));
        //登陆者所属门店编码
        requestParams.put(Contants.LOGIN_USER_BRANCH_CODE, MapUtils.getString(userInfo, "branchcode"));
        //登陆者openid
        requestParams.put(Contants.LOGIN_SCP_XCX_USER_OPENID, MapUtils.getString(userInfo, "openid"));
        return requestParams;
    }
}
