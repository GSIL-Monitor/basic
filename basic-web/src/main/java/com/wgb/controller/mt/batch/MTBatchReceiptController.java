package com.wgb.controller.mt.batch;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitBatchReceiptService;
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
@RequestMapping("/batch/receipt")
public class MTBatchReceiptController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTBatchReceiptController.class);
    @Autowired
    private ApitBatchReceiptService apitBatchReceiptService;

    /**
     * 零售2.0
     */
    @RequestMapping("/batchReceiptDetail")
    @ResponseBody
    public ZLResult batchReceiptDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.batchReceiptDetail(params);
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

    @RequestMapping("/batchReceiptAddOrUpdate")
    @ResponseBody
    public ZLResult addOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.batchReceiptAddOrUpdate(params);
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

    @RequestMapping("/batchReceiptToUpdateDetail")
    @ResponseBody
    public ZLResult purchaseReceiptToUpdateDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.batchReceiptToUpdateDetail(params);
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
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.showReceiptGoodsByOrder(params);
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

    @RequestMapping("/batchReceiptExamine")
    @ResponseBody
    public ZLResult purchaseReceiptExamine(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.batchReceiptExamine(params);
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

    @RequestMapping("/batchReceiptDel")
    @ResponseBody
    public ZLResult purchaseOrderDel(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.batchReceiptDel(params);
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
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
                zlRpcResult = apitBatchReceiptService.ChooseReceipt(params);
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

    /*导出批发销售单*/
    @RequestMapping("/exportBatchReceiptDetail")
    @ResponseBody
    public ZLResult exportBatchReceiptDetail(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBatchReceiptService.exportBatchReceiptDetail(params);
            // 字段名数组
            String[] purchase = {"billcode", "billstatus","customername","storehousename",
                    "totalprice", "examineflag", "createusername", "createtime", "ordercode"};
            // 字段名数组
            String[] purchaseNames = {"业务单号", "单据状态","客户名称", "仓库",
                    "单据金额", "审核状态", "制单人", "制单日期", "批发订单号"};
            String purchaseName = "批发销售单_";
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
