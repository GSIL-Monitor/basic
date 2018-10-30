
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.stms.web.ApitCustomerReceiptService;
import com.wgb.util.ExcelUtil;
import com.wgb.util.ExportFiledContants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
 * 客户预存款
 *
 * @author fxs
 * @create 2018-05-14 9:52
 **/

@Controller
@RequestMapping("/settle/customerReceipt")
public class MTCustomerReceiptController extends MTBaseController {

    @Autowired
    private ApitCustomerReceiptService customerReceiptService;

    // 新增
    @RequestMapping("saveCustomerReceipt")
    @ResponseBody
    public ZLResult saveCustomerReceipt(){
        long startTime = System.currentTimeMillis();
        ZLRpcResult rpcResult;
        Map<String, Object> params = getParams();
        logger.info("开始新增查询-参数:"+params);
        if(StringUtils.isNotBlank(MapUtils.getString(params ,"id"))){
            rpcResult = customerReceiptService.updateCustomerReceiptById(params);
        }else{
            rpcResult = customerReceiptService.saveCustomerReceipt(params);
        }
        long endTime = System.currentTimeMillis();
        logger.info("结束新增用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForMessage(rpcResult);
    }

    // 修改
    @RequestMapping("updateCustomerReceipt")
    @ResponseBody
    public ZLResult updateCustomerReceipt(){
        long startTime = System.currentTimeMillis();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        logger.info("开始修改-参数:"+params);
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerReceiptService.updateCustomerReceiptById(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束修改用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForMessage(rpcResult);
    }

    // 删除
    @RequestMapping("deleteCustomerReceipt")
    @ResponseBody
    public ZLResult deleteCustomerReceipt(HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        logger.info("开始deleteCustomerReceipt-参数:"+params);
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerReceiptService.deleteCustomerReceipt(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束deleteCustomerReceipt用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForMessage(rpcResult);
    }


    // 查寻
    @RequestMapping("queryCustomerReceipt")
    @ResponseBody
    public ZLResult queryCustomerReceipt(HttpServletRequest request){

        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        logger.info("开始queryCustomerReceipt-参数:"+params);
        ZLRpcResult rpcResult = customerReceiptService.queryCustomerReceipt(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束queryCustomerReceipt用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("updateAudit")
    @ResponseBody
    public ZLResult updateAudit(HttpServletRequest request){
        long startTime  = System.currentTimeMillis();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        logger.info("开始updateAudit-参数:"+params);
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        params.put("auditstatus" ,"1");
        ZLRpcResult rpcResult = customerReceiptService.updateAuditById(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束updateAudit用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("queryCustomerReceiptDetail")
    @ResponseBody
    public ZLResult queryCustomerReceiptDetail(HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        logger.info("开始queryCustomerReceiptDetail-参数:"+params);
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerReceiptService.queryCustomerReceiptDetailById(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束queryCustomerReceiptDetail用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForData(rpcResult);
    }


    // 导出
    @RequestMapping("exportCustomerReceipt")
    public void exportCustomerReceipt(HttpServletResponse response ,HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        logger.info("开始导出-参数:"+params);
        ZLRpcResult rpcResult = customerReceiptService.queryCustomerReceiptForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .CUSTOMER_RECEIPT_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.CUSTOMER_RECEIPT_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .CUSTOMER_RECEIPT_FILED
                .values()
                .toArray(new String[ExportFiledContants.CUSTOMER_RECEIPT_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "客户预收款_", request, response);
        long endTime = System.currentTimeMillis();
        logger.info("结束导出用时间:"+(endTime-startTime)+"ms");
    }

}

