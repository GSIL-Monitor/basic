
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiSaleBillService;
import com.wgb.service.dubbo.stms.web.ApitUnionBillService;
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
 * 联营账单
 *
 * @author fxs
 * @create 2018-05-14 11:07
 **/

@Controller
@RequestMapping("/settle/unionBill")
public class MTUnionBillController extends MTBaseController {


    @Autowired
    private ApitUnionBillService apitUnionBillService;

    @Autowired
    private ApiSaleBillService apiSaleBillService;


/**
     * 查询联营流水单
     * @param request
     * @return
     */

    @RequestMapping("/queryUnionBills")
    @ResponseBody
    public ZLResult queryConsignmentBills(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = apitUnionBillService.queryUnionBill(params);
        return  parseRpcResultForData(rpcResult);
    }


/**
     * 添加和修改操作
     * @param request
     * @return
     */

    @RequestMapping("/saveOrUpdateUnionBills")
    @ResponseBody
    public ZLResult saveOrUpdateUnionBills(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult;
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            rpcResult = apitUnionBillService.saveUnionBill(params);
        }else {
            rpcResult = apitUnionBillService.updateUnionBill(params);
        }
        return parseRpcResultForMessage(rpcResult);
    }


/**
     * 删除对账单
     * @param request
     * @return
     */

    @RequestMapping("/deleteUnionBills")
    @ResponseBody
    public ZLResult deleteUnionBills(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = apitUnionBillService.deleteUnionBill(params);
        return parseRpcResultForMessage(rpcResult);
    }


/**
     * 审核对账单
     * @param request
     */

    @RequestMapping("/examineUnionBills")
    @ResponseBody
    public ZLResult examineUnionBills(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("examineflag","1");
        params.put("recstate","0");
        ZLRpcResult rpcResult = apitUnionBillService.updateExamineUnionBill(params);
        return parseRpcResultForMessage(rpcResult);
    }


/**
     * 查询对账单详情
     * @param request
     * @return
     */

    @RequestMapping("/unionBillDetails")
    @ResponseBody
    public ZLResult unionBillDetails(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String id = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        ZLRpcResult rpcResult = apitUnionBillService.getUnionBillDetail(params);
        return parseRpcResultForData(rpcResult);
    }


/**
     * 查询联营销售流水
     * @param request
     */

    @RequestMapping("/querySaleForUnions")
    @ResponseBody
    public ZLResult querySaleForConsignment(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        if (StringUtils.isEmpty(MapUtils.getString(params,"suppliercode"))) {
            return ZLResult.Error("未选择供应商");
        }
        addParams(params);
        ZLRpcResult rpcResult=apiSaleBillService.queryBillsForStms(params);
        List<Map<String,Object>> data=rpcResult.getList();
        if(data.isEmpty()){
            return ZLResult.Success("暂无数据");
        }
//        结算
        ZLRpcResult rpcResult1 = apitUnionBillService.getAccount(data);
        return parseRpcResultForData(rpcResult1);
    }


    private void addParams(Map<String,Object> params){
        String loginuesrishead= MapUtils.getString(params,"loginuserbranchishead");
        String loginuserbranchcode=MapUtils.getString(params,"loginuserbranchcode");
        if(StringUtils.equals(loginuesrishead,"0")){
            params.put("branchcode",loginuserbranchcode);
        }else if(StringUtils.isEmpty(MapUtils.getString(params,"branchcode")) && StringUtils.equals(loginuesrishead,"1")){
            params.put("branchcode",loginuserbranchcode);
        }
        params.put("recstate","0");
    }

    @RequestMapping("/exportUnionBills")
    public void exportUnionBills(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = apitUnionBillService.queryUnionBillForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .JOINT_OPERATION_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.JOINT_OPERATION_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .JOINT_OPERATION_FILED
                .values()
                .toArray(new String[ExportFiledContants.JOINT_OPERATION_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "联营账单_", request, response);
    }


}

