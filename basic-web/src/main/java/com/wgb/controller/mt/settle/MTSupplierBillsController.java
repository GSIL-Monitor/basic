
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitSupplierBillsService;
import com.wgb.util.ExcelUtil;
import com.wgb.util.ExportFiledContants;
import com.wgb.util.ParamsUtil;
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
 * 供应商对账单
 *
 * @author fxs
 * @create 2018-05-14 14:01
 **/

@Controller
@RequestMapping("/settle/supplierBills")
public class MTSupplierBillsController extends MTBaseController {

    @Autowired
    private ApitSupplierBillsService supplierBillsService;


/**
     * 查询对账单
     * @param request
     * @return
     */

    @RequestMapping("/querySupplierBills")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = supplierBillsService.querySupplierBill(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * 查询对账单详情
     * @param request
     * @return
     */

    @RequestMapping("/querySupplierBillDetails")
    @ResponseBody
    public ZLResult details(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //控制参数
        String billcode = MapUtils.getString(params, "billcode");
        String mainid = MapUtils.getString(params, "mainid");
        if (StringUtils.isEmpty(billcode)) {
            return ZLResult.Error("对账单号未获取到！");
        }
        ZLRpcResult rpcResult = supplierBillsService.querySupplierBillDetails(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * 添加和修改操作
     * @param request
     * @return
     */

    @RequestMapping("/saveOrUpdateSupplierBills")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult;
        if(StringUtils.isEmpty(MapUtils.getString(params,"billcode"))){
            rpcResult = supplierBillsService.saveSupplierBill(params);
        }else {
            params.put("lprxbillcode",MapUtils.getString(params,"billcode"));
            rpcResult = supplierBillsService.updateSupplierBill(params);
        }
        return parseRpcResultForMessage(rpcResult);
    }

/**
     * 审核对账单操作
     * @param request
     * @return
     */

    @RequestMapping("/approvalSupplierBills")
    @ResponseBody
    public ZLResult approvalSupplierBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //控制参数
        String billcode = MapUtils.getString(params, "billcode");
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        //吧DP单号赋给lprxbillcode
        params.put("lprxbillcode",billcode);
        params.put("branchcode", MapUtils.getString(params, "resbranchcode"));
        params.put("branchname", MapUtils.getString(params, "resbranchname"));
        ZLRpcResult rpcResult = supplierBillsService.updateStatsOfSupplierBillMain(params);
        return parseRpcResultForMessage(rpcResult);
    }


/**
     * 删除未审核对账单
     * @param request
     * @return
     */

    @RequestMapping("/deleteSupplierBills")
    @ResponseBody
    public ZLResult deleteSupplierBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult;
        if(StringUtils.isNotEmpty(MapUtils.getString(params,"billcode"))){
            rpcResult = supplierBillsService.deleteSupplierBill(params);
        }else{
            return ZLResult.Error("删除失败,参数缺失！");
        }
        return parseRpcResultForMessage(rpcResult);
    }



/**
     * 审核对账单操作  --------------------放在外面的审核
     * @param request
     * @return
     */

    @RequestMapping("/approvalSupplierBillsNew")
    @ResponseBody
    public ZLResult approvalSupplierBillNew(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //控制参数
        String billcode = MapUtils.getString(params, "billcode");
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        //吧DP单号赋给lprxbillcode
        params.put("lprxbillcode",billcode);
        ZLRpcResult rpcResult = supplierBillsService.updateStatsOfSupplierBillMainNew(params);
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/exportSupplierBills")
    public void exportSupplierBills(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = supplierBillsService.querySupplierBillForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .SUPPLIER_BILL_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.SUPPLIER_BILL_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .SUPPLIER_BILL_FILED
                .values()
                .toArray(new String[ExportFiledContants.SUPPLIER_BILL_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "供应商对账单_", request, response);
    }

}

