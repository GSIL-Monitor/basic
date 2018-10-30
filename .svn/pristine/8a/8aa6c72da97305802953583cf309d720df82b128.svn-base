package com.wgb.bean;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by wgb on 2018/7/26 0026.
 */
public class RpcServiceObject implements ServiceObject{

    private String serviceName;
    private ReferenceBean referenceBean;
    private Object object;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ReferenceBean getReferenceBean() {
        return referenceBean;
    }

    public void setReferenceBean(ReferenceBean referenceBean) {
        this.referenceBean = referenceBean;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * @param params
     * @param request
     * @param response
     * @return
     */
    public ZLResult invokeMethod(String methodName, Map<String, Object> params,HttpServletRequest request, HttpServletResponse response) {
        Method method = null;
        try {
            method = this.object.getClass().getDeclaredMethod(methodName, Map.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object result = null;
        try {
            result = method.invoke(this.object, params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        ZLRpcResult rpcResult = (ZLRpcResult) result;
        if (!rpcResult.success()) {
            throw new ServiceException(rpcResult.getErrorCode());
        }
        return ZLResult.Success(rpcResult.getData());
    }
}
