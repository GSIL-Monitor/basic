package com.wgb.util;

/**
 * Created by wgb on 2018/7/26 0026.
 */
public class ServiceParams {

    private String serviceName;
    private String methodName;
    private String interfaceType;
    private String key;
    private String entry;

    public ServiceParams(String key, String serviceName, String methodName, String interfaceType, String entry) {
        this.key = key;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.interfaceType = interfaceType;
        this.entry = entry;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public boolean local() {
        return this.interfaceType.equals("local");
    }

    public boolean rpc() {
        return this.interfaceType.equals("rpc");
    }
}
