package com.wgb.service.impl.admin;

import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.admin.AdminMenuService;
import com.wgb.service.admin.AdminUserService;
import com.wgb.service.dubbo.basic.admin.AdminBasicService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/9/18 0018.
 */
@Service
public class AdminBasicServiceImpl implements AdminBasicService {

    public static final Logger logger = LoggerFactory.getLogger(AdminBasicServiceImpl.class);

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminMenuService adminMenuService;

    @Override
    public ZLRpcResult getUserInfo(String account) {

        ZLRpcResult rpcResult = new ZLRpcResult();

        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("account", account);
        Map<String, Object> userInfo = adminUserService.getLoginUser(p1);
        if (MapUtils.isEmpty(userInfo)) {
            return rpcResult;
        }
        rpcResult.setData(userInfo);
        return rpcResult;
    }

    @Override
    public ZLRpcResult getMenuList(String account) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("account", account);
        Map<String, Object> userInfo = adminUserService.getLoginUser(p1);
        if (MapUtils.isEmpty(userInfo)) {
            return rpcResult;
        }
        String userId = MapUtils.getString(userInfo, "id", "");
        List<Map<String, Object>> menuList = adminMenuService.getMenuByUserId(userId);
        rpcResult.setData(menuList);
        return rpcResult;
    }
}
