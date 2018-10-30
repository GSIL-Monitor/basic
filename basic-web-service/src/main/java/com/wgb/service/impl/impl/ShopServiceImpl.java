package com.wgb.service.impl.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.dao.CommonDalClient;
import com.wgb.service.ShopService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2018/3/16 0016.
 */
@Service
public class ShopServiceImpl implements ShopService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.ShopServiceImpl.";

    @Autowired
    private CommonDalClient dal;

    @Override
    public Map<String, Object> getRemoteShopInfo(Map<String, Object> user) {

        String shopcode = MapUtils.getString(user, "shopcode");

        //存储在缓存服务器上的key
        String cacheKey = "Shop_" + shopcode;
        Map<String, Object> shopInfo = null;

        //从统一redis服务器获取用户数据
        shopInfo = RedisFactory.getPassportClient().getMapFromJedis(cacheKey);

        //如果用户不存在
        if (MapUtils.isEmpty(shopInfo)) {

            Map<String, Object> p1 = new HashMap<String, Object>();
            p1.put("shopcode", shopcode);

            //通过统一数据服务器获取商户信息
            shopInfo = getShopInfo(p1);

            //如果统一数据服务器不存在该数据
            if (MapUtils.isEmpty(shopInfo)) {
                return null;
            }

            //更新用户数据到redis中
            RedisFactory.getPassportClient().setMapToJedis(shopInfo, cacheKey);
        }

        return shopInfo;
    }

    @Override
    public Map<String, Object> getShopInfo(Map<String, Object> params) {
        return dal.getDalClient().queryForMap(NAMESPACE + "getShopInfo", params);
    }
}
