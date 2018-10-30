package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.scpms.web.ApitSupplierOrderService;
import com.wgb.service.dubbo.stms.web.ApitPurchaseOrderOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/purchase/orderonline")
public class MTPurchaseOrderOnlineController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseOrderOnlineController.class);

    @Autowired
    private ApitPurchaseOrderOnlineService apitPurchaseOrderOnlineService;

    @Autowired
    private ApitSupplierOrderService apitSupplierOrderService;

    /**
     * 网上订单-采购订单首页查询
     */
    @RequestMapping("/purchaseOrderOnlineListDetail")
    @ResponseBody
    public ZLResult purchaseOrderOnlineListDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderOnlineService.purchaseOrderOnlineListDetail(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 网上订单-采购订单详情
     */
    @RequestMapping("/getOrderInfoForPurchase")
    @ResponseBody
    public ZLResult getOrderInfoForPurchase(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitSupplierOrderService.getOrderInfoForPurchase(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 网上订单-选择采购单据
     *
     * @param request
     * @return
     */
    @RequestMapping("/orderOnlineToChoose")
    @ResponseBody
    public ZLResult orderOnlineToChoose(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderOnlineService.ordertoChoose(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 网上订单-采购选择全部供应商
     *
     * @param request
     * @return
     */
    @RequestMapping("/selectSupplierOnline")
    @ResponseBody
    public ZLResult selectSupplierOnline(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderOnlineService.selectSupplierOnline(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

}
