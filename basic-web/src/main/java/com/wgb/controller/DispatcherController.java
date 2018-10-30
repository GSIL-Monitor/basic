
package com.wgb.controller;

import com.wgb.bean.ZLResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.DispatcherService;
import com.wgb.util.HttpRequestUtil;
import com.wgb.util.ServiceConstant;
import com.wgb.util.ServiceParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wgb on 2018/7/26 0026.
 */

@Controller
@RequestMapping("/")
public class DispatcherController {

    @Autowired
    private DispatcherService dispatcherService;

    /**
     * 统一服务处理实现类
     *
     * @param request
     * @return
     */

    @RequestMapping("/{key}/{version}/action")
    @ResponseBody
    public ZLResult service(@PathVariable("key") String key, @PathVariable("version") String version, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = HttpRequestUtil.getParams(request);
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(version)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ServiceParams serviceParams = ServiceConstant.getServiceParams(key);
        if (serviceParams == null) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        return dispatcherService.execute(serviceParams, params, request, response);
    }
}
