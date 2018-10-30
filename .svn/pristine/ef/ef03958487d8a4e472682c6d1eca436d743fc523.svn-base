package com.wgb.controller.scp.supplier;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitSupplierSaleLogsService;
import com.wgb.util.ExcelUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 曾经沧海 on 2018/8/10.
 */
@Controller
@RequestMapping("/supplier/sale")
public class SCPSupplierSaleLogsController extends SCPBaseController {

    private static final Integer export_size = 10;
    private static final Logger LOGGER  = LoggerFactory.getLogger(SCPSupplierSaleLogsController.class);

    @Autowired
    private ApitSupplierSaleLogsService apitSupplierSaleLogsService;

    @RequestMapping("/queryBillSum")
    @ResponseBody
    public ZLResult queryBillSum(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            LOGGER.info("调用RPC查询销售统计单据统计参数：" ,params);
            zlRpcResult = apitSupplierSaleLogsService.queryBillSum(params);
        } catch (Exception ex) {
            //系统级别异常
            LOGGER.error("销售统计查询单据统计异常！" ,ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportBillSum")
    public void exportBillSum(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        int pagestart = MapUtils.getIntValue(params ,"pagestart");
        int pageend = MapUtils.getIntValue(params ,"pageend");
        try {
            //系统调用
            LOGGER.info("调用RPC查询销售统计单据统计导出参数：" , params);
            List<Map<String ,Object>> exportDataList = new ArrayList<>();
            for (;pagestart <= pageend ;pagestart++) {
                params.put("page" ,pagestart);
                ZLRpcResult rpcResult = apitSupplierSaleLogsService.queryBillSum(params);
                parseExportDate(exportDataList ,rpcResult);
            }
            String[] columns = {"billcode","billtypename","account", "nickname","salenums","salesubtotal","returnnums",
                    "returnsubtotal","realnums","realsubtotal","realgivenums","realgivesubtotal","createtime"};
            String[] columnNames = {"订单编号","单据类型","客户ID","客户名称","销售数量","销售金额","退货数量",
                    "退货金额","实销数量","实销金额","赠送数量","赠送金额","制单日期"};
            ExcelUtil.exportExcel(exportDataList ,columns ,columnNames ,"销售单据汇总_" ,request ,response);
        } catch (Exception ex) {
            //系统级别异常
            LOGGER.error("销售统计单据统计导出查询异常！" ,ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/queryBillSumDetail")
    @ResponseBody
    public ZLResult queryBillSumDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            LOGGER.info("调用RPC查询销售统计明细统计参数：" ,params);
            zlRpcResult = apitSupplierSaleLogsService.queryBillSumDetail(params);
        } catch (Exception ex) {
            LOGGER.error("调用RPC查询销售统计明细统计异常!" ,ex);
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportBillSumDetail")
    public void exportBillSumDetail(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        int pagestart = MapUtils.getIntValue(params ,"pagestart");
        int pageend = MapUtils.getIntValue(params ,"pageend");
        try {
            //系统调用
            LOGGER.info("调用RPC查询销售统计明细统计导出参数：" ,params);
            List<Map<String ,Object>> exportDataList = new ArrayList<>();
            for (;pagestart <= pageend ;pagestart++) {
                params.put("page" ,pagestart);
                ZLRpcResult rpcResult = apitSupplierSaleLogsService.queryBillSumDetail(params);
                parseExportDate(exportDataList ,rpcResult);
            }
            String[] columns = {"billcode","billtypename","account", "nickname","commoditycode","commodityname","categoryname",
                    "spec","unitname","nums","price","subtotal","givenums","givesubtotal"};
            String[] columnNames = {"订单编号","单据类型","客户ID","客户名称","货号","商品名称","商品类别",
                    "规格","单位","数量","售价","小计","赠送数量","赠送金额"};
            ExcelUtil.exportExcel(exportDataList ,columns ,columnNames ,"销售明细汇总_" ,request ,response);
        } catch (Exception ex) {
            //系统级别异常
            LOGGER.error("调用RPC查询销售统计明细统计导出异常!" ,ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }


    @RequestMapping("/queryCommoditySum")
    @ResponseBody
    public ZLResult queryCommoditySum(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            LOGGER.info("调用RPC查询销售统计商品明细参数：" , params);
            zlRpcResult = apitSupplierSaleLogsService.queryCommoditySum(params);
        } catch (Exception ex) {
            //系统级别异常
            LOGGER.error("调用RPC查询销售统计商品明细异常！" ,ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/exportCommoditySum")
    public void exportCommoditySum(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        int pagestart = MapUtils.getIntValue(params ,"pagestart");
        int pageend = MapUtils.getIntValue(params ,"pageend");
        try {
            //系统调用
            LOGGER.info("调用RPC查询销售统计商品明细导出参数：" ,params);
            List<Map<String ,Object>> exportDataList = new ArrayList<>();
            for (;pagestart <= pageend ;pagestart++){
                params.put("page" ,pagestart);
                ZLRpcResult rpcResult = apitSupplierSaleLogsService.queryCommoditySum(params);
                parseExportDate(exportDataList ,rpcResult);
            }
            String[] columns = {"commoditycode","commodityname","categoryname", "spec","unitname","salenums","salesubtotal",
                    "returnnums","returnsubtotal","realnums","realsubtotal","realgivenums","realgivesubtotal"};
            String[] columnNames = {"货号","商品名称","商品分类","商品规格","商品单位","销售数量","销售金额",
                    "退货数量","退货金额","实销数量","实销金额","赠送数量","赠送金额"};
            ExcelUtil.exportExcel(exportDataList ,columns ,columnNames ,"销售商品汇总_" ,request ,response);
        } catch (Exception ex) {
            LOGGER.error("调用RPC查询销售统计商品明细导出异常！" ,ex);
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    private void parseExportDate(List<Map<String ,Object>> exportDateList, ZLRpcResult rpcResult){
        if (null != rpcResult && rpcResult.success()){
            Page<?> pageInfo = (Page<?>)rpcResult.getData();
            if (null != pageInfo){
                List<Map<String ,Object>> exportList = (List<Map<String ,Object>>)pageInfo.getList();
                exportDateList.addAll(exportList);
            }
        }
    }
}
