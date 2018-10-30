
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.stms.web.ApitCustomerBillSumService;
import com.wgb.util.ExcelUtil;
import com.wgb.util.ExportFiledContants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
 * 客户往来账单汇总
 *
 * @author fxs
 * @create 2018-05-14 10:01
 **/

@Controller
@RequestMapping("/settle/customerBillSum")
public class MTCustomerBillSumController extends MTBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MTCustomerBillSumController.class);

    @Autowired
    private ApitCustomerBillSumService customerBillSumService;


/**
     * 综合查询
     * 0.客户往来账单
     * 1.到期账单
     * 2.应收款汇总
     * 3.预收款明细
     * @return
     */

    @RequestMapping("queryCustomerBill")
    @ResponseBody
    public ZLResult queryCustomerBill(){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = null ;
        String type = MapUtils.getString(params, "type");
        if(StringUtils.isNotBlank(type)){
            switch (type){
                case "0":
                    rpcResult = customerBillSumService.queryHistoryCustomerBill(params);
                    break;
                case "1":
                    rpcResult = customerBillSumService.queryHistoryCustomerBill(params);
                    break;
                case "2":
                    rpcResult =  customerBillSumService.queryReceivableBill(params);
                    break;
                case "3":
                    rpcResult =  customerBillSumService.queryCustomerReceipt(params);
                    break;
            }
        }else {
            return ZLResult.Error("type参数缺失");
        }
        return parseRpcResultForData(rpcResult);
    }

    /**
     * 综合导出
     * @return
     */
    @RequestMapping("exportCustomerBill")
    public void queryCustomerBill(HttpServletRequest request , HttpServletResponse response){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = null ;
        String type = MapUtils.getString(params, "type");
        StringBuffer fileName = new StringBuffer();
        Map<String ,String> filed = null;
        if(StringUtils.isNotBlank(type)){
            switch (type){
                case "0":
                    rpcResult = customerBillSumService.queryHistoryCustomerBillForExport(params);
                    fileName.append("客户往来账款＿历史往来账款_");
                    filed = ExportFiledContants.CUSTOMER_HISTORY_BILL_FILED;
                    break;
                case "1":
                    rpcResult =  customerBillSumService.queryHistoryCustomerBillForExport(params);
                    fileName.append("客户往来账款＿到期账款_");
                    filed = ExportFiledContants.CUSTOMER_HISTORY_BILL_FILED;
                    break;
                case "2":
                    rpcResult =  customerBillSumService.queryReceivableBillForExport(params);
                    fileName.append("客户往来账款＿应收账款汇总_");
                    filed = ExportFiledContants.CUSTOMER_RECEIVABLES_FILED;
                    break;
                case "3":
                    rpcResult =  customerBillSumService.queryCustomerReceiptForExport(params);
                    fileName.append("客户往来账款＿预收款明细_");
                    filed = ExportFiledContants.CUSTOMER_RECEIPT_DETAIL_FILED;
                    break;
            }
            List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();

            String[] columns =filed.keySet().toArray(new String[filed.keySet().size()]);
            String[] columnNames = filed.values().toArray(new String[filed.values().size()]);

            ExcelUtil.exportExcel(exportResult, columns, columnNames, fileName.toString(), request, response);
        }else {
           ZLResult.Error("type参数缺失");
        }
    }




/**
     * 历史往来账单
     */

    @RequestMapping("historyCustomerBill")
    @ResponseBody
    public ZLResult queryHistoryCustomerBill(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = customerBillSumService.queryHistoryCustomerBill(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * 应收款账单汇总
     */

    @RequestMapping("queryReceivableBill")
    @ResponseBody
    public ZLResult queryReceivableBill(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = customerBillSumService.queryReceivableBill(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * 预收款明细
     */

    @RequestMapping("queryCustomerReceipt")
    @ResponseBody
    public ZLResult queryCustomerReceipt(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = customerBillSumService.queryCustomerReceipt(params);
        return parseRpcResultForData(rpcResult);
    }
}

