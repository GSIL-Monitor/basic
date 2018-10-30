package com.wgb.service.impl.portal;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.portal.PortalUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2018/3/16 0016.
 */
@Service
public class PortalUserRoleServiceImpl implements PortalUserRoleService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.portal.PortalUserRoleServiceImpl.";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonDalClient dal;

    @Override
    public List<Map<String, Object>> getUserRoleList(Map<String, Object> params) {
        return dal.getDalClient().queryForList(NAMESPACE + "getUserRoleList", params);
    }
}
