package com.wgb.controller.mt.sales;

import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSaleRecordService;
import com.wgb.service.dubbo.dwms.ApiSaleInfoDataProvideService;
import com.wgb.service.dubbo.stms.web.ApitPurchaseReportFormsService;
import com.wgb.util.Contants;
import com.wgb.util.ValidateParams;
import com.wgb.util.excel.constant.ExcelTitles;
import com.wgb.util.excel.model.ExcelColumn;
import com.wgb.util.excel.util.CustomizeToExcel;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by 87357 on 2018/8/27.
 */
@Controller
@RequestMapping("/sale/reportforms")
public class MTSalesReportFormsController extends MTBaseController {

    @Autowired
    private ApitSaleRecordService apitSaleRecordService;
    @Autowired
    private ApiSaleInfoDataProvideService apiSaleInfoDataProvideService;

    public static final com.alibaba.dubbo.common.logger.Logger logger = LoggerFactory.getLogger(MTSalesReportFormsController.class);

    /**
     * 收银流水报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryManageDatailForReport")
    @ResponseBody
    public ZLResult queryManageDatailForReport(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        /*params.put("starttime","2017-12-30");
        params.put("endtime","2018-07-01");
        */
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        try {

            // 系统调用
            zlRpcResult = apitSaleRecordService.queryManageDatailForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 销售报表-收银流水导出
     * @param request
     * @return
     */
    @RequestMapping("/exportManageDatailForReport")
    @ResponseBody
    public ZLResult exportManageDatailForReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        //参数校验
        String keyword = MapUtils.getString(params, "keyword");
        String memberno = MapUtils.getString(params, "memberno");
        String returncod = MapUtils.getString(params, "returncod");
        String cashiername = MapUtils.getString(params, "cashiername");
        if (StringUtils.isNotEmpty(keyword)) {
            params.put("keyword",keyword.trim());
        }
        if ( StringUtils.isNotEmpty(memberno)){
            params.put("memberno",memberno.trim());
        }
        if ( StringUtils.isNotEmpty(returncod)){
            params.put("returncod",returncod.trim());
        }
        if ( StringUtils.isNotEmpty(cashiername)){
            params.put("cashiername",cashiername.trim());
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
           zlRpcResult = apitSaleRecordService.exportManageDatailForRepor(params);
            List<ExcelColumn> salesManageDatailCollectTitle = ExcelTitles.getSalesManageDatailCollectTitle();
            ExcelColumn excelColumn = salesManageDatailCollectTitle.get(0);
            excelColumn.setTitle(  excelColumn.getTitle());
            String saleName = "销售报表_收银流水_" +  DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response ,salesManageDatailCollectTitle ,zlRpcResult.getList() ,saleName);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }
    /**
     * 商品销售流水--报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryCommoditySaleWaterForReport")
    @ResponseBody
    public ZLResult queryCommoditySaleWaterForReport(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        handleIsheadCope(params);
        /*params.put("starttime","2017-12-30");
        params.put("endtime","2018-07-01");
        */
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        try {

            // 系统调用
            zlRpcResult = apitSaleRecordService.queryCommoditySaleWaterForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 商品销售流水--报表导出
     * @param request
     * @return
     */
    @RequestMapping("/exportCommoditySaleWaterForReport")
    @ResponseBody
    public ZLResult exportCommoditySaleWaterForReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
       /* params.put("starttime","2017-12-30");
        params.put("endtime","2018-07-01");
        params.put("zl-request-test","13962226972");
        */
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        //参数校验
        String keyword = MapUtils.getString(params, "keyword");
        String branchcode = MapUtils.getString(params, "branchcode");
        String returncod = MapUtils.getString(params, "returncod");
        String ticketcode = MapUtils.getString(params, "ticketcode");
        if (StringUtils.isNotEmpty(keyword)) {
            params.put("keyword",keyword.trim());
        }
        if ( StringUtils.isNotEmpty(branchcode)){
            params.put("branchcode",branchcode.trim());
        }
        if ( StringUtils.isNotEmpty(returncod)){
            params.put("returncod",returncod.trim());
        }
        if ( StringUtils.isNotEmpty(ticketcode)){
            params.put("ticketcode",ticketcode.trim());
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.exportCommoditySaleWaterForReport(params);
            List<ExcelColumn> commoditySaleWaterForReport = ExcelTitles.getCommoditySaleWaterForReport();
            ExcelColumn excelColumn = commoditySaleWaterForReport.get(0);
            excelColumn.setTitle(  excelColumn.getTitle());
            String saleName = "销售报表_商品销售流水_" +  DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response ,commoditySaleWaterForReport ,zlRpcResult.getList() ,saleName);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /**
     *商品销售统计报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryRetailStatisticsForReport")
    @ResponseBody
    public ZLResult queryRetailStatisticsForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
       /* params.put("industryid","12");
        params.put("starttime","2017-12-30");
        params.put("endtime","2018-07-01");
        */
        /*
        params.put("groupkey","commoditycode");//  commoditycode   categorycode brandcode
        params.put("orderkey","canreturnnums");//  commoditycode   categorycode brandcode
        params.put("orderdesc","DESC");//  commoditycode   categorycode brandcode
        */
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.queryRetailStatisticsForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);

        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     *商品销售统计明细报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryRetailStatisticsDetailForReport")
    @ResponseBody
    public ZLResult queryRetailStatisticsDetailForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        /*params.put("starttime","2017-12-30");
        params.put("endtime","2018-07-01");
        params.put("groupkey","commoditycode");//  commoditycode   categorycode brandcode
        params.put("orderkey","canreturnnums");//  commoditycode   categorycode brandcode
        params.put("orderdesc","DESC");//  commoditycode   categorycode brandcode
        */
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.queryRetailStatisticsDetailForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 商品销售统计展示页面--服装行业的明细接口
     * @param request
     * @return
     */
    @RequestMapping("/queryRetailStatisticsIndustryDetailForReport")
    @ResponseBody
    public ZLResult queryRetailStatisticsIndustryDetailForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        /*params.put("shopcode","125806668");//125806723
        params.put("brandcode","000001");
        */
        //明细导出的是sku商品，spucode和shopcode参数是必传
        String shopcode = MapUtils.getString(params, "shopcode");
        String industryid = MapUtils.getString(params, "industryid");
        String spucode = MapUtils.getString(params, "spucode");
        if (StringUtils.isEmpty(shopcode) || StringUtils.isEmpty(industryid) || StringUtils.isEmpty(spucode)) {
            logger.info(" 参数缺失!spucode" + spucode + "industryid:" + industryid + "shopcode:" + shopcode);
            return null;
        } else {
            // 定义返回对象
            ZLRpcResult zlRpcResult = new ZLRpcResult();
            try {
                // 系统调用
                zlRpcResult = apitSaleRecordService.queryRetailStatisticsIndustryDetailForReport(params);
            } catch (Exception ex) {
                // 系统级别异常
                logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
                logger.info(" 错误信息是" + ex);
                throw new ServiceException(ServiceException.SYS_ERROR);
            }
            // 判断返回结果
            if (!zlRpcResult.success()) {
                throw new ServiceException(zlRpcResult.getErrorMsg());
            }
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

    /**
     * 销售报表-商品销售统计导出
     * @param request
     * @return
     */
    @RequestMapping("/exportRetailStatisticsForReport")
    @ResponseBody
    public ZLResult exportRetailStatisticsForReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
       /*  params.put("starttime","2017-12-30");
        params.put("endtime","2018-07-01");
        params.put("groupkey","commoditycode");//  commoditycode   categorycode brandcode
        params.put("groupkey","commoditycode");//  commoditycode   categorycode brandcode
*/
        //先判断参数必传，不能为空
        ValidateParams.query_validateParams(params);
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        //参数校验
        String commodityname = MapUtils.getString(params, "commodityname");
        if (StringUtils.isNotEmpty(commodityname)) {
            params.put("commodityname",commodityname.trim());
        }
        String groupkey = MapUtils.getString(params, "groupkey");
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        List<ExcelColumn> salesRetailStatisticsCollectTitle =null;
        ExcelColumn excelColumn = null;
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.exportRetailStatisticsForReport(params);

            //判断是不是服装行业，如果是，导出的模板不一样
            if ( Contants.CLOTHES_INDUSTRYID.equals(MapUtils.getString(params, "industryid"))){
                salesRetailStatisticsCollectTitle = ExcelTitles.getSalesRetailStatisticsIndustryCollectTitle();
                System.out.println("查询结果 服装行业零售统计：" + JSON.toJSONString(zlRpcResult.getData()));
                excelColumn = salesRetailStatisticsCollectTitle.get(0);
                excelColumn.setTitle( excelColumn.getTitle()  );
                String saleName = "销售报表_零售统计_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                CustomizeToExcel.exportExcel(response ,salesRetailStatisticsCollectTitle ,zlRpcResult.getList() ,saleName);

            }else{
                if ( groupkey.equals("commoditycode") ){
                    salesRetailStatisticsCollectTitle = ExcelTitles.getSalesRetailStatisticsCollectTitle();
                }
                //如果前台传入的如果按照品类查询，则按照品类导出
                if ( groupkey.equals("categorycode") ){
                    salesRetailStatisticsCollectTitle = ExcelTitles.getSalesRetailStatistics1CollectTitle();
                }
                //如果前台传入的如果按照品牌查询，则按照品牌导出
                if ( groupkey.equals("brandcode") ){
                    salesRetailStatisticsCollectTitle = ExcelTitles.getSalesRetailStatistics2CollectTitle();
                }

                System.out.println("查询结果 商品销售统计：" + JSON.toJSONString(zlRpcResult.getData()));
                excelColumn = salesRetailStatisticsCollectTitle.get(0);
                excelColumn.setTitle( excelColumn.getTitle()  );
                String saleName = "销售报表_商品销售统计_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                CustomizeToExcel.exportExcel(response ,salesRetailStatisticsCollectTitle ,zlRpcResult.getList() ,saleName);
            }
            String saleName = "销售报表_商品销售统计_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response ,salesRetailStatisticsCollectTitle ,zlRpcResult.getList() ,saleName);

        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }
    /**
     * 门店统计报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryStoresSumForReport")
    @ResponseBody
    public ZLResult queryStoresSumForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
       /* params.put("starttime","2017-12-30");
        params.put("endtime","2018-10-01");
        */
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.queryStoresSumForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 门店统计明细报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryStoresSumDetailForReport")
    @ResponseBody
    public ZLResult queryStoresSumDetailForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        /*params.put("starttime","2017-12-30");
        params.put("endtime","2018-10-01");
        */
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.queryStoresSumDetailForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 门店统计 按支付方式 报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryStoresSumPayForReport")
    @ResponseBody
    public ZLResult queryStoresSumPayForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
      /*  params.put("starttime","2017-12-30");
        params.put("endtime","2018-10-01");
        params.put("shopcode","125806653");
*/
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        List<Map<String, Object>> manageList = zlRpcResult.getList();
      //  Map<String, Object> manageListSum = zlRpcResultSum.getMap();
     //   manageListSum.put("branchname", "合计");
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.queryStoresSumPayForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 门店统计 按支付方式 明细报表展示页面
     * @param request
     * @return
     */
    @RequestMapping("/queryStoresSumPayDetailForReport")
    @ResponseBody
    public ZLResult queryStoresSumPayDetailForReport(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
      /*  params.put("starttime","2017-12-30");
        params.put("endtime","2018-10-01");
        params.put("shopcode","125806653");
*/
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        List<Map<String, Object>> manageList = zlRpcResult.getList();
        //  Map<String, Object> manageListSum = zlRpcResultSum.getMap();
        //   manageListSum.put("branchname", "合计");
        try {
            // 系统调用
            zlRpcResult = apitSaleRecordService.queryStoresSumPayDetailForReport(params);
        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 销售报表-门店统计导出
     * @param request
     * @return
     */
    @RequestMapping("/exportStoresSumForReport")
    @ResponseBody
    public ZLResult exportStoresSumForReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
      /*   params.put("starttime","2017-12-30");
        params.put("endtime","2018-10-01");
        params.put("shopcode","125806653");*/
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            long startTime =System.currentTimeMillis();
            // 系统调用
            zlRpcResult = apitSaleRecordService.exportStoresSumForReport(params);
            long endTime =System.currentTimeMillis();
            System.out.println("查询数据用时：" + (endTime -startTime));
            System.out.println("查询结果：" + JSON.toJSONString(zlRpcResult.getData()));
            List<ExcelColumn> salesStoresSumPayCollectTitle = ExcelTitles.getSalesStoresSumCollectTitle();
            ExcelColumn excelColumn = salesStoresSumPayCollectTitle.get(0);
            excelColumn.setTitle( excelColumn.getTitle());
            String saleName = "销售报表_门店统计_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response ,salesStoresSumPayCollectTitle ,zlRpcResult.getList() ,saleName);

        } catch (Exception ex){
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }
    /**
     * 销售报表-门店统计 按支付方式导出
     * @param request
     * @return
     */
    @RequestMapping("/exportStoresSumPayForReport")
    @ResponseBody
    public ZLResult exportStoresSumPayForReport(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        //个是参数校验的方法:替换orderby的值
        ValidateParams.query_validateParamsOderKey(params);
        /*params.put("starttime","2017-12-30");
        params.put("endtime","2018-10-01");
*/

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            long startTime =System.currentTimeMillis();
            // 系统调用
            zlRpcResult = apitSaleRecordService.exportStoresSumPayForReport(params);
            long endTime =System.currentTimeMillis();
            System.out.println("查询数据用时：" + (endTime -startTime));
            System.out.println("查询结果：" + JSON.toJSONString(zlRpcResult.getData()));
            List<ExcelColumn> salesStoresSumPayCollectTitle = ExcelTitles.getSalesStoresSumPayCollectTitle();
            ExcelColumn excelColumn = salesStoresSumPayCollectTitle.get(0);
            excelColumn.setTitle( excelColumn.getTitle());
            String saleName = "销售报表_门店统计_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response ,salesStoresSumPayCollectTitle ,zlRpcResult.getList() ,saleName);

        } catch (Exception ex){
            // 系统级别异常
            logger.info("销售报表_门店统计 按支付方式调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /*************************************统计分析报表********************************************************************/
    /**
     * 统计分析报表-商品分析
     * @param request
     * @return
     */
    @RequestMapping("/getSalesVolumeSummaryDataList")
    @ResponseBody
    public ZLResult getSalesVolumeSummaryDataList(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        /*   params.put("zl-request-test", "15262580023");
        params.put("shopcode", "125806649");
        params.put("branchcode", "001");
        params.put("startdatetime", "2018-09-26");
        params.put("enddatetime", "2018-09-30");
        params.put("currentdatetime","2018-10-01");
        params.put("saletype","0");
      */
        ZLRpcResult zlRpcResult0 = null;
        ZLRpcResult zlRpcResult1 = null;
        try {
            // 系统调用-概要值
            zlRpcResult0 = apiSaleInfoDataProvideService.getSaleVolumeAnalysisData(params);
            logger.info("-概要值" + zlRpcResult0.getData());
            //系统调用折线图预测值
            zlRpcResult1 = apiSaleInfoDataProvideService.getSaleVolumePredictData(params);
            logger.info("折线图预测值" + zlRpcResult1.getData());
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult0.success()) {
            throw new ServiceException(zlRpcResult0.getErrorMsg());
        }
        if (!zlRpcResult1.success()) {
            throw new ServiceException(zlRpcResult1.getErrorMsg());
        }
        Map<String, Object> item = new HashMap<>();
        item.put("saleVolumeAnalysisData", zlRpcResult0.getData());
        item.put("saleVolumePredictData", zlRpcResult1.getData());
       // item.put("tetsdata", "111111111111111111111");
        return ZLResult.Success(item);
    }

    /**
     * 统计分析报表-商品分析 预测信息
     * @param request
     * @return
     */
    @RequestMapping("/getSaleVolumePredictData")
    @ResponseBody
    public ZLResult getSaleVolumePredictData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        /*  params.put("shopcode","125806649");
        params.put("branchcode","001");
        params.put("currentdatetime","2018-10-01");
       // params.put("enddatetime","2018-09-30");
     */
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiSaleInfoDataProvideService.getSaleVolumePredictData(params);

        } catch (Exception ex) {
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 统计分析报表-商品分析 对比时间
     * @param request
     * @return
     */
    @RequestMapping("/getSaleVolumeCompareData")
    @ResponseBody
    public ZLResult getSaleVolumeCompareData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
       /* params.put("shopcode", "125806649");
        params.put("branchcode", "001");
        params.put("startdatetime", "2018-09-29");
        params.put("enddatetime", "2018-09-30");
        params.put("enddatetimecmp", "2018-09-28");

        params.put("comparetarget","1");
        params.put("saletype","0");
*/
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiSaleInfoDataProvideService.getSaleVolumeCompareData(params);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 统计分析报表-商品分析 商品明细,传个page
     * @param request
     * @return
     */
    @RequestMapping("/queryTotalDetailList")
    @ResponseBody
    public ZLResult queryTotalDetailList(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
        //参数校验
        String keyword = MapUtils.getString(params, "keyword");
        String barcode = MapUtils.getString(params, "barcode");
        if (StringUtils.isNotEmpty(keyword)) {
            params.put("keyword",keyword.trim());
        }
      /* params.put("shopcode","125806649");
        params.put("branchcode","001");
        params.put("startdatetime","2018-09-26");
        params.put("enddatetime","2018-09-30");
        params.put("sortkey","1");
        params.put("sorttype","1");
 */
        //前台需要传入一个page 去获取参数，
        String pagetop = params.get("page").toString();
        String pageEnd = params.get("pageSize").toString();
        Page page = new Page();
        if (StringUtils.isEmpty(pagetop)){
            //如果不传入，则默认查询全部的值
            page.setPage(0);
            page.setPageSize(0);
        }else {
            //如果传入则根据pagesize去调用值

            page.setPage(Integer.parseInt(pagetop));
            page.setPageSize(Integer.parseInt(pageEnd));
        }
        params.put("page",page);
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiSaleInfoDataProvideService.queryTotalDetailList(params);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 统计分析报表-商品分析明细导出
     * @param request
     * @return
     */
    @RequestMapping("/exportTotalDetailList")
    @ResponseBody
    public ZLResult exportTotalDetailList(HttpServletRequest request, HttpServletResponse response) {
        // 获取入参数据
        Map<String, Object> params = getParams();
         /* params.put("shopcode","125806649");
        params.put("branchcode","001");
        params.put("startdatetime","2018-09-28");
        params.put("enddatetime","2018-09-30");
        */
        //前台需要传入一个page 去获取参数，
        String pagetop = params.get("page").toString();
        String pageEnd = params.get("pageSize").toString();
        Page page = new Page();
        //查询全部的数据
        page.setPage(0);
        page.setPageSize(0);

        params.put("page",page);
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            long startTime = System.currentTimeMillis();
            // 系统调用
            zlRpcResult = apiSaleInfoDataProvideService.queryTotalDetailList(params);
            long endTime = System.currentTimeMillis();
            System.out.println("查询数据用时：" + (endTime - startTime));
            System.out.println("查询结果：" + JSON.toJSONString(zlRpcResult.getData()));
            //编写excel表格
            List<ExcelColumn> queryTotalDetailListCollectTitle = ExcelTitles.queryTotalDetailListCollectTitle();
            ExcelColumn excelColumn = queryTotalDetailListCollectTitle.get(0);

            excelColumn.setTitle(excelColumn.getTitle());
            String saleName = "统计分析_商品分析明细_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            //传过来的值是一个 map对象 存放到page中
            Map<String, Object> map = (Map<String, Object>)zlRpcResult.getData();
            //取值 map中的page对象
            Page pageInfo= (Page)map.get("page");
            //变成list
            List<Map<String ,Object>> list =pageInfo.getList();
            //给excel
           CustomizeToExcel.exportExcel(response, queryTotalDetailListCollectTitle, pageInfo.getList(), saleName);

        } catch (Exception ex) {
            // 系统级别异常
            logger.info("API服装行业的商品档案统调用处理异常，输入参数 =" + params);
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }
}
