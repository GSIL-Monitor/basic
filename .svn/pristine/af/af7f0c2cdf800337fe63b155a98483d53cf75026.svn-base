package com.wgb.interceptor.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wgb on 2018/7/11 0011.
 */
public interface AuthProcessor {

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    boolean adapter(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    boolean adapterSign(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
