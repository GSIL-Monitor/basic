package com.wgb.util;

import com.wgb.dao.Page;
import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/3/21.
 */
public class ApiUtil {

    /**
     * 通用API返回数据格处理通用方法
     *
     * @param object
     */
    public static void formatObjectForApi(Object object) {

        if (object == null) {
            return;
        }

        if (object instanceof Map) {
            formatMapForApi((Map<String, Object>) object);
        } else if (object instanceof List) {
            for (Map<String, Object> item : (List<Map<String, Object>>) object) {
                formatMapForApi(item);
            }
        } else if (object instanceof Page) {
            formatObjectForApi(((Page) object).getList());
        }
    }

    /**
     * 通用API返回数据处理map
     *
     * @param map
     */
    private static void formatMapForApi(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        for (String key : map.keySet()) {

            Object val = map.get(key);
            if (val != null && val instanceof Map) {
                formatObjectForApi(val);
            } else if (val != null && val instanceof List) {
                formatObjectForApi(val);
            } else if (val != null && val instanceof Page) {
                formatObjectForApi(val);
            } else {
                map.put(key, MapUtils.getString(map, key, ""));
            }
        }
    }
}
