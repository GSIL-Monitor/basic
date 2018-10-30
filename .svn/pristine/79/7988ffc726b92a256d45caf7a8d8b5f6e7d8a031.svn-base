package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseLogsService;
import com.wgb.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by whq on 2018/4/19.
 */
@Controller
@RequestMapping("/purchase/purchase")
public class MTPurchaseLogController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseLogController.class);

    @Autowired
    private ApitPurchaseLogsService apitPurchaseLogsService;

    @RequestMapping("/purchaseSummaryDayDetail")
    @ResponseBody
    public ZLResult purchaseSummaryDayDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.purchaseSummaryDayDetail(params);

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

    @RequestMapping("/exportPurchaseDay")
    @ResponseBody
    public ZLResult exportPurchaseDay(HttpServletRequest request, HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitPurchaseLogsService.exportPurchaseDay(params);
            // 字段名数组
            String[] purchase = {"purbranchname", "createtime","commoditycode","commodityname",
                    "spec", "unitname", "receiptnums", "receiptsubtotal", "receiptgivenums", "returnnums", "returnsubtotal", "returngivenums", "subnums", "subtotal"};
            // 字段名数组
            String[] purchaseNames = {"门店", "日期","货号", "品名",
                    "规格", "单位", "采购数量", "采购金额", "采购赠送", "退货数量","退货金额", "退货赠送","数量小计","金额小计"};
            String purchaseName = "采购日汇总";
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

    @RequestMapping("/purchaseSummaryMonthDetail")
    @ResponseBody
    public ZLResult purchaseSummaryMonthDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.purchaseSummaryMonthDetail(params);

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

    @RequestMapping("/exportPurchaseMonth")
    @ResponseBody
    public ZLResult exportPurchaseMonth(HttpServletRequest request, HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.exportPurchaseMonth(params);
            // 字段名数组
            String[] purchase = {"purbranchname", "createtime","commoditycode","commodityname",
                    "spec", "unitname", "receiptnums", "receiptsubtotal", "receiptgivenums", "returnnums", "returnsubtotal", "returngivenums", "subnums", "subtotal"};

            // 字段名数组
            String[] purchaseNames = {"门店", "日期","货号", "品名",
                    "规格", "单位", "采购数量", "采购金额", "采购赠送", "退货数量","退货金额", "退货赠送","数量小计","金额小计"};

            String purchaseName = "采购月汇总";

            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
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

    @RequestMapping("/purchaseSummaryBrandDetail")
    @ResponseBody
    public ZLResult brand_detail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.brand_detail(params);

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

    @RequestMapping("/exportPurchaseBrand")
    @ResponseBody
    public ZLResult exportPurchaseBrand(HttpServletRequest request, HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.exportPurchaseBrand(params);
            // 字段名数组
            String[] purchase = {"purbranchname", "brandcode", "brandname", "receiptnums", "receiptsubtotal",
                    "receiptgivenums", "returnnums", "returnsubtotal", "returngivenums", "subnums", "subtotal"};

            // 字段名数组
            String[] purchaseNames = {"门店", "品牌编码", "品牌", "采购数量", "采购金额",
                    "采购赠送", "退货数量", "退货金额", "退货赠送", "数量小计", "金额小计"};

            String purchaseName = "采购品牌汇总";

            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
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

    @RequestMapping("/purchaseSummaryCategoryDetail")
    @ResponseBody
    public ZLResult purchaseSummaryCategoryDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.purchaseSummaryCategoryDetail(params);

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

    @RequestMapping("/exportPurchaseCategory")
    @ResponseBody
    public ZLResult exportPurchaseCategory(HttpServletRequest request,HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.exportPurchaseCategory(params);
            // 字段名数组
            String[] purchase = {"purbranchname", "categorynamestr","receiptnums","receiptsubtotal",
                    "receiptgivenums", "returnnums", "returnsubtotal", "returngivenums", "subnums", "subtotal"};

            // 字段名数组
            String[] purchaseNames = {"门店", "类别","采购数量", "采购金额",
                    "采购赠送", "退货数量", "退货金额", "退货赠送", "数量小计", "金额小计"};

            String purchaseName = "采购类别汇总";

            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
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

    @RequestMapping("/purchaseLogsManageDetail")
    @ResponseBody
    public ZLResult manage_detail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.manage_detail(params);

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

    @RequestMapping("/exportPurchaseDetail")
    @ResponseBody
    public ZLResult exportPurchaseDetail(HttpServletRequest request,HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.exportPurchaseDetail(params);
            // 字段名数组
            String[] purchase = {"purbranchname", "billcode", "billtype", "commoditycode", "commodityname",
                    "spec", "unitname", "receiptnums", "receiptgivenums", "receiptprice", "receiptsubtotal", "suppliername",
                    "categoryname", "brandname"};

            // 字段名数组
            String[] purchaseNames = {"门店", "单号", "单据类型", "货号", "品名",
                    "规格", "单位", "数量", "赠送数量", "单价", "金额", "供应商", "类别", "品牌"};

            String purchaseName = "采购明细汇总";

            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
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

    @RequestMapping("/purchaseSummarySupplierDetail")
    @ResponseBody
    public ZLResult supplier_detail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.supplier_detail(params);

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

    @RequestMapping("/exportPurchaseSupplier")
    @ResponseBody
    public ZLResult exportPurchaseSupplier(HttpServletRequest request,HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPurchaseLogsService.exportPurchaseSupplier(params);
            // 字段名数组
            String[] purchase = {"purbranchname", "suppliername","receiptnums","receiptsubtotal",
                    "receiptgivenums", "returnnums", "returnsubtotal", "returngivenums", "subnums", "subtotal"};

            // 字段名数组
            String[] purchaseNames = {"门店", "供应商","采购数量", "采购金额",
                    "采购赠送", "退货数量", "退货金额", "退货赠送", "数量小计", "金额小计"};

            String purchaseName = "采购供应商汇总";

            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
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

