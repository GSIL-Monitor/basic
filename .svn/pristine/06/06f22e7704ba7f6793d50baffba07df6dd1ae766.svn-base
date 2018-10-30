package com.wgb.util;

import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ParamsUtil {

    private static final String PAGE = "page";
    private static final String PAGESIZE = "pageSize";

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getDefaultParams(HttpServletRequest request) {

        Map<String, Object> rParams = handleServletParameter(request);

        if (StringUtils.isEmpty(rParams.get(PAGE) == null ? null : rParams.get(PAGE).toString())) {
            rParams.put(PAGE, Contants.PAGE_START);

        } else {
            rParams.put(PAGE, MapUtils.getIntValue(rParams, PAGE, Contants.PAGE_START));
        }
        if (StringUtils.isEmpty(rParams.get(PAGESIZE) == null ? null : rParams.get(PAGESIZE).toString())) {
            rParams.put(PAGESIZE, Contants.PAGE_SIZE);
        } else {
            rParams.put(PAGESIZE, MapUtils.getIntValue(rParams, PAGESIZE, Contants.PAGE_SIZE));
        }

        //登陆者的IP
        rParams.put(Contants.USER_IP, CommonUtil.getRemortIP(request));
        return rParams;
    }

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

    /**
     * 设置门店参数
     * @param params
     */
    public static void setNewBranchParams(Map<String, Object> params) {
        String loginuesrishead=MapUtils.getString(params,"loginuserbranchishead");
        String loginuserbranchcode=MapUtils.getString(params,"loginuserbranchcode");
        if(StringUtils.equals(loginuesrishead,"0")){
            params.put("branchcode",loginuserbranchcode);
        }
    }
}
