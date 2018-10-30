package com.wgb.service.impl.admin;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.admin.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.admin.AdminUserServiceImpl.";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonDalClient dal;

    @Override
    public Map<String, Object> getLoginUser(Map<String, Object> params) {
        return dal.getDalClient().queryForMap(NAMESPACE + "getLoginUser", params);
    }
}
