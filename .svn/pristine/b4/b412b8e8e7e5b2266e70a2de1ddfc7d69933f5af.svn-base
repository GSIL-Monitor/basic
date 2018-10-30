package com.wgb.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ParamsUtil {

    /**
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> handleServletParameter(HttpServletRequest request) {
        Map<String, String[]> requestParameter = request.getParameterMap();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.putAll(requestParameter);

        Map<String, Object> rParams = new HashMap<String, Object>(0);
        for (Map.Entry<String, Object> m : parameter.entrySet()) {
            String key = m.getKey();
            Object[] obj = (Object[]) parameter.get(key);
            rParams.put(key, (obj.length > 1) ? obj : obj[0]);
        }
        return rParams;
    }
}
