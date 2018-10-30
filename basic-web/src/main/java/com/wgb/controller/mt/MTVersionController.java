package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiVersionService;
import com.wgb.util.ApiUtil;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2017/3/20.
 */
@Controller
@RequestMapping("/version")
public class MTVersionController extends MTBaseController{

    @Autowired
    private ApiVersionService apiVersionService;

    @RequestMapping("/entry/getNewest")
    @ResponseBody
    public Object getNewest(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        ZLRpcResult rpcResult = null;
        try {
            rpcResult = apiVersionService.getNewest(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!rpcResult.success()){
            return FailResult(rpcResult.getErrorCode());
        }

        return RpcSuccessResult(rpcResult);
    }

    @RequestMapping("/entry/getActNewest")
    @ResponseBody
    public Object getActNewest(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = null;
        try {
            rpcResult = apiVersionService.getActNewest(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!rpcResult.success()){
            return FailResult(rpcResult.getErrorCode());
        }
        return RpcSuccessResult(rpcResult);
    }

    private Object RpcSuccessResult(ZLRpcResult rpcResult) {
        Object data = null;
        if (rpcResult != null) {
            data = rpcResult.getData();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "1");
        result.put("result", data);
        return getFinalResult(result);
    }

    private Object FailResult(int errorCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "0");
        result.put("errcode", errorCode);
        result.put("errmsg", ServiceException.getEnMsg(errorCode));
        return getFinalResult(result);
    }

    private Object getFinalResult(Map<String, Object> result) {
        ApiUtil.formatObjectForApi(result);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String callback = request.getParameter("callback");
        if (StringUtils.isEmpty(callback)) {
            return result;
        } else {
            return new JSONPObject(callback, result);
        }
    }
}
