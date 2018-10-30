package com.wgb.service.portal;

import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2018/3/17 0017.
 */
public interface PortalRoleService {
    List<Map<String,Object>> getShopRoleList(Map<String, Object> params);
}
