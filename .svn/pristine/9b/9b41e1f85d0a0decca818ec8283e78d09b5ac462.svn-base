package com.wgb.service.impl;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.wgb.bean.LocalServiceObject;
import com.wgb.bean.RpcServiceObject;
import com.wgb.bean.ServiceObject;
import com.wgb.bean.ZLResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.DispatcherService;
import com.wgb.util.ServiceParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by wgb on 2018/7/26 0026.
 */
@Service
public class DispatcherServiceImpl implements DispatcherService, ApplicationContextAware {

    protected static final Logger logger = LoggerFactory.getLogger(DispatcherServiceImpl.class);

    private ApplicationContext applicationContext;

    private static ConcurrentMap<String, ServiceObject> serviceMap = new ConcurrentHashMap<String, ServiceObject>();

    /**
     * @return
     */
    private ServiceObject getRpcServiceObject(String serviceName) {

        ServiceObject serviceObject = serviceMap.get(serviceName);
        if (serviceObject != null) {
            return serviceObject;
        }

        ReferenceBean referenceBean = new ReferenceBean();
        referenceBean.setApplicationContext(applicationContext);
        referenceBean.setInterface(serviceName);
        try {
            referenceBean.afterPropertiesSet();
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        RpcServiceObject rpcServiceObject = new RpcServiceObject();
        rpcServiceObject.setObject(referenceBean.get());
        rpcServiceObject.setReferenceBean(referenceBean);
        rpcServiceObject.setServiceName(serviceName);
        serviceMap.put(serviceName, rpcServiceObject);
        return rpcServiceObject;
    }

    /**
     * @param serviceName
     * @return
     */
    private ServiceObject getLocalServiceObject(String serviceName) {

        ServiceObject serviceObject = serviceMap.get(serviceName);
        if (serviceObject != null) {
            return serviceObject;
        }

        Object object = applicationContext.getBean(serviceName);

        LocalServiceObject localServiceObject = new LocalServiceObject();
        localServiceObject.setObject(object);
        serviceMap.put(serviceName, localServiceObject);
        return localServiceObject;
    }

    /**
     * @param serviceParams
     * @return
     */
    private ServiceObject getServiceObject(ServiceParams serviceParams) {
        String serviceName = serviceParams.getServiceName();
        if (serviceParams.local()) {
            return getLocalServiceObject(serviceName);
        } else if (serviceParams.rpc()) {
            return getRpcServiceObject(serviceName);
        } else {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    /**
     * @param serviceParams
     * @param params
     * @return
     */
    @Override
    public ZLResult execute(ServiceParams serviceParams, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        //获取服务对象
        ServiceObject serviceObject = getServiceObject(serviceParams);
        if (serviceObject == null) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        String methodName = serviceParams.getMethodName();
        return serviceObject.invokeMethod(methodName, params, request, response);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
