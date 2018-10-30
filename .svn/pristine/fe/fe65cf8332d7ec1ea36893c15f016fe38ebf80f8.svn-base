package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.LoginInfoService;
import com.wgb.service.dubbo.scpms.web.ApitScpLoginService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller通用父类
 */
@Controller
@Qualifier("/baseController")
public abstract class SCPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SCPBaseController.class);

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map<String, Object> getParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return HttpRequestUtil.getParams(request);
    }

    public Map<String, Object> getPubParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return HttpRequestUtil.getParams(request);
    }

    public Map<String, Object> Success(Object data) {
        ApiUtil.formatObjectForApi(data);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "1");
        result.put("data", data);
        return result;
    }

    public Map<String, Object> Success() {
        return Success(null);
    }

    public Map<String, Object> Error(String errorMsg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "0");
        result.put("errorMsg", errorMsg);
        return result;
    }

    /**
     * 返回结果为字符串类型，且字符串是一个提示语
     *
     * @param rpcResult
     * @return
     */
    public ZLResult parseRpcResultForMessage(ZLRpcResult rpcResult) {
        if (null == rpcResult) {
            LOGGER.info("调用RPC，未返回RPC调用结果！");
            return ZLResult.Error("系统异常，请联系管理员!");
        }
        ZLResult result;
        if (rpcResult.success()) {
            if (null != rpcResult.getData() && StringUtils.isNotBlank(rpcResult.getData().toString())) {
                result = ZLResult.Error(rpcResult.getData().toString());
            } else {
                result = ZLResult.Success();
            }
        } else {
            result = ZLResult.Error(rpcResult.getErrorMsg());
        }
        return result;
    }

    /**
     * 返回结果，为数据
     *
     * @param rpcResult
     * @return
     */
    public ZLResult parseRpcResultForData(ZLRpcResult rpcResult) {
        if (null == rpcResult) {
            LOGGER.info("调用RPC，未返回RPC调用结果！");
            return ZLResult.Error("系统异常，请联系管理员!");
        }
        ZLResult result;
        if (rpcResult.success()) {
            result = ZLResult.Success(rpcResult.getData());
        } else {
            result = ZLResult.Error(rpcResult.getErrorMsg());
        }
        return result;
    }
}
