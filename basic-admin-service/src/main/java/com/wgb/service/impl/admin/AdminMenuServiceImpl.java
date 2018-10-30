package com.wgb.service.impl.admin;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.admin.AdminMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.admin.AdminMenuServiceImpl.";

    @Autowired
    private CommonDalClient dal;

    @Override
    public List<Map<String, Object>> getMenuByUserId(String userid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        List<Map<String, Object>> menusList = dal.getDalClient().queryForList(NAMESPACE + "getMenuByUserId", params);
        if (CollectionUtils.isNotEmpty(menusList)) {
            List<String> menuIds = new ArrayList<String>();
            for (Map<String, Object> menusObj : menusList) {
                String menus = MapUtils.getString(menusObj, "menus", "");
                String[] menuArr = menus.split(",");
                for (String menuId : menuArr) {
                    if (StringUtils.isNotEmpty(menuId) && !menuIds.contains(menuId)) {
                        menuIds.add(menuId);
                    }
                }
            }

            params.put("menuIds", menuIds);
            return dal.getDalClient().queryForList(NAMESPACE + "getMenuListByIds", params);
        }
        return null;
    }
}
