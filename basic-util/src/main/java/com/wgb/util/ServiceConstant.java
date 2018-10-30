package com.wgb.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2018/7/26 0026.
 */
public class ServiceConstant {

    private static Map<String, ServiceParams> serviceParamsMap = new HashMap<String, ServiceParams>();

    static {
        addItem("zl.retail.commodity.brand.select", "com.wgb.service.dubbo.dcms.web.ApitBrandService", "selectBrand", "rpc", "0");

        addItem("zl.retail.commodity.brand.test", "cacheServiceImpl", "test", "local", "0");
    }

    private static void addItem(String key, String serviceName, String methodName, String interfaceType, String entry) {
        serviceParamsMap.put(key, new ServiceParams(key, serviceName, methodName, interfaceType, entry));
    }

    public static ServiceParams getServiceParams(String key) {
        return serviceParamsMap.get(key);
    }

    public static boolean entry(String key) {
        ServiceParams serviceParams = serviceParamsMap.get(key);
        if (serviceParams != null) {
            return serviceParams.getEntry().equals("1");
        }
        return false;
    }
}
