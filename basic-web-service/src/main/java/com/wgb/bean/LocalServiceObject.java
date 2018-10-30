package com.wgb.bean;

import com.wgb.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by wgb on 2018/7/30 0030.
 */
public class LocalServiceObject implements ServiceObject {

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public ZLResult invokeMethod(String methodName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        Method method = null;
        try {
            method = this.object.getClass().getDeclaredMethod(methodName, Map.class, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object result;
        try {
            result = method.invoke(this.object, params, request, response);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return (ZLResult) result;
    }
}
