package com.wgb.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2018/7/11 0011.
 */
public class HttpRequestUtil {

    /**
     * @param request
     * @return
     */
    public static String getRemoteUser(HttpServletRequest request) {
        return (String) request.getAttribute(HttpRequestConstant.CONST_LOGIN_ACCOUNT);
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getRequestHeadInfo(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getRequestCookies(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                result.put(cookie.getName(), cookie.getValue());
            }
        }
        return result;
    }

    /**
     * @param request
     * @param response
     * @param key
     */
    public static void removeRootCookies(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    /**
     * @param request
     * @param params
     */
    public static void setParams(HttpServletRequest request, Map<String, Object> params) {
        request.setAttribute(HttpRequestConstant.CONST_LOGIN_PARAMS, params);
    }

    /**
     * @param request
     */
    public static Map<String, Object> getParams(HttpServletRequest request) {
        return (Map<String, Object>) request.getAttribute(HttpRequestConstant.CONST_LOGIN_PARAMS);
    }
}
