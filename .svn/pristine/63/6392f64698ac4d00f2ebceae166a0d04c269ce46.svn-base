package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseReceiptOnlineService;
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
@RequestMapping("/purchase/receiptonline")
public class MTPurchaseReceiptOnlineController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseReceiptOnlineController.class);

    @Autowired
    private ApitPurchaseReceiptOnlineService apitPurchaseReceiptOnlineService;

    /**
     * 网上订单-采购收货首页查询
     */
    @RequestMapping("/purchaseReceiptOnlineDetail")
    @ResponseBody
    public ZLResult purchaseReceiptOnlineDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.purchaseReceiptDetail(params);
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

    @RequestMapping("/purchaseReceiptOnlineAddOrUpdate")
    @ResponseBody
    public ZLResult purchaseReceiptOnlineAddOrUpdate(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.purchaseReceiptAddOrUpdate(params);
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

    @RequestMapping("/purchaseReceiptOnlineToUpdateDetail")
    @ResponseBody
    public ZLResult purchaseReceiptOnlineToUpdateDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.purchaseReceiptToUpdateDetail(params);
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

    @RequestMapping("/showReceiptGoodsOnlineByOrder")
    @ResponseBody
    public ZLResult showReceiptGoodsOnlineByOrder(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.showReceiptGoodsByOrder(params);
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

    @RequestMapping("/purchaseReceiptOnlineExamine")
    @ResponseBody
    public ZLResult purchaseReceiptOnlineExamine(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.purchaseReceiptExamine(params);
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

    @RequestMapping("/purchaseReceiptOnlineDel")
    @ResponseBody
    public ZLResult purchaseReceiptOnlineDel(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.purchaseReceiptDel(params);
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

    @RequestMapping("/chooseReceiptOnline")
    @ResponseBody
    public ZLResult chooseReceiptOnline(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseReceiptOnlineService.ChooseReceipt(params);
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
