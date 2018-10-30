package com.wgb.controller.mt.commodity;

import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiCommodityService;
import com.wgb.service.dubbo.dcms.web.ApitCommodityService;
import com.wgb.service.dubbo.stms.admin.ApiStorageService;
import com.wgb.util.*;
import com.wgb.util.CommonUtil;
import com.wgb.util.ExcelReader;
import com.wgb.util.ExcelUtil;
import com.wgb.service.dubbo.urms.web.ApitBranchService;
import com.wgb.util.excel.constant.ExcelTitles;
import com.wgb.util.excel.model.ExcelColumn;
import com.wgb.util.excel.util.CustomizeToExcel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/commodity/commodity")
public class MTCommodityController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitCommodityService apitCommodityService;

    @Autowired
    private ApiStorageService apiStorageService;

    @Autowired
    private ApiCommodityService apiCommodityService;

    @Autowired
    private ApitBranchService apitBranchService;

    /**
     * 收银端接口-查询商品(扫条形码barcode)
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCommodity")
    @ResponseBody
    public ZLResult getCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String barcode = MapUtils.getString(params, "barcode");
        String commoditycode = MapUtils.getString(params, "commoditycode");
        String id = MapUtils.getString(params, "id");
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(barcode) && StringUtils.isEmpty(id) && StringUtils.isEmpty(commoditycode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCommodityService.getCommodityInfo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-添加商品(扫条形码barcode)
     *
     * @param request
     * @return
     */
    @RequestMapping("/addCommodity")
    @ResponseBody
    public ZLResult addCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String barcode = MapUtils.getString(params, "barcode");
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(barcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCommodityService.addCommodity(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-添加商品(扫条形码barcode)
     * 2
     * @param request
     * @return
     */
    @RequestMapping("/addCommodityFromPos")
    @ResponseBody
    public ZLResult addCommodityFromPos(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String barcode = MapUtils.getString(params, "barcode");
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(barcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCommodityService.addCommodityFromPos(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 自动建档  收银端添加商品(扫条形码barcode)
     * 2018/10/18--服装版本添加(给普通行业用,服装行业还不支持自动建档)
     *
     * 这是第三个自动建档的方法
     *
     * @param request
     * @return
     */
    @RequestMapping("/addCommodityFromPosByClothesVersion")
    @ResponseBody
    public ZLResult addCommodityFromPosByClothesVersion(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode  = MapUtils.getString(params, "branchcode");
        String branchname  = MapUtils.getString(params, "branchname");
        String saleprice = MapUtils.getString(params, "saleprice");
        String commodityname = MapUtils.getString(params, "commodityname");
        String pricing = MapUtils.getString(params, "pricing");


        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(saleprice)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(commodityname)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(pricing)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchname)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCommodityService.addCommodityFromPosByClothesVersion(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 零售2.0 商品查询页面(存在调价,门店级数据,只查当前登录门店)
     *
     * @param
     * @return
     */
    @RequestMapping("/queryCommodityshow")
    @ResponseBody
    public ZLResult queryCommodityshow(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        handleIshead(params);

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.queryCommodityshow(params);
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


    /**
     * 零售2.0 商品资料(档案)页面(不存在调价,没有门店,商户级数据)
     *
     * @return
     * @paramrequest
     */
    @RequestMapping("/queryCommoditys")
    @ResponseBody
    public ZLResult queryCommoditys(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.queryCommoditys(params);
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

    /**
     * 零售2.0 采购,调拨,要货,调价单及其他接口查询商品数据
     *
     * @param
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        handleIshead(params);
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.query(params);
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

    /**
     * 零售2.0  新增商品,查询可捆绑商品的子商品
     *
     * @param
     * @return
     */
    @RequestMapping("/queryInner")
    @ResponseBody
    public ZLResult queryInner(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        handleIshead(params);

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.queryInner(params);
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

    /**
     * 查询商品信息，并根据当前店铺信息
     * 获取当前店铺的配送方式，以及配送方式的价格,适用于要货单，调拨单
     * 新增  CommodityServiceImpl.SHOPPING_PRICE
     *
     * @param
     * @return
     */
    @RequestMapping("/queryCommdityAndShippingPrice")
    @ResponseBody
    public ZLResult queryCommdityAndShippingPrice(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.queryCommdityAndShippingPrice(params);
            Page<Map<String, Object>> pageInfo = (Page<Map<String, Object>>) zlRpcResult.getData();
            if (pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getList())) {
                //商品清单
                List<Map<String, Object>> dataList = pageInfo.getList();
                //commoditycode list
                List<String> commoditylist = CommonUtil.getListStringFromListMap(dataList, "commoditycode");
                params.put("commoditycodelist", commoditylist);
                //库存数据
                ZLRpcResult rpcResult = apiStorageService.queryStorageListForBasic(params);
                List<Map<String, Object>> storageList = rpcResult.getList();
                //给商品添加库存字段
                setCommodityStorage(dataList, storageList);
                pageInfo.setList(dataList);
            }
            //查询库存数据
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

    /*
    *查询美团映射商品
    */
    @RequestMapping("/meituanCommodity")
    @ResponseBody
    public ZLResult meituanCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.meituanCommodity(params);
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

    /**
     * 零售2.0 新增修改提交的
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.saveOrUpdate(params);
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

    /**
     * 点击商品货号调用查询接口
     * 对商品可能已经有的条形码进行回显
     *
     * @params commoditycode、id
     */
    @RequestMapping("/querryOneMoreCode")
    @ResponseBody
    public ZLResult querryOneMoreCode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.querryOneMoreCode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getList());
    }

    /**
     * 添加一品多码
     *
     * @param
     * @return
     */
    @RequestMapping("/saveOneMoreCode")
    @ResponseBody
    public ZLResult saveOneMoreCode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.saveOneMoreCode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 删除一品多码
     *
     * @return
     */
    @RequestMapping("/deleteOneMoreCode")
    @ResponseBody
    public ZLResult deleteOneMoreCode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.deleteOneMoreCode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 零售2.0
     * 删除商品
     *
     * @param
     * @return
     */
    @RequestMapping("/delCommodity")
    @ResponseBody
    public ZLResult delCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.delCommodity(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 批量刪除
     *
     * @param
     * @return
     */
    @RequestMapping("/delBatch")
    @ResponseBody
    public ZLResult delBatch(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.delBatch(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 商品档案导出
     *
     * @Param request, response
     */
    @RequestMapping("/exportCommodity")
    @ResponseBody
    public void exportCommodity(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        /*params.put("industryid", "12");
        params.put("shopcode", "125806668");
        params.put("branchcode", "000001");
        params.put("spucode", "spu000001");
*/
        //参数校验
        String keyword = MapUtils.getString(params, "keyword");
        String barcode = MapUtils.getString(params, "barcode");
        if (StringUtils.isNotEmpty(keyword)) {
            params.put("keyword",keyword.trim());
        }
        if ( StringUtils.isNotEmpty(barcode)){
            params.put("barcode",barcode.trim());
        }
//        ValidateParams.query_validateParamsSPU(params);
        if (Contants.CLOTHES_INDUSTRYID.equals(MapUtils.getString(params, "industryid"))) {
            exportCommodityByIndustry(params, request, response);
        } else {
            exportMethod(params, request, response);
        }

    }

    /**
     * 商品查询导出(按门店)
     *
     * @Param request, response
     */
    @RequestMapping("/exportCommodityShow")
    @ResponseBody
    public void exportCommodityShow(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
      /* params.put("industryid", "12");
        params.put("shopcode", "125806668");
        params.put("branchcode", "000001");
       // params.put("spucode", "spu000001");
        params.put("softwaretype", "1");
 */
        //参数校验，必传spucode和shopcode
        ValidateParams.query_validateParams(params);
        //参数校验，去空格
        String keyword = MapUtils.getString(params, "keyword");
        String barcode = MapUtils.getString(params, "barcode");
        if (StringUtils.isNotEmpty(keyword)) {
            params.put("keyword",keyword.trim());
        }
        if ( StringUtils.isNotEmpty(barcode)){
            params.put("barcode",barcode.trim());
        }

        if (Contants.CLOTHES_INDUSTRYID.equals(MapUtils.getString(params, "industryid"))) {
            //商品查询导出,加门店,覆盖调价数据
            handleIshead(params);
            exportCommodityDetailByIndustry(params, request, response);
        } else {
            //商品查询导出,加门店,覆盖调价数据
            handleIshead(params);
            exportMethod(params, request, response);
        }
    }

    //处理商品列表 - 导出到excel
    private ZLResult exportMethod(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCommodityService.exportCommodity(params);
            List<ExcelColumn> commodityDetailByCollectTitle = new ArrayList<ExcelColumn>();
            ExcelColumn excelColumn = new ExcelColumn();
            //软件版本（1.标准版 2.连锁版）
            String softwaretype = MapUtils.getString(params, Contants.LOGIN_USER_SHOP_SOFTWARETYPE);
            if ("1".equals(softwaretype)) {
                commodityDetailByCollectTitle = ExcelTitles.getCommodityDetailByCollectTitle();
                excelColumn = commodityDetailByCollectTitle.get(0);
            } else {
                commodityDetailByCollectTitle = ExcelTitles.getCommodityDetailBy2CollectTitle();
                excelColumn = commodityDetailByCollectTitle.get(0);

            }
//            System.out.println("服装行业的商品导出：" + JSON.toJSONString(zlRpcResult.getData()));

            excelColumn.setTitle(excelColumn.getTitle());
            String saleName = "商品导出_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response, commodityDetailByCollectTitle, zlRpcResult.getList(), saleName);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();

//        ZLRpcResult zlRpcResult  = new ZLRpcResult();
//        try {
//            zlRpcResult = apitCommodityService.exportCommodity(params);
//        }catch (Exception ex){
//            throw new ServiceException(ServiceException.SYS_ERROR);
//        }
//        if(!zlRpcResult.success()){
//            throw  new ServiceException(zlRpcResult.getErrorMsg());
//        }
//        List<Map<String, Object>> commodityList = zlRpcResult.getList();
//
//        //软件版本（1.标准版 2.连锁版）
//        String softwaretype = MapUtils.getString(params,Contants.LOGIN_USER_SHOP_SOFTWARETYPE);
//        if ("1".equals(softwaretype)) {
//            // 字段名数组
//            String[] columns = {"commoditycode", "barcodes", "commodityname", "pricing", "plu", "unitname", "buyprice",
//                    "saleprice", "memberprice", "validtime", "spec", "categoryname",
//                    "brandname", "buyspec", "suppliername", "commoditystatus", "commoditytype", "storagetype",
//                    "canscore", "score", "commission", "commissionall", "intaxrate",
//                    "outtaxrate"};
//
//            // 列名数组
//            String[] columnNames = {"货号", "条形码", "品名", "销售方式", "PLU", "单位", "进货价",
//                    "零售价", "会员价", "有效期(天)", "规格", "类别",
//                    "品牌", "进货规格", "供应商名称", "商品状态", "商品类型", "库管类型",
//                    "是否允许积分", "积分值", "导购员是否提成", "提成值/提成率", "进项税",
//                    "销项税"};
//            String fileName = "商品资料";
//            ExcelUtil.exportExcel(commodityList, columns, columnNames, fileName, request, response);
//        } else {
//            String[] columns = {"commoditycode", "barcodes", "commodityname", "pricing", "plu", "unitname", "buyprice",
//                    "saleprice", "memberprice", "wholesaleprice", "dispatchprice", "validtime", "spec", "categoryname",
//                    "brandname", "buyspec", "suppliername", "commoditystatus", "commoditytype", "storagetype",
//                    "canscore", "score", "commission", "commissionall", "intaxrate",
//                    "outtaxrate"};
//
//            String[] columnNames = {"货号", "条形码", "品名", "销售方式", "PLU", "单位", "进货价",
//                    "零售价", "会员价", "批发价", "配送价", "有效期(天)", "规格", "类别",
//                    "品牌", "进货规格", "供应商名称", "商品状态", "商品类型", "库管类型",
//                    "是否允许积分", "积分值", "导购员是否提成", "提成值/提成率", "进项税",
//                    "销项税"};
//            String fileName = "商品资料";
     //      ExcelUtil.exportExcel(commodityList, columns, columnNames, fileName, request, response);
//        }

    }

    /**
     *  服装行业的商品导出 ,只要入参是12 的服装行业就走这里的excel
     *
     * @param
     * @param request
     * @param response
     */
    public ZLResult exportCommodityDetailByIndustry(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用

            zlRpcResult = apitCommodityService.exportCommodity(params);

            List<ExcelColumn> commodityDetailByIndustryCollectTitle = new ArrayList<ExcelColumn>();
            ExcelColumn excelColumn = new ExcelColumn();
            //软件版本（1.标准版 2.连锁版）
            String softwaretype = MapUtils.getString(params, Contants.LOGIN_USER_SHOP_SOFTWARETYPE);
            if ("1".equals(softwaretype)) {
                commodityDetailByIndustryCollectTitle = ExcelTitles.getCommodityDetailByIndustryCollectTitle();
                excelColumn = commodityDetailByIndustryCollectTitle.get(0);
            } else {
                commodityDetailByIndustryCollectTitle = ExcelTitles.getCommodityDetailByIndustry2CollectTitle();
                excelColumn = commodityDetailByIndustryCollectTitle.get(0);
            }

            System.out.println("服装行业的商品导出：" + JSON.toJSONString(zlRpcResult.getData()));
            excelColumn = commodityDetailByIndustryCollectTitle.get(0);
            excelColumn.setTitle(excelColumn.getTitle());
            String saleName = "服装行业的商品导出_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response, commodityDetailByIndustryCollectTitle, zlRpcResult.getList(), saleName);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /**
     * 服装行业的商品档案导出 ,只要入参是12 的服装行业就走这里的excel
     *
     * @param
     * @param request
     * @param response
     */
    public ZLResult exportCommodityByIndustry(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCommodityService.exportCommodityByIndustry(params);
            List<ExcelColumn> commodityDetailByIndustryCollectTitle = ExcelTitles.getCommodityByIndustryCollectTitle();
            System.out.println("服装行业的商品档案导出：" + JSON.toJSONString(zlRpcResult.getData()));
            ExcelColumn excelColumn = commodityDetailByIndustryCollectTitle.get(0);
            excelColumn.setTitle(excelColumn.getTitle());
            String saleName = "服装行业的商品档案导出_" + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            CustomizeToExcel.exportExcel(response, commodityDetailByIndustryCollectTitle, zlRpcResult.getList(), saleName);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info(" 错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    //商品导入
    @RequestMapping("/importCommodity")
    @ResponseBody
    public void importCommodity(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> result = null;
        List<Map<String, Object>> dataList = null;
        //参数校验，必传spucode和shopcode
        //ValidateParams.query_validateParamsSPU(params);
        try {
            //服装行业商品导入
            if ( Contants.CLOTHES_INDUSTRYID.equals(MapUtils.getString(params, "industryid"))) {
                //读取导入数据
                dataList = commodityReadByIndustry(file, params, request);
            } else {
                //零售商品导入
                //读取导入数据
                dataList = commodityRead(file, params, request);
            }

            //导入限制大小
            if (dataList != null && dataList.size() > 2000) {
                throw new ServiceException("一次只能导入2000条商品数据!");
            }
            params.put("dataList", dataList);

            //dubbo调用
            zlRpcResult = apitCommodityService.importCommodity(params);

            //处理调用结果
            if (zlRpcResult.success()) {
                result = zlRpcResult.getMap();
                String url = null;
                //服装行业商品导入
                if ( Contants.CLOTHES_INDUSTRYID.equals(MapUtils.getString(params, "industryid"))) {
                    //读取导入错误数据
                    url = saveHandleResultByIndustry(result);
                } else {
                    //零售商品错误导入
                    url = saveHandleResult(result);
                }
                result.put("url", url);
                result.remove("errorList");
            } else {
              //  throw new ServiceException("导入异常," + zlRpcResult.getErrorMsg());
            }
        } catch (ServiceException e) {
            result = new HashMap<String, Object>();
            result.put("success", "0");
            result.put("errorMsg", e.getMessage());
            logger.error("EXCEL处理异常0" + e.getMessage());
        } catch (Exception e) {
            result = new HashMap<String, Object>();
            result.put("success", "0");
            result.put("errorMsg", "导入异常");
            logger.error("EXCEL处理异常1" + e.toString());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, muste-rvalidate");
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            this.logger.error("与客户端通讯异常：" + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private String saveHandleResult(Map<String, Object> result) {
        List<Map<String, Object>> errorList = (List<Map<String, Object>>) result.get("errorList");
        if (CollectionUtils.isNotEmpty(errorList)) {
            // 字段名数组
            String[] columns = {"commoditycode", "barcodes", "commodityname", "pricing", "plu", "unitname", "buyprice",
                    "saleprice", "memberprice", "wholesaleprice", "dispatchprice", "storage", "validtime", "spec", "categoryname",
                    "brandname", "buyspec", "suppliername", "errorMsg"};
            // 模板标题
            String[] columnNames = {"货号", "条形码", "品名", "销售方式", "PLU", "单位", "进货价",
                    "零售价", "会员价", "批发价", "配送价", "初始库存", "有效期(天)", "规格", "类别",
                    "品牌", "进货规格", "供应商名称", "错误信息"};
            //文件名
            String fileName = "商品资料导入错误报表";
            //上传
            return upLoadNewExcel(errorList, columns, columnNames, fileName);
        }
        return "";
    }


    /**
     * 服装行业的
     *
     * @param result
     * @return
     */
    private String saveHandleResultByIndustry(Map<String, Object> result) {
        List<Map<String, Object>> errorList = (List<Map<String, Object>>) result.get("errorList");
        if (CollectionUtils.isNotEmpty(errorList)) {
            // 字段名数组
            String[] columns = {"spucode", "barcode", "commodityname", "unitname", "skucolor", "skusize", "buyprice",
                    "saleprice", "memberprice", "wholesaleprice", "dispatchprice", "storage", "categoryname",
                    "brandname", "remark", "errorMsg"};
            // 模板标题
            String[] columnNames = {"货号", "条形码", "*商品名称", "单位", "颜色", "尺码", "进货价",
                    "零售价", "会员价", "批发价", "配送价", "初始库存", "类别",
                    "品牌", "备注", "错误信息"};
            //文件名
            String fileName = "商品资料导入错误报表";
            //上传
            return upLoadNewExcel(errorList, columns, columnNames, fileName);
        }
        return "";
    }


    private List<Map<String, Object>> commodityRead(MultipartFile file, Map<String, Object> params, HttpServletRequest request) throws IOException {
        // 字段名数组
        String[] columns = {"commoditycode", "barcodes", "commodityname", "pricing", "plu", "unitname", "buyprice",
                "saleprice", "memberprice", "wholesaleprice", "dispatchprice", "storage", "validtime", "spec", "categoryname",
                "brandname", "buyspec", "suppliername"};

        // 模板标题
        String[] columnNames = {"货号", "条形码", "品名", "销售方式", "PLU", "单位", "进货价",
                "零售价", "会员价", "批发价", "配送价", "初始库存", "有效期(天)", "规格", "类别",
                "品牌", "进货规格", "供应商名称"};


        //把表格中数据按行读取，每一行放到一个Map中，组合一个List集合
        List<Map<String, Object>> dataList = ExcelReader.read(file.getInputStream(), columns, columnNames, 2);
        return dataList;
    }

    /**
     * 服装行业的导入封装
     *
     * @param
     * @return
     */
    private List<Map<String, Object>> commodityReadByIndustry(MultipartFile file, Map<String, Object> params, HttpServletRequest request) throws IOException {
        // 字段名数组
        String[] columns = {"spucode", "barcode", "commodityname", "unitname", "skucolor", "skusize", "buyprice",
                "saleprice", "memberprice", "wholesaleprice", "dispatchprice", "storage", "categoryname",
                "brandname", "remark"};

        // 模板标题
        String[] columnNames = {"货号", "条形码", "*商品名称", "单位", "颜色", "尺码", "进货价",
                "零售价", "会员价", "批发价", "配送价", "初始库存", "类别",
                "品牌", "备注"};

        //把表格中数据按行读取，每一行放到一个Map中，组合一个List集合
        List<Map<String, Object>> dataList = ExcelReader.read(file.getInputStream(), columns, columnNames, 2);
        return dataList;
    }

    private Map<String, Object> getStorageMap(List<Map<String, Object>> storageList, String commoditycode) {
        if (CollectionUtils.isNotEmpty(storageList)) {
            for (Map<String, Object> storageMap : storageList) {
                String _commoditycode = MapUtils.getString(storageMap, "commoditycode");
                if (_commoditycode.equals(commoditycode)) {
                    return storageMap;
                }
            }
        }
        return null;
    }

    private void setCommodityStorage(List<Map<String, Object>> dataList, List<Map<String, Object>> storageList) {
        for (Map<String, Object> dataItem : dataList) {
            String commoditycode = MapUtils.getString(dataItem, "commoditycode");
            Map<String, Object> storageMap = getStorageMap(storageList, commoditycode);
            if (MapUtils.isEmpty(storageMap)) {
                dataItem.put("storage", 0);
            } else {
                dataItem.put("storage", MapUtils.getIntValue(storageMap, "storage", 0));
            }
        }
    }

    //选择门店查看此门店商品的最新价格
    @RequestMapping("/queryCommodityByBranch")
    @ResponseBody
    public ZLResult queryCommodityByBranch(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        handleIshead(params);
       /* params.put("industryid", "12");
        params.put("shopcode", "125806668");
        params.put("spucode", "SPU0001");
        //params.put("shopcode", "125807008");
*/
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.queryCommodityByBranch(params);
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

    //选择门店查看此门店商品的商品名字，商品sku 给前端作为查询条件
    @RequestMapping("/selectCommodityByBranch")
    @ResponseBody
    public ZLResult selectCommodityByBranch(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        handleIshead(params);
        /*
        params.put("barcode","345678");
        */
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.selectCommodityByBranch(params);
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

    //商品在不同门店中的价格
    @RequestMapping("/queryBranchPriceByCommodity")
    @ResponseBody
    public ZLResult queryBranchPriceByCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        handleIshead(params);
        /*params.put("shopcode","100000512");
        params.put("commoditycode","SKU00002");*/
        //定义返回对象
        ZLRpcResult zlRpcResultbranch = new ZLRpcResult();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Page<Map<String, Object>> branchlistPage = new Page<Map<String, Object>>();
        try {
            //系统调用
            /*HashMap shopparams = new HashMap();
            shopparams.put("shopcode",MapUtils.getString(params, "shopcode"));
            shopparams.put("page",MapUtils.getString(params, "page"));
            shopparams.put("pageSize",MapUtils.getString(params, "pageSize"));*/
            zlRpcResultbranch = apitBranchService.selectBranchByShopcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResultbranch.success()) {
            throw new ServiceException(zlRpcResultbranch.getErrorMsg());
        }

        try {
            //系统调用
            branchlistPage = (Page<Map<String, Object>>) zlRpcResultbranch.getData();
            List<Map<String, Object>> branchlist = branchlistPage.getList();
            params.put("branchlist", branchlist);
            zlRpcResult = apitCommodityService.queryBranchPriceByCommodity(params);
            branchlistPage.setList(zlRpcResult.getList());
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(branchlistPage);
    }

    /**
     * 批量修改
     *
     * @param
     * @return
     */
    @RequestMapping("/updateCommodityBatch")
    @ResponseBody
    public ZLResult updateCommodityBatch(HttpServletRequest request) {
        Map<String, Object> params = getParams();
       /* params.put("industryid", "12");
        params.put("shopcode","125806668");
        params.put("ids","59565");
        params.put("commoditycodes","Spu0002");
        params.put("pricing","1");
        params.put("brandcode","000001");
        */
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.updateCommodityBatch(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    //扫码条形码，查出商品信息
    @RequestMapping("/queryBarLibInfoByBarcode")
    @ResponseBody
    public ZLResult queryBarLibInfoByBarcode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        /*params.put("shopcode","100000512");
        params.put("barcode","6920734742714");
        params.put("loginuserid","1001");*/
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityService.queryBarLibInfoByBarcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getMap());
    }

    //直接生成excel对象上传
    public String upLoadNewExcel(List<Map<String, Object>> list, String[] columns, String[] columnNames, String fileName) {
        //无数据直接返回""
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        try {
            HSSFWorkbook e = new HSSFWorkbook();
            Sheet sheet = e.createSheet("sheet0");
            Row row = null;
            Cell cell = null;
            Map temp = null;
            row = sheet.createRow(0);
            int[] rowMaxBytes = new int[columnNames.length];

            int size;
            for (size = 0; size < columnNames.length; ++size) {
                cell = row.createCell(size);
                cell.setCellValue(columnNames[size]);
                rowMaxBytes[size] = columnNames[size].getBytes().length * 2 * 256;
            }

            size = list.size();
            int userAgent;
            for (userAgent = 0; userAgent < size; ++userAgent) {
                temp = (Map) list.get(userAgent);
                row = sheet.createRow(userAgent + 1);
                int j = 0;
                String[] arr$ = columns;
                int len$ = columns.length;

                for (int i$ = 0; i$ < len$; ++i$) {
                    String column = arr$[i$];
                    cell = row.createCell(j);
                    cell.setCellValue(MapUtils.getString(temp, column, ""));
                    int var45 = MapUtils.getString(temp, column, "").getBytes().length * 2 * 256;
                    if (var45 >= rowMaxBytes[j]) {
                        rowMaxBytes[j] = var45;
                    }
                    ++j;
                }
            }

            for (userAgent = 0; userAgent < columnNames.length; ++userAgent) {
                int columnWidth = rowMaxBytes[userAgent];
                sheet.setColumnWidth(userAgent, columnWidth < 255 * 256 ? columnWidth : 255 * 256);
            }
            //生成文件名
            fileName = fileName + UUID.randomUUID().toString() + ".xls";
            out = new ByteArrayOutputStream();
            //写入输出流
            e.write(out);
            //转换为输入流
            in = new ByteArrayInputStream(out.toByteArray());
            //文件上传存放路径
            String relativeFilePath = "attachment" + "/commodityexcel" + "/" + CommonUtil.getCurrentYMDStr() + "/" + fileName;
            //上传
            OssUtil.uploadFile2OSS(in, relativeFilePath);
            //返回路径
            return relativeFilePath;
        } catch (UnsupportedEncodingException var41) {
            logger.error("不支持的字符编码");
        } catch (FileNotFoundException var42) {
            logger.error("模板文件找不到");
        } catch (IOException var43) {
            logger.error("IO异常");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var40) {
                    logger.error("上传错误信息失败!");
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var39) {
                    logger.error("上传错误信息失败!");
                }
            }
        }
        return "";
    }

    /**
     * 商品档案
     * 当有行业id传入且 等于12的时候，就是服装行业，需要明细
     *
     * @param
     * @return
     */
    @RequestMapping("/queryCommodityDetailByIndustry")
    @ResponseBody
    public ZLResult queryCommodityDetailByIndustry(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
      /*  params.put("industryid", "12");
        params.put("shopcode", "100000026");
        params.put("spucode", "spu000001");
        params.put("loginuserid","1001");*/
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCommodityService.queryCommodityDetailByIndustry(params);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 当有行业id传入且 等于12的时候，就是服装行业，商品查询页面需要明细
     *　商品查询
     * @param
     * @return
     */
    @RequestMapping("/queryCommoditysDetailByIndustry")
    @ResponseBody
    public ZLResult queryCommoditysDetailByIndustry(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params = getParams();
      /*  params.put("industryid", "12");
        params.put("shopcode", "100000026");
        params.put("spucode", "spu000001");


        params.put("loginuserid","1001");*/
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCommodityService.queryCommoditysDetailByIndustry(params);
        } catch (Exception ex) {
            // 系统级别异常
            logger.info("错误信息是"+ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
