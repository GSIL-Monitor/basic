package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseReceiptService;
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
@RequestMapping("/purchase/receipt")
public class MTPurchaseReceiptController extends MTBaseController {
    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseReceiptController.class);
    @Autowired
    private ApitPurchaseReceiptService apitPurchaseReceiptService;

    /**
     *零售2.0
     */
    @RequestMapping("/purchaseReceiptDetail")
    @ResponseBody
    public ZLResult receipt_manage_detail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.purchaseReceiptDetail(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/purchaseReceiptAddOrUpdate")
    @ResponseBody
    public ZLResult addOrUpdate(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.purchaseReceiptAddOrUpdate(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/purchaseReceiptToUpdateDetail")
    @ResponseBody
    public ZLResult purchaseReceiptToUpdateDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.purchaseReceiptToUpdateDetail(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/showReceiptGoodsByOrder")
    @ResponseBody
    public ZLResult showReceiptGoodsByOrder(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.showReceiptGoodsByOrder(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/purchaseReceiptExamine")
    @ResponseBody
    public ZLResult purchaseReceiptExamine(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.purchaseReceiptExamine(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/purchaseReceiptDel")
    @ResponseBody
    public ZLResult purchaseReceiptDel(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.purchaseReceiptDel(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/ChooseReceipt")
    @ResponseBody
    public ZLResult Choose(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitPurchaseReceiptService.ChooseReceipt(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }
}
