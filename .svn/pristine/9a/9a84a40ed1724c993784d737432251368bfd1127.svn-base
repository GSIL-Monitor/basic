package com.wgb.interceptor;

import com.wgb.exception.ServiceException;
import com.wgb.service.camera.CameraService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.ParamsUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CameraInterceptor extends HandlerInterceptorAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String[] whites = {"/camera/"};

    @Autowired
    private CameraService cameraService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String uri = request.getRequestURI();
        String  scheme = request.getScheme();
        String  serverName = request.getServerName();
        String userIp = CommonUtil.getRemortIP(request);
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        logger.info("客户端IP：" + userIp + "，访问链接："+ scheme + "//" + serverName + uri + "，请求参数：" + params);

        if (isNeedCheckLogin(uri)) {
            return true;
        }
        String accessToken = cameraService.queryCameraAccessToken();
        if (StringUtils.isNotBlank(accessToken)){
            request.setAttribute(Contants.MONITOR_ACCESS_TOKEN,accessToken);
            return true;
        }
        throw new ServiceException(ServiceException.OPER_ERROR);
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

}