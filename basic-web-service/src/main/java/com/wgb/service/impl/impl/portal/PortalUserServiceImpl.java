package com.wgb.service.impl.impl.portal;

import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.basic.web.PortalBasicService;
import com.wgb.service.portal.PortalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PortalUserServiceImpl implements PortalUserService {

    @Autowired
    private PortalBasicService portalBasicService;

    @Override
    public Map<String, Object> getLoginUser(Map<String, Object> params) {
        ZLRpcResult rpcResult = portalBasicService.getUserInfo(params);
        if(rpcResult.getData() == null){
            return  new HashMap<String,Object>();
        }
        return rpcResult.getMap();
    }
}
