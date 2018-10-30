package com.wgb.service.impl.portal;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.portal.PortalRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2018/3/17 0017.
 */
@Service
public class PortalRoleServiceImpl implements PortalRoleService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.portal.PortalRoleServiceImpl.";

    @Autowired
    private CommonDalClient dal;

    @Override
    public List<Map<String, Object>> getShopRoleList(Map<String, Object> params) {
        return dal.getDalClient().queryForList(NAMESPACE + "getShopRoleList", params);
    }
}
