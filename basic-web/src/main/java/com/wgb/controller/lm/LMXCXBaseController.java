package com.wgb.controller.lm;

import com.wgb.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * By chmin
 */
@Controller
@Qualifier("baseController")
public class LMXCXBaseController {

    public Map<String, Object> getParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> params = HttpRequestUtil.getParams(request);
        return params;
    }

    public Map<String, Object> getPubParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> params = HttpRequestUtil.getParams(request);
        return params;
    }
}
