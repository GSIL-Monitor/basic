package com.wgb.service.impl;

import com.wgb.dao.CommonDalClient;
import com.wgb.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/10/15 0015.
 */
@Service
public class BranchServiceImpl implements BranchService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.BranchServiceImpl.";

    @Autowired
    private CommonDalClient dal;

    @Override
    public List<Map<String, Object>> queryBranchList(Map<String, Object> params) {
        return dal.getDalClient().queryForList(NAMESPACE + "getBranchList", params);
    }

    @Override
    public Map<String, Object> queryBranchInfo(Map<String, Object> params) {
        return dal.getDalClient().queryForMap(NAMESPACE + "queryBranchInfo", params);
    }
}
