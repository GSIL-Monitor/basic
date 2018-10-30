package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSaleRecordService;
import com.wgb.util.Contants;
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
@RequestMapping("/retail/salerecord")
public class MTSalerecordController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitSaleRecordService apitSaleRecordService;

     /**
     * react页面查询月报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryMonthManageDetail")
    @ResponseBody
    public ZLResult queryMonthManageDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.queryMonthManageDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportMonthManage")
    @ResponseBody
    public ZLResult exportMonthManage(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.exportMonthManage(params);
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
        String[] manages = {"branchname", "saletime", "commoditycode", "commodityname", "spec",
                "unitname", "salenums", "paysubtotal", "returnsalenums", "returnpaysubtotal", "nums", "total"};

        // 字段名数组
        String[] manageNames = {"门店", "月份", "货号", "品名", "规格",
                "单位", "销售数量", "销售金额", "退货数量", "退货金额", "实际销售数量", "金额小计"};

        String manageName = "零售查询-零售月汇总表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
        return ZLResult.Success();
    }

    /**
     * react页面查询日报表
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryDayManageDetail")
    @ResponseBody
    public ZLResult queryDayManageDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.queryDayManageDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportDayManage")
    @ResponseBody
    public ZLResult exportDayManage(HttpServletRequest request, HttpServletResponse response) {


        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.exportDayManage(params);
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
        String[] manages = {"branchname", "saletime", "commoditycode", "commodityname", "spec",
                "unitname", "salenums", "paysubtotal", "returnsalenums", "returnpaysubtotal", "nums", "total"};

        // 字段名数组
        String[] manageNames = {"门店", "日期", "货号", "品名", "规格",
                "单位", "销售数量", "销售金额", "退货数量", "退货金额", "实际销售数量", "金额小计"};

        String manageName = "零售查询-零售日汇总表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
        return ZLResult.Success();
    }

    /**
     * 品牌
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryBrandDetail")
    @ResponseBody
    public ZLResult queryBrandDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.queryBrandDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportBrandManage")
    @ResponseBody
    public ZLResult exportBrandManage(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.exportBrandManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> manageList = zlRpcResult.getList();
        // 字段名数组
        String[] manages = {"branchname", "brandcode", "brandname", "salenums", "paysubtotal",
                "returnsalenums", "returnpaysubtotal", "nums", "total"};

        // 字段名数组
        String[] manageNames = {"门店", "品牌编码", "品牌", "销售数量", "销售金额",
                "退货数量", "退货金额", "实际销售数量", "金额小计"};

        String manageName = "零售查询-零售品牌汇总表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
        return ZLResult.Success();
    }

    /**
     * react页面查询零售类别汇总
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryCaterecordDetail")
    @ResponseBody
    public ZLResult queryCaterecordDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        String categorytype = (String) params.get("categorytype");
        params.put("categorytype", Contants.PROFITTYPE_THREE);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.queryCaterecordDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportCategoryManage")
    @ResponseBody
    public ZLResult exportCategoryManage(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.exportCategoryManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        List<Map<String, Object>> manageList = zlRpcResult.getList();
        // 字段名数组
        String[] manages = {"branchname", "categorynamestr", "salenums", "paysubtotal", "returnsalenums",
                "returnpaysubtotal", "nums", "total"};

        // 字段名数组
        String[] manageNames = {"门店", "类别", "销售数量", "销售金额",
                "退货数量", "退货金额", "实际销售数量", "金额小计"};

        String manageName = "零售查询-零售类别汇总表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);

        return ZLResult.Success();
    }

    /**
     * 2.0零售商品流水
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
            zlRpcResult = apitSaleRecordService.queryManageDetail(params);
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
     * 2.0零售商品流水-导出
     * @param request
     * @return
     */
    @RequestMapping("/exportManage")
    @ResponseBody
    public ZLResult exportManage(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        ZLRpcResult zlRpcResultSum  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.exportManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        try {
            //系统调用
            zlRpcResultSum = apitSaleRecordService.exportManageSum(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResultSum.success()) {
            throw  new ServiceException(zlRpcResultSum.getErrorMsg());
        }

        List<Map<String, Object>> manageList = zlRpcResult.getList();
        Map<String, Object> manageListSum = zlRpcResultSum.getMap();
        manageListSum.put("branchname", "合计");
        //处理商品列表 - 导出到excel
        manageList.add(manageListSum == null ? new HashMap<String, Object>() : manageListSum);
        // 字段名数组
        String[] manages = {"ticketcode", "branchname", "saletime", "commoditycode", "commodityname", "spec",
                "unitname", "tradename", "salenums", "saleprice", "paysaleprice", "paysubtotal", "assistantname", "cashiername", "categoryname"};

        // 字段名数组
        String[] manageNames = {"票号", "门店", "销售时间", "货号", "品名", "规格",
                "单位", "交易方式", "数量", "原价", "售价", "小计金额", "导购员", "收银员", "类别"};

        String manageName = "零售查询-零售商品流水表";

        ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);

        return ZLResult.Success();
    }

    /**
     * react页面查询零售毛利分析
     * @param request
     * @return
     */
    @RequestMapping("/queryProfitListDetail")
    @ResponseBody
    public ZLResult queryProfitListDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        logger.info("react页面查询零售毛利分析:"+params);
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.queryProfitList(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryProfitListExport")
    @ResponseBody
    public ZLResult queryProfitListExport(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        String profitType = (String) params.get("profitType");
        if (Contants.PROFITTYPE_TWO.equals(profitType)) {
            try {
                //系统调用
                zlRpcResult = apitSaleRecordService.queryProfitCategoryExport(params);
            }catch (Exception ex){
                //系统级别异常
                throw new ServiceException(ServiceException.SYS_ERROR);
            }
            if (!zlRpcResult.success()) {
                throw  new ServiceException(zlRpcResult.getErrorMsg());
            }
            List<Map<String, Object>> profitList = zlRpcResult.getList();
            //处理商品列表 - 导出到excel

            // 字段名数组
            String[] manages = {"categoryname", "salenums", "subtotalprice", "profit", "profitrate",
                    "costprice"};

            // 字段名数组
            String[] manageNames = {"类别", "销售数量", "销售金额", "毛利", "毛利率",
                    "销售成本"};

            String manageName = "零售查询-零售毛利分析商品类别表";

            ExcelUtil.exportExcel(profitList, manages, manageNames, manageName, request, response);
        } else if (Contants.PROFITTYPE_THREE.equals(profitType)) {
            try {
                //系统调用
                zlRpcResult = apitSaleRecordService.queryProfitBrandExport(params);
            }catch (Exception ex){
                //系统级别异常
                throw new ServiceException(ServiceException.SYS_ERROR);
            }
            if (!zlRpcResult.success()) {
                throw  new ServiceException(zlRpcResult.getErrorMsg());
            }
            List<Map<String, Object>> profitList = zlRpcResult.getList();
            //处理商品列表 - 导出到excel

            // 字段名数组
            String[] manages = {"brandname", "salenums", "subtotalprice", "profit", "profitrate",
                    "costprice"};

            // 字段名数组
            String[] manageNames = {"品牌", "销售数量", "销售金额", "毛利", "毛利率",
                    "销售成本"};

            String manageName = "零售查询-零售毛利分析商品品牌表";

            ExcelUtil.exportExcel(profitList, manages, manageNames, manageName, request, response);
        } else if (Contants.PROFITTYPE_FOUR.equals(profitType)) {
            try {
                //系统调用
                zlRpcResult = apitSaleRecordService.queryProfitBranchExport(params);
            }catch (Exception ex){
                //系统级别异常
                throw new ServiceException(ServiceException.SYS_ERROR);
            }
            if (!zlRpcResult.success()) {
                throw  new ServiceException(zlRpcResult.getErrorMsg());
            }
            List<Map<String, Object>> profitList = zlRpcResult.getList();
            //处理商品列表 - 导出到excel

            // 字段名数组
            String[] manages = {"branchcode", "branchname", "salenums", "subtotalprice", "profit",
                    "profitrate", "costprice"};

            // 字段名数组
            String[] manageNames = {"门店编号", "门店名称", "销售数量", "销售金额", "毛利",
                    "毛利率", "销售成本"};

            String manageName = "零售查询-零售毛利分析门店表";

            ExcelUtil.exportExcel(profitList, manages, manageNames, manageName, request, response);
        } else if (Contants.PROFITTYPE_ONE.equals(profitType) || profitType == null) {
            try {
                //系统调用
                zlRpcResult = apitSaleRecordService.queryProfitExport(params);
            }catch (Exception ex){
                //系统级别异常
                throw new ServiceException(ServiceException.SYS_ERROR);
            }
            if (!zlRpcResult.success()) {
                throw  new ServiceException(zlRpcResult.getErrorMsg());
            }
            List<Map<String, Object>> profitList = zlRpcResult.getList();
            //处理商品列表 - 导出到excel

            // 字段名数组
            String[] manages = {"commoditycode", "commodityname", "spec",
                    "unitname", "salenums", "paysubtotal", "profit", "profitrate", "costprice", "categoryname", "brandname"};

            // 字段名数组
            String[] manageNames = {"货号", "品名", "规格",
                    "单位", "销售数量", "销售金额", "毛利", "毛利率", "销售成本", "类别", "品牌"};

            String manageName = "零售查询-零售毛利分析商品表";
            ExcelUtil.exportExcel(profitList, manages, manageNames, manageName, request, response);
        }
        return ZLResult.Success();
    }

    /**
     * react页面商品销售统计
     *
     * @param request
     * @return
     */
    @RequestMapping("/reactCommoditySaleManageDetail")
    @ResponseBody
    public ZLResult reactCommoditySaleManageDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.reactCommoditySaleManageDetail(params);
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
     * 商品销售统计-导出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/exportCommoditySaleManage")
    @ResponseBody
    public ZLResult exportCommoditySaleManage(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {
            //系统调用
            zlRpcResult = apitSaleRecordService.exportProductSaleManage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }else {
            List<Map<String, Object>> manageList = zlRpcResult.getList();

            // 字段名数组
            String[] manages = {"commodityname", "commoditycode", "branchname", "categoryname", "storage", "salenums", "subtotal", "paysubtotal", "realsubpayamount"};

            // 字段名数组
            String[] manageNames = {"品名", "货号", "门店", "商品分类", "现有库存", "销售数量", "商品总价", "实收金额（计算优惠后）", "实得金额(计算汇率后)"};

            String manageName = "零售查询-商品销售统计表";

            ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);
        }

        return ZLResult.Success();
    }

}
