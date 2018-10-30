package com.wgb.service.impl.portal;

import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.BranchService;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.basic.web.PortalBasicService;
import com.wgb.service.portal.PortalMenuService;
import com.wgb.service.portal.PortalRoleService;
import com.wgb.service.portal.PortalUserService;
import com.wgb.util.CacheConstant;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/1/19.
 */
@Service
public class PortalBasicServiceImpl implements PortalBasicService {

    public static final Logger logger = LoggerFactory.getLogger(PortalBasicServiceImpl.class);

    @Autowired
    private PortalUserService portalUserService;

    @Autowired
    private PortalMenuService portalMenuService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private PortalRoleService portalRoleService;

    @Autowired
    private CacheService cacheService;

    @Override
    public ZLRpcResult getUserInfo(String account) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("account", account);
        Map<String, Object> userInfo = portalUserService.getLoginUser(p1);
        if (MapUtils.isEmpty(userInfo)) {
            return rpcResult;
        }
        rpcResult.setData(userInfo);
        return rpcResult;
    }

    @Override
    public ZLRpcResult getBranchInfo(Map<String, Object> params) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        try {
            Map<String, Object> branchInfo = branchService.queryBranchInfo(params);
            rpcResult.setData(branchInfo);

        } catch (ServiceException e) {
            // 异常处理
            logger.error("查询门店信息处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(e.getCode());
        } catch (Exception e) {
            // 异常处理
            logger.error("查询门店信息处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(ServiceException.SYS_ERROR);
        }

        return rpcResult;
    }

    @Override
    public ZLRpcResult getShopRoleList(Map<String, Object> params) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        try {
            List<Map<String, Object>> roleList = portalRoleService.getShopRoleList(params);
            rpcResult.setData(roleList);

        } catch (ServiceException e) {
            // 异常处理
            logger.error("查询门店角色列表处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(e.getCode());
        } catch (Exception e) {
            // 异常处理
            logger.error("查询门店角色列表处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(ServiceException.SYS_ERROR);
        }

        return rpcResult;
    }

    @Override
    public ZLRpcResult getUserInfo(Map<String, Object> params) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        try {
            Map<String, Object> userInfo = portalUserService.getLoginUser(params);
            rpcResult.setData(userInfo);

        } catch (ServiceException e) {
            // 异常处理
            logger.error("查询用户信息处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(e.getCode());
        } catch (Exception e) {
            // 异常处理
            logger.error("查询用户信息处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(ServiceException.SYS_ERROR);
        }

        return rpcResult;
    }

    @Override
    public ZLRpcResult getMenuList(String account) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("account", account);
        Map<String, Object> userInfo = portalUserService.getLoginUser(p1);
        if (MapUtils.isEmpty(userInfo)) {
            return rpcResult;
        }
        String userId = MapUtils.getString(userInfo, "id", "");
        List<Map<String, Object>> menuList = portalMenuService.getMenuByUserId(userId);
        rpcResult.setData(menuList);
        return rpcResult;
    }

    @Override
    public ZLRpcResult getAccountByToken(String token) {

        ZLRpcResult rpcResult = new ZLRpcResult();

        try {
            String account = portalUserService.getAccountByToken(token);
            rpcResult.setData(account);

        } catch (ServiceException e) {
            // 异常处理
            logger.error("查询account处理异常，输入参数 token =" + token, e);
            rpcResult.setErrorCode(e.getCode());
        } catch (Exception e) {
            // 异常处理
            logger.error("查询account处理异常，输入参数 token =" + token, e);
            rpcResult.setErrorCode(ServiceException.SYS_ERROR);
        }

        return rpcResult;
    }

    @Override
    public ZLRpcResult getAllMenuList(Map<String, Object> params) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        try {
            List<Map<String, Object>> menuList = cacheService.getCacheList(CacheConstant.CACHE_PORTAL_MENU_LIST);
            rpcResult.setData(menuList);

        } catch (ServiceException e) {
            // 异常处理
            logger.error("查询所有菜单处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(e.getCode());
        } catch (Exception e) {
            // 异常处理
            logger.error("查询所有菜单处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(ServiceException.SYS_ERROR);
        }

        return rpcResult;
    }

    @Override
    public ZLRpcResult getLoginInfo(Map<String, Object> params) {
        return new ZLRpcResult();
    }
}
