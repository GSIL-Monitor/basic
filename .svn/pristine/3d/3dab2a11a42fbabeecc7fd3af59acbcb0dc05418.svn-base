
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.stms.web.ApitSettleSupplierService;
import com.wgb.util.ExcelUtil;
import com.wgb.util.ExportFiledContants;
import com.wgb.util.ParamsUtil;
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
 * Created by wgy on 2018/4/19.
 */

@Controller
@RequestMapping("/settle/settersupplier")
public class MTSettleSupplierController extends MTBaseController {

    @Autowired
    private ApitSettleSupplierService settleSupplierService;



/**
     * @param @param  request
     * @param @return
     * @param
     * @return ZLResult
     * @throws
     * @Description 新建结算单列表
     * @Title storageCheckDetail
     */

    @RequestMapping("/supplierDetail")
    @ResponseBody
    public ZLResult storageCheckDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = settleSupplierService.getStorageSupplie(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * @param @param  request
     * @param @return
     * @param
     * @return ZLResult
     * @throws
     * @Description 新建结算单
     * @Title addSupplier
     */

    @RequestMapping("/settersupplierAdd")
    @ResponseBody
    public ZLResult addSupplier(HttpServletRequest requests) {
            ZLRpcResult rpcResult;
            Map<String, Object> params = getParams();
            rpcResult = settleSupplierService.addSupplier(params);
        return parseRpcResultForMessage(rpcResult);
    }


/**
     * @param @param  request
     * @param @return
     * @param
     * @return ZLResult
     * @throws
     * @Description 结算单列表
     * @Title getRecSupplier
     */

    @RequestMapping("/settleSupplierDetail")
    @ResponseBody
    public ZLResult getRecSupplier(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = settleSupplierService.getRecsupplierMain(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/supplierAdd")
    @ResponseBody
    public ZLResult addSupplierMain(HttpServletRequest requests) {
        Map<String, Object> params = getParams();
        List<Map<String, Object>> supliers = new ArrayList<Map<String, Object>>();
        supliers.add(params);
        ZLRpcResult rpcResult = settleSupplierService.addSupplierMain(supliers);
        return parseRpcResultForMessage(rpcResult);
    }


/**
     * @param @param  request
     * @param @return
     * @param
     * @return ZLResult
     * @throws
     * @Description 查询结算单详情
     * @Title details
     */

    @RequestMapping("/querySettSupplierBillDetails")
    @ResponseBody
    public ZLResult details(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = settleSupplierService.querySupplierBillDetails(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * @param @param  request
     * @param @return
     * @param
     * @return ZLResult
     * @Description 审核结算单
     * @Title uodateCheckStatus
     * @throwNs
     */

    @RequestMapping("/updateCheckstatus")
    @ResponseBody
    public ZLResult updateCheckStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = settleSupplierService.updateCheckStatus(params);
        return parseRpcResultForMessage(rpcResult);
    }

/**
     * @param @param  request
     * @param @return
     * @param
     * @return ZLResult
     * @throws
     * @Description 删除结算单
     * @Title delete
     */

    @RequestMapping("/delSettSupplier")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        settleSupplierService.delStorageSupplie(params);
        return ZLResult.Success();
    }

    @RequestMapping("/exportSupplierSettle")
    public void exportSupplierSettle(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = settleSupplierService.getRecsupplierMainForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .SUPPLIER_SETTLE_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.SUPPLIER_SETTLE_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .SUPPLIER_SETTLE_FILED
                .values()
                .toArray(new String[ExportFiledContants.SUPPLIER_SETTLE_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "供应商结算单_", request, response);
    }
}

