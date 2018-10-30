package com.wgb.controller.mt.wholesale;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitOrderCommoditySearchService;
import com.wgb.util.ExcelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/wholesale/wholesale")
public class MTOrderCommoditySearchController extends MTBaseController {

    @Autowired
    private ApitOrderCommoditySearchService apitOrderCommoditySearchService;

    @RequestMapping("/queryWholesaleOrders")
    @ResponseBody
    public ZLResult queryWholesaleOrders(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitOrderCommoditySearchService.queryWholesaleOrders(params);

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

    @RequestMapping("/searchOrderCommodity")
    @ResponseBody
    public ZLResult searchOrderCommodity(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitOrderCommoditySearchService.searchOrderCommodity(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("查询订单商品 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    /*导出订单商品查询*/
    @RequestMapping("/exportOrderCommondityList")
    @ResponseBody
    public ZLResult exportOrderCommondityList(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params = getParams();
        //获取行业id
        String industryid=MapUtils.getString(params,"industryid");
        String[] purchase= new String[]{};
        String[] purchaseNames=new String[]{};
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitOrderCommoditySearchService.exportOrderCommondityList(params);
            formatForExport(zlRpcResult.getList());
            if(StringUtils.equals(industryid,"12")){
                 purchase = new String[]{"billcode", "billtype", "customercode", "customername", "storehousename", "validornot",
                         "effectivedate", "billstatuss", "spucode","barcode", "commodityname", "categoryname","specvalue",
                         "spec", "nums", "subtotalprice", "hasshipped", "leftnums", "givenums", "shippingrate"};
                // 字段名数组
                purchaseNames = new String[]{"业务单号", "单据类型", "客户编码", "客户名称", "仓库", "有效性",
                        "有效日期", "出货情况", "货号","条形码", "商品名称", "商品类别","颜色/尺码",
                        "规格", "订单商品数量", "订单商品金额", "已出货数量", "未出货数量", "赠送数量", "出货率"};
            }else {
                 purchase = new String[]{"billcode", "billtype", "customercode", "customername", "storehousename", "validornot",
                         "effectivedate", "billstatuss", "commoditycode", "commodityname", "categoryname",
                         "spec", "nums", "subtotalprice", "hasshipped", "leftnums", "givenums", "shippingrate"};
                // 字段名数组
                 purchaseNames = new String[]{"业务单号", "单据类型", "客户编码", "客户名称", "仓库", "有效性",
                         "有效日期", "出货情况", "货号", "商品名称", "商品类别",
                         "规格", "订单商品数量", "订单商品金额", "已出货数量", "未出货数量", "赠送数量", "出货率"};
            }
            String purchaseName = "订单商品_";
            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
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

    private void formatForExport(List<Map<String, Object>> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> supplier : list) {
                String flag = MapUtils.getString(supplier, "billstatus", "");
                if (flag.equals("0")) {
                    supplier.put("billstatuss", "未销售");
                } else if (flag.equals("1")) {
                    supplier.put("billstatuss", "部分销售");
                } else {
                    supplier.put("billstatuss", "已销售");
                }

            }
        }
    }

}
