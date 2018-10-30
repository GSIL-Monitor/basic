package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitPriceRecordService;
import com.wgb.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/retail/pricerecord")
public class MTPricerecordController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitPriceRecordService apitPriceRecordService;

    /**
     * 2.0页面查询收银流水
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryManageDetail")
    @ResponseBody
    public ZLResult queryManageDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.queryManageDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 2.0页面查询收银流水-所有支付方式
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryManageDetailPaytype")
    @ResponseBody
    public ZLResult queryManageDetailPaytype(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.queryManageDetailPaytype(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportManage")
    @ResponseBody
    public ZLResult exportManage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);

        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.getListForExportManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        List<Map<String, Object>> manageList = zlRpcResult.getList();
        //处理商品列表 - 导出到excel
        if (manageList.size() == 0) {
            // 字段名数组
            String[] manages = {"ticketcode", "branchname", "tradename", "total", "payname", "payamount",
                    "cashiername", "paytime", "memberno", "businesscode", "returncode"};

            // 字段名数组
            String[] manageNames = {"票号", "门店", "交易", "单据金额", "付款方式", "付款金额",
                    "收银员", "收银时间", "会员号", "付款卡/凭证号", "退货原单号"};

            String manageName = "零售查询-收银流水表";

            ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
        } else {
            ZLRpcResult zlRpcResultSum =  new ZLRpcResult();
            try {
                //系统调用
                zlRpcResultSum = apitPriceRecordService.getSumForExportManage(params);
            }catch (Exception ex){
                //系统级别异常
                throw new ServiceException(ServiceException.SYS_ERROR);
            }
            if (!zlRpcResultSum.success()) {
                throw  new ServiceException(zlRpcResultSum.getErrorMsg());
            }

            Map<String, Object> manageListSum = zlRpcResultSum.getMap();
            //给excel添加合计
            manageListSum.put("branchname", "合计");
            //处理商品列表 - 导出到excel
            manageList.add(manageListSum == null ? new HashMap<String, Object>() : manageListSum);

            // 字段名数组
            String[] manages = {"ticketcode", "branchname", "tradename", "total", "payname", "payamount",
                    "cashiername", "paytime", "memberno", "businesscode", "returncode"};

            // 字段名数组
            String[] manageNames = {"票号", "门店", "交易", "单据金额", "付款方式", "付款金额",
                    "收银员", "收银时间", "会员号", "付款卡/凭证号", "退货原单号"};

            String manageName = "零售查询-收银流水表";
            ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
        }

        return ZLResult.Success();
    }

    /**
     * 导购员提成
     * @param request
     * @return
     */
    @RequestMapping("/queryCsManageDetail")
    @ResponseBody
    public ZLResult  queryCsManageDetail(HttpServletRequest request){

        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.queryCsManageDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

    /**
     * 导购员提成导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportCsManage")
    public void exportCsManage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);

        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.getExportCsManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        List<Map<String, Object>> manageList = zlRpcResult.getList();
        //处理商品列表 - 导出到excel
        // 字段名数组
        String[] manages = {"branchname", "assistantcode", "assistantname", "paytotal", "commissionprice"};

        // 字段名数组
        String[] manageNames = {"门店", "导购员编号", "导购员姓名", "销售金额", "提成金额"};

        String manageName = "零售查询-导购员提成表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
    }

    /**
     * 导购员提成明细
     * @param request
     * @return
     */
    @RequestMapping("/DetailCsManage")
    @ResponseBody
    public ZLResult  DetailCsManage(HttpServletRequest request) {

        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.DetailCsManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

    /**
     * 导购员提成明细导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportDetailCsManage")
    public void exportDetailCsManage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);

        try {
            //系统调用
            zlRpcResult = apitPriceRecordService.getExportDetailCsManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        List<Map<String, Object>> manageList = zlRpcResult.getList();
        //处理商品列表 - 导出到excel
        // 字段名数组
        String[] manages = {"branchname", "assistantcode", "assistantname", "ticketcode", "saletime",
                "commoditycode", "commodityname", "spec", "unitname", "tradename", "commissionprice", "paysubtotal", "categorynamestr"};

        // 字段名数组
        String[] manageNames = {"门店", "导购员编号", "导购员姓名", "票号", "销售时间",
                "货号", "品名", "规格", "单位", "交易方式", "提成金额", "销售金额", "类别"};

        String manageName = "零售查询-导购员提成明细表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
    }

}
