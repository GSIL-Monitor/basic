package com.wgb.interceptor.processor.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.LoginInfoService;
import com.wgb.util.HttpRequestConstant;
import com.wgb.util.MD5Util;
import com.wgb.util.ParamsUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wgb on 2018/7/11 0011.
 */
public class MTWXProcessor extends AdapterProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private LoginInfoService loginInfoService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (checkAuth(request.getRequestURI())) {
            String token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                // 需要授权未授权，去授权
                response.sendRedirect(getAuthUrl(request));
            } else {
                //account
                String account = RedisFactory.getPassportClient().get(HttpRequestConstant.MB_WX_ACCOUNT_PREFIX + token, SESSION_TIME_SECONDS);
                if (account == null) {
                    // 过期，去授权
                    response.sendRedirect(getAuthUrl(request));
                } else {
                    if (MD5Util.GetMD5Code(account + getHost(request)).equals(token)) {
                        return true;
                    } else {
                        // 用户地址发生过变更，废弃原token，重新授权
                        RedisFactory.getPassportClient().del(HttpRequestConstant.MB_WX_ACCOUNT_PREFIX + token);
                        response.sendRedirect(getAuthUrl(request));
                    }
                }

            }
        }
        return true;
    }

    private String getAuthUrl(HttpServletRequest request) {
        // TODO 获取授权url;
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        return "";
    }

    private String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断请求的路径是否需要鉴权
     *
     * @param uri
     * @return
     */
    private boolean checkAuth(String uri) {
        if (uri.contains("/auth")) {
            return false;
        }
        return true;
    }
}
