
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.stms.web.ApitCustomerSettleService;
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
 * 客户结算
 *
 * @author fxs
 * @create 2018-05-14 9:32
 **/

@Controller
@RequestMapping("/settle/customerSettle")
public class MTCustomerSettleController extends MTBaseController {

    @Autowired
    private ApitCustomerSettleService customerSettleService;

    // 增
    @RequestMapping("saveCustomerSettle")
    @ResponseBody
    public ZLResult saveCustomerSettle(){
        Map<String, Object> params = getParams();
        String id = MapUtils.getString(params, "id");
        String message;
        ZLRpcResult rpcResult;
        if (StringUtils.isNotBlank(id)){
            rpcResult = customerSettleService.updateCustomerSettleById(params);
        }else {
            rpcResult = customerSettleService.saveCustomerSettle(params);
        }
        return parseRpcResultForMessage(rpcResult);
    }

    // 改
    @RequestMapping("updateCustomerSettle")
    @ResponseBody
    public ZLResult updateCustomerSettle(){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");

        Map<String, Object> params = getParams();
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerSettleService.updateCustomerSettleById(params);
        return parseRpcResultForMessage(rpcResult);
    }

    // 删
    @RequestMapping("deleteCustomerSettle")
    @ResponseBody
    public ZLResult deleteCustomerSettle(HttpServletRequest request){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerSettleService.deleteCustomerSettle(params);
        return parseRpcResultForData(rpcResult);
    }


    // 查
    @RequestMapping("queryCustomerSettle")
    @ResponseBody
    public ZLResult queryCustomerReceipt(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = customerSettleService.queryCustomerSettle(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("updateAudit")
    @ResponseBody
    public ZLResult updateAudit(HttpServletRequest request){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        params.put("auditstatus" ,"1");
        ZLRpcResult rpcResult = customerSettleService.updateAuditById(params);
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("queryCustomerSettleDetail")
    @ResponseBody
    public ZLResult queryCustomerReceiptDetail(HttpServletRequest request){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        Map<String, Object> params = getParams();
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerSettleService.queryCustomerSettleDetail(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     *  查询单据(退货销售单)
     */

    @RequestMapping("queryCustomerBill")
    @ResponseBody
    public ZLResult queryCustomerBill(HttpServletRequest request){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("customercode");
        keys.add("branchcode");
        Map<String, Object> params = getParams();
        if(!checkParam(params,keys)){
            return ZLResult.Error("参数缺失");
        }
        ZLRpcResult rpcResult = customerSettleService.queryCustomerBill(params);
        return parseRpcResultForData(rpcResult);
    }


    // 导出
    @RequestMapping("exportCustomerSettle")
    public void exportCustomerReceipt(HttpServletResponse response , HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = customerSettleService.queryCustomerSettleForExoprt(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .CUSTOMER_SETTLE_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.CUSTOMER_SETTLE_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .CUSTOMER_SETTLE_FILED
                .values()
                .toArray(new String[ExportFiledContants.CUSTOMER_SETTLE_FILED.values().size()]);
        ExcelUtil.exportExcel(exportResult, columns, columnNames, "客户结算单_", request, response);
    }

}

