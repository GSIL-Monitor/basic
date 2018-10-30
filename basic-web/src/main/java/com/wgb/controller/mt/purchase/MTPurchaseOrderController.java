package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseOrderService;
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
@RequestMapping("/purchase/order")
public class MTPurchaseOrderController extends MTBaseController {
    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseOrderController.class);

    @Autowired
    private ApitPurchaseOrderService apitPurchaseOrderService;

    /**
     * 零售2.0
     */
    @RequestMapping("/purchaseOrderListDetail")
    @ResponseBody
    public ZLResult manage_detail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseOrderService.purchaseOrderListDetail(params);
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

    @RequestMapping("/purchaseOrderUpdateDetail")
    @ResponseBody
    public ZLResult purchaseUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderService.purchaseOrderUpdateDetail(params);
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


    @RequestMapping("/purchaseOrderAddOrUpdate")
    @ResponseBody
    public ZLResult addOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderService.purchaseOrderAddOrUpdate(params);
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

    @RequestMapping("/purchaseOrderExamine")
    @ResponseBody
    public ZLResult purchaseOrderExamine(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderService.examinePurchaseOrder(params);
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

    @RequestMapping("/purchaseOrderDel")
    @ResponseBody
    public ZLResult purchaseOrderDel(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderService.deletePurchaseOrder(params);
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

    @RequestMapping("/ordertoChoose")
    @ResponseBody
    public ZLResult ordertoChoose(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseOrderService.ordertoChoose(params);
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
