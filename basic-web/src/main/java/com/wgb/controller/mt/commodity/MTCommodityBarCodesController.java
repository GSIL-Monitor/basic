package com.wgb.controller.mt.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitCommodityBarcodesService;
import com.wgb.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/commodity/commoditybarcode")
public class MTCommodityBarCodesController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitCommodityBarcodesService apitCommodityBarcodesService;

    @RequestMapping("/queryCommoditybarcode")
    @ResponseBody
    public ZLResult queryCommoditybarcode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityBarcodesService.queryCommoditybarcode(params);
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

//条形码导出
@RequestMapping("/exportBarcode")
@ResponseBody
public void exportBarcode(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> params =  getParams();
    //定义返回对象
    ZLRpcResult zlRpcResult  = new ZLRpcResult();
    try {
        //系统调用
        zlRpcResult = apitCommodityBarcodesService.exportBarcode(params);

    }catch (Exception ex){
        //系统级别异常
        throw new ServiceException(ServiceException.SYS_ERROR);
    }
    //判断返回结果
    if(!zlRpcResult.success()){
        throw  new ServiceException(zlRpcResult.getErrorMsg());
    }
    //导出条形码
    List<Map<String, Object>> manageList = zlRpcResult.getList();
    //处理列表 - 导出到excel

    // 字段名数组
    String[] manages = {"commoditycode", "commodityname", "barcode", "categorycode", "categoryname", "createtime"};

    // 字段名数组
    String[] manageNames = {"货号", "品名", "条码", "类别编码", "类别名称", "创建时间"};

    String manageName = "一品多码查询-汇总表";

    ExcelUtil.exportExcel(manageList, manages, manageNames, manageName, request, response);

}

    @RequestMapping("/queryShopBarcodeRepeat")
    @ResponseBody
    public ZLResult queryShopBarcodeRepeat(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCommodityBarcodesService.queryShopBarcodeCount(params);
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
