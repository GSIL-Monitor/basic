package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseReturnOnlineService;
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
@RequestMapping("/purchase/returnonline")
public class MTPurchaseReturnOnlineController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseReturnOnlineController.class);

    @Autowired
    private ApitPurchaseReturnOnlineService apitPurchaseReturnOnlineService;

    /**
     * 网上订单-采购退货首页查询
     */
    @RequestMapping("/purchaseReturnOnlineDetail")
    @ResponseBody
    public ZLResult purchaseReturnOnlineDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReturnOnlineService.purchaseReturnDetail(params);
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


    @RequestMapping("/purchaseReturnOnlineAddOrUpdate")
    @ResponseBody
    public ZLResult purchaseReturnOnlineAddOrUpdate(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReturnOnlineService.purchaseReturnAddOrUpdate(params);
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

    @RequestMapping("/purchaseReturnOnlineDel")
    @ResponseBody
    public ZLResult purchaseReturnOnlineDel(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseReturnOnlineService.purchaseReturnDel(params);
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

    @RequestMapping("/purchaseReturnOnlineToUpdateDetail")
    @ResponseBody
    public ZLResult purchaseReturnOnlineToUpdateDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseReturnOnlineService.purchaseReturnToUpdateDetail(params);
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

    @RequestMapping("/showReturnGoodsOnlineByReceipt")
    @ResponseBody
    public ZLResult showReturnGoodsOnlineByReceipt(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseReturnOnlineService.showReturnGoodsByReceipt(params);
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


    @RequestMapping("/purchaseReturnOnlineExamine")
    @ResponseBody
    public ZLResult purchaseReturnOnlineExamine(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseReturnOnlineService.purchaseReturnExamine(params);
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
