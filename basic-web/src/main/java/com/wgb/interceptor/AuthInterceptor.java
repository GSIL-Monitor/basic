package com.wgb.interceptor;

import com.wgb.interceptor.processor.AuthProcessor;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private List<AuthProcessor> processors;

    public static final String[] whites = {"/entry/"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String uri = request.getRequestURI();
        String  scheme = request.getScheme();
        String  serverName = request.getServerName();
        String userIp = CommonUtil.getRemortIP(request);
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        logger.info("客户端IP：" + userIp + "，访问链接："+ scheme + "//" + serverName + uri + "，请求参数：" + params);

        //判断是否需要鉴权，兼容entry路径
        if (!isNeedCheckLogin(uri)) {
            HttpRequestUtil.setParams(request, params);
            return true;
        }

        //判断访问接口是否为entry接口
        if (isEntryService(request)) {
            HttpRequestUtil.setParams(request, params);
            return true;
        }

        //找到对应的处理器，处理响应的请求
        for (AuthProcessor processor : processors) {
            if (processor.adapter(request, response) || processor.adapterSign(request,response)) {
                return processor.process(request, response);
            }
        }

        return false;
    }

    /**
     * @param uri
     * @return
     */
    private boolean isNeedCheckLogin(String uri) {
        if (whites == null || whites.length == 0) {
            return true;
        }
        for (String white : whites) {
            if (uri.indexOf(white) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param request
     * @return
     */
    private boolean isEntryService(HttpServletRequest request) {
        Map<String, Object> variables = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String key = MapUtils.getString(variables, "key", "");
        return ServiceConstant.entry(key);
    }

    public void setProcessors(List<AuthProcessor> processors) {
        this.processors = processors;
    }
}