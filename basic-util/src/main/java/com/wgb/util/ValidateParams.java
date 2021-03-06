package com.wgb.util;

import com.wgb.exception.ServiceException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by wfy on 2018/9/27.
 */
public  class ValidateParams
{
    /**
     *  这个是参数校验的方法
     */
    public static void query_validateParams(Map<String, Object> params) {
        String shopcode = MapUtils.getString(params, "shopcode");
        String industryid = MapUtils.getString(params, "industryid");
        if (StringUtils.isEmpty(shopcode) || StringUtils.isEmpty(industryid)) {
            throw new ServiceException("参数缺失! param missing!");
        }
    }

    /**
     *  这个是参数校验的方法:校验spucode必传
     */
    public static void query_validateParamsSPU(Map<String, Object> params) {
        String shopcode = MapUtils.getString(params, "shopcode");
        String industryid = MapUtils.getString(params, "industryid");
        String spucode = MapUtils.getString(params, "spucode");
        if (StringUtils.isEmpty(shopcode) || StringUtils.isEmpty(industryid) || StringUtils.isEmpty(spucode)) {
            throw new ServiceException("参数缺失! param missing!");
        }
    }
    /**
     *  这个是参数校验的方法:替换orderby的值
     *  排序规则（0：asc正序， 1：desc倒序）（默认为不排序
     */
    public static void query_validateParamsOderKey(Map<String, Object> params) {
        //参数校验
        String orderdesc = MapUtils.getString(params, "orderdesc");
        if (StringUtils.isNotEmpty(orderdesc)) {
            //asc正序
            if(orderdesc.equals(Contants.STR_ZERO)){
                orderdesc="ASC";
            }else {
                orderdesc="DESC";
            }
            params.put("orderdesc", orderdesc.trim());
        }
    }
}
