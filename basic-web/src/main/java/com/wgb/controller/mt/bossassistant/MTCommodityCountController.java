package com.wgb.controller.mt.bossassistant;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitBossAssistantService;
import com.wgb.service.dubbo.urms.web.ApitBranchService;
import com.wgb.service.dubbo.urms.web.ApitPortalUserService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 老板助手-商品数据统计
 * Created by lzy on 2018/8/6.
 */
@Controller
@RequestMapping("/boss/commodityCount")
public class MTCommodityCountController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTCommodityCountController.class);

    @Autowired
    private ApitBossAssistantService apitBossAssistantService;

    /**
     * 老板助手-商品数据统计-销售信息列表-查询ABC类
     * @param request
     * @return
     * @date 2018/10/23
     */
    @RequestMapping("/queryForABC")
    @ResponseBody
    public ZLResult queryForABC(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryForABC(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 老板助手-商品数据统计-销售信息列表-查询ABC类之一的所有类别
     * @param request
     * @return
     * @date 2018/10/23
     */
    @RequestMapping("/queryCategoryByABC")
    @ResponseBody
    public ZLResult queryCategoryByABC(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryCategoryByABC(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 老板助手-商品数据统计-销售信息列表--查询某一类别下销售的商品信息
     * @param request
     * @return
     * @date 2018/10/23
     */
    @RequestMapping("/queryCommodityByCategory")
    @ResponseBody
    public ZLResult queryCommodityByCategory(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryCommodityByCategory(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 按销售额查询
     * @param request
     * @return
     */
    @RequestMapping("/queryByPaysubtotal")
    @ResponseBody
    public ZLResult queryByPaysubtotal(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryByPaysubtotal(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 按库存占比查询
     * @param request
     * @return
     */
    @RequestMapping("/queryByStorageRate")
    @ResponseBody
    public ZLResult queryByStorageRate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryByStorageRate(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 按库存周转查询
     * @param request
     * @return
     */
    @RequestMapping("/queryByStorageChange")
    @ResponseBody
    public ZLResult queryByStorageChange(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryByStorageChange(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 商品定位分类
     * @param request
     * @return
     */
    @RequestMapping("/queryByCommodityCategory")
    @ResponseBody
    public ZLResult queryByCommodityCategory(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitBossAssistantService.queryByCommodityCategory(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
