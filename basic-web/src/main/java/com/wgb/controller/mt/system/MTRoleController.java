package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.urms.web.ApitPortalRoleService;
import com.wgb.util.CacheConstant;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/system/role")
public class MTRoleController extends MTBaseController {

    @Autowired
    private ApitPortalRoleService apitPortalRoleService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalRoleService.query(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalRoleService.select(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalRoleService.update(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalRoleService.saveOrUpdate(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/delRole")
    @ResponseBody
    public ZLResult delRole(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalRoleService.delRole(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/getRoleList")
    @ResponseBody
    public ZLResult getRoleList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalRoleService.getRoleList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    private ZLResult isSuccess(ZLRpcResult rpcResult) {
        boolean success = rpcResult.success();
        if (success) {
            return ZLResult.Success();
        } else {
            return ZLResult.Error(rpcResult.getErrorMsg());
        }
    }
}
