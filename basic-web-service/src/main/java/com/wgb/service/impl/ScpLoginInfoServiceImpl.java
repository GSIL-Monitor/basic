package com.wgb.service.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.LoginInfoService;
import com.wgb.service.ScpLoginInfoService;
import com.wgb.service.ShopService;
import com.wgb.service.dubbo.basic.web.PortalBasicService;
import com.wgb.service.dubbo.scpms.web.ApitScpLoginService;
import com.wgb.util.CacheConstant;
import com.wgb.util.CommonUtil;
import com.wgb.util.HttpRequestConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yjw on 2018/8/8
 */
@Service
public class ScpLoginInfoServiceImpl implements ScpLoginInfoService {

    @Autowired
    private ApitScpLoginService apitScpLoginService;

    @Override
    public Map<String, Object> getRemoteUserInfo(String account) {
        //存储在缓存服务器上的key
        String userKey = "scp_user_" + account;
        Map<String, Object> userInfo = null;

        //从统一redis服务器获取用户数据
        userInfo = RedisFactory.getPassportClient().getMapFromJedis(userKey);

        //如果用户不存在
        if (MapUtils.isEmpty(userInfo)) {

            //通过统一数据服务器获取用户数据
            Map<String, Object> params = new HashMap<>();
            params.put("account", account);
            ZLRpcResult rpcResult = apitScpLoginService.getRemoteUserInfo(params);
            userInfo = rpcResult.getMap();

            //如果统一数据服务器不存在该数据
            if (MapUtils.isEmpty(userInfo)) {
                return null;
            }

            //更新用户数据到redis中
            RedisFactory.getPassportClient().setMapToJedis(userInfo, userKey);
        }

        return userInfo;
    }

}
