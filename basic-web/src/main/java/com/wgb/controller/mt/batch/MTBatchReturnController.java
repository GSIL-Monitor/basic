package com.wgb.controller.mt.batch;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitBatchReturnService;
import com.wgb.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/batch/return")
public class MTBatchReturnController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTBatchReturnController.class);

    @Autowired
    private ApitBatchReturnService apitBatchReturnService;

    /**
     * 零售
     */
    @RequestMapping("/batchReturnDetail")
    @ResponseBody
    public ZLResult batchReturnDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReturnService.batchReturnDetail(params);
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



    @RequestMapping("/batchReturnAddOrUpdate")
    @ResponseBody
    public ZLResult addOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReturnService.batchReturnAddOrUpdate(params);
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

    @RequestMapping("/batchReturnDel")
    @ResponseBody
    public ZLResult purchaseReturnDel(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReturnService.batchReturnDel(params);
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

    @RequestMapping("/batchReturnToUpdateDetail")
    @ResponseBody
    public ZLResult purchaseReceiptToUpdateDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReturnService.batchReturnToUpdateDetail(params);
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

    @RequestMapping("/showReturnGoodsByReceipt")
    @ResponseBody
    public ZLResult showReturnGoodsByReceipt(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReturnService.showReturnGoodsByReceipt(params);
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


    @RequestMapping("/batchReturnExamine")
    @ResponseBody
    public ZLResult purchaseReturnExamine(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReturnService.batchReturnExamine(params);
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

    /*导出批发退货单*/
    @RequestMapping("/exportBatchReturnDetail")
    @ResponseBody
    public ZLResult exportBatchReturnDetail(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBatchReturnService.exportBatchReturnDetail(params);
            // 字段名数组
            String[] purchase = {"billcode","customername","storehousename",
                    "totalprice", "examineflag", "createusername", "createtime", "receiptcode"};
            // 字段名数组
            String[] purchaseNames = {"业务单号","客户名称","仓库",
                    "退货金额", "审核状态", "制单人", "制单日期", "原批发销售单号"};
            String purchaseName = "批发退货单_";
            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
