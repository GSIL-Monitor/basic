package com.wgb.controller.mt.purchase;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseReportFormsService;
import com.wgb.util.excel.constant.ExcelTitles;
import com.wgb.util.excel.model.ExcelColumn;
import com.wgb.util.excel.util.CustomizeToExcel;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 87357 on 2018/8/23.
 */
@Controller
@RequestMapping("/purchase/reportforms")
public class MTPurchaseReportFormsController extends MTBaseController {

    @Autowired
    ApitPurchaseReportFormsService apitPurchaseReportFormsService;

    /**
     * 采购报表-采购统计查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPurchaseStatisticsReport")
    @ResponseBody
    public ZLResult queryPurchaseStatisticsReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            if ("12".equals(MapUtils.getString(params,"industryid"))){
                zlRpcResult = apitPurchaseReportFormsService.queryPurchaseStatisticsClothReport(params);
            }else {
            zlRpcResult = apitPurchaseReportFormsService.queryPurchaseStatisticsReport(params);
            }
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException("API采购报表-采购统计查询系统调用处理异常，输入参数 =" + params);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 采购报表-采购统计导出
     *
     * @param request
     * @return
     */
    @RequestMapping("/exportPurchaseStatisticsReport")
    @ResponseBody
    public ZLResult exportPurchaseStatisticsReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            if ("12".equals(MapUtils.getString(params,"industryid"))){
                zlRpcResult = apitPurchaseReportFormsService.exportPurchaseStatisticsClothReport(params);
                List<ExcelColumn> purchaseStatisticsCollectTitle = ExcelTitles.getPurchaseStatisticsClothCollectTitle();
                String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                String purchaseName = "采购报表统计" + today;
                CustomizeToExcel.exportExcel(response, purchaseStatisticsCollectTitle, zlRpcResult.getList(), purchaseName);
            }else {
                zlRpcResult = apitPurchaseReportFormsService.exportPurchaseStatisticsReport(params);
                List<ExcelColumn> purchaseStatisticsCollectTitle = ExcelTitles.getPurchaseStatisticsCollectTitle();
                String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                String purchaseName = "采购报表统计" + today;
                CustomizeToExcel.exportExcel(response, purchaseStatisticsCollectTitle, zlRpcResult.getList(), purchaseName);
            }
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException("API采购报表-采购统计导出系统调用处理异常，输入参数 =" + params);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 采购报表-采购明细查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPurchaseDetailsReport")
    @ResponseBody
    public ZLResult queryPurchaseDetailsReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            if ("12".equals(MapUtils.getString(params,"industryid"))){
                zlRpcResult = apitPurchaseReportFormsService.queryPurchaseDetailsClothReport(params);
            }else {
                zlRpcResult = apitPurchaseReportFormsService.queryPurchaseDetailsReport(params);
            }
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException("API采购报表-采购明细查询系统调用处理异常，输入参数 =" + params);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 采购报表-采购明细导出
     *
     * @param request
     * @return
     */
    @RequestMapping("/exportPurchaseDetailsReport")
    @ResponseBody
    public ZLResult exportPurchaseDetailsReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            if ("12".equals(MapUtils.getString(params,"industryid"))){
                zlRpcResult = apitPurchaseReportFormsService.exportPurchaseDetailsClothReport(params);
                List<ExcelColumn> purchaseDetailsCollectTitle = ExcelTitles.getPurchaseDetailsClothCollectTitle();
                String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                String purchaseName = "采购报表明细" + today;
                CustomizeToExcel.exportExcel(response, purchaseDetailsCollectTitle, zlRpcResult.getList(), purchaseName);
            }else {
                zlRpcResult = apitPurchaseReportFormsService.exportPurchaseDetailsReport(params);
                List<ExcelColumn> purchaseDetailsCollectTitle = ExcelTitles.getPurchaseDetailsCollectTitle();
                String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                String purchaseName = "采购报表明细" + today;
                CustomizeToExcel.exportExcel(response, purchaseDetailsCollectTitle, zlRpcResult.getList(), purchaseName);
            }
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException("API采购报表-采购明细导出系统调用处理异常，输入参数 =" + params);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

}
