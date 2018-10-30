package com.wgb.service.impl.portal;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.CacheDataService;
import com.wgb.service.portal.PortalMenuService;
import com.wgb.util.Contants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortalMenuServiceImpl implements PortalMenuService,CacheDataService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.portal.PortalMenuServiceImpl.";

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

            //获取所有菜单列表
            List<Map<String, Object>> menuList = dal.getDalClient().queryForList(NAMESPACE + "getMenuList", params);

            List<String> resultIds = new ArrayList<String>();
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

            //初始化菜单Map
            Map<String, Map<String, Object>> menuMap = new HashMap<String, Map<String, Object>>();
            for (Map<String, Object> menu : menuList) {
                menuMap.put(MapUtils.getString(menu, "id"), menu);
            }

            //遍历权限菜单ids
            for (String menuId : menuIds) {

                //获取菜单及其所有父节点
                String menuid = menuId;
                while (StringUtils.isNotEmpty(menuid) && !menuid.equals(Contants.NODE_PARENT_PID)) {

                    Map<String, Object> menu = menuMap.get(menuid);

                    if (!resultIds.contains(menuid)) {
                        resultList.add(menu);
                        resultIds.add(menuid);
                    }

                    menuid = MapUtils.getString(menu, "pid");
                }
            }

            //菜单按照ordernum排序
            Collections.sort(resultList, new IntegerComparator());

            return resultList;
        }
        return null;
    }

    @Override
    public Map<String, Object> getCacheMap(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getCacheList(Map<String, Object> params) {
        return dal.getDalClient().queryForList(NAMESPACE + "getCacheList",params);
    }

    // 自定义比较器：按书出版时间来排序
    static class IntegerComparator implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            Map<String, Object> p1 = (Map<String, Object>) object1; // 强制转换
            Map<String, Object> p2 = (Map<String, Object>) object2;
            Integer ordernum1 = MapUtils.getInteger(p1, "ordernum", 0);
            Integer ordernum2 = MapUtils.getInteger(p2, "ordernum", 0);
            return ordernum1.compareTo(ordernum2);
        }
    }
}
