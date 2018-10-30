package com.wgb.service.impl.portal;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.portal.PortalUserRoleService;
import com.wgb.service.portal.PortalUserService;
import com.wgb.util.CommonUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortalUserServiceImpl implements PortalUserService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.portal.PortalUserServiceImpl.";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonDalClient dal;

    @Autowired
    private PortalUserRoleService portalUserRoleService;

    @Override
    public Map<String, Object> getLoginUser(Map<String, Object> params) {
        Map<String, Object> userInfo = dal.getDalClient().queryForMap(NAMESPACE + "getLoginUser", params);
        if (MapUtils.isEmpty(userInfo)) {
            return null;
        }
        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("shopcode", MapUtils.getString(userInfo, "shopcode"));
        p1.put("userid", MapUtils.getString(userInfo, "id"));

        List<Map<String, Object>> userRoleList = portalUserRoleService.getUserRoleList(p1);
        userInfo.put("roleids", CommonUtil.getStringFromListMap(userRoleList, "roleid"));
        userInfo.put("rolenames", CommonUtil.getStringFromListMap(userRoleList, "rolename"));
        return userInfo;
    }

    @Override
    public String getAccountByToken(String token) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return dal.getDalClient().queryForObject(NAMESPACE + "getAccountByToken", params, String.class);
    }
}
