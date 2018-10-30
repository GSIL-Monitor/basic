package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiSaleBillService;
import com.wgb.service.dubbo.stms.web.ApitConsignmentBillService;
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
 * 测试
 *
 * @author fxs
 * @create 2018-05-18 9:30
 **/

@Controller
@RequestMapping("/settle/consignmentBill")
public class MTConsignmentBillController extends MTBaseController {

    @Autowired
    private ApitConsignmentBillService consignmentBillService;

    @Autowired
    private ApiSaleBillService apiSaleBillService;


    /**
     * 查询代销流水单
     * @param request
     * @return
     */
    @RequestMapping("/queryConsignmentBill")
    @ResponseBody
    public ZLResult queryConsignmentBill(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        logger.info("开始代销流水单查询-参数:"+params);
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = consignmentBillService.queryConsignmentBill(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束代销流水单查询用时间:"+(endTime-startTime)+"ms");
        return  parseRpcResultForData(rpcResult);
    }


    /**
     * 添加和修改操作
     * @param request
     * @return
     */

    @RequestMapping("/saveOrUpdateConsignmentBill")
    @ResponseBody
    public ZLResult saveOrUpdateConsignmentBill(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        logger.info("开始saveOrUpdateConsignmentBill-参数:"+params);
        ZLRpcResult rpcResult;
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            rpcResult = consignmentBillService.saveConsignmentBill(params);
        }else {
            rpcResult = consignmentBillService.updateConsignmentBill(params);
        }
        long endTime = System.currentTimeMillis();
        logger.info("结束saveOrUpdateConsignmentBill用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForMessage(rpcResult);
    }


    /**
     * 删除流水单
     * @param request
     * @return
     */

    @RequestMapping("/deleteConsignmentBill")
    @ResponseBody
    public ZLResult deleteConsignmentBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = consignmentBillService.deleteConsignmentBill(params);
        return parseRpcResultForMessage(rpcResult);
    }


    /**
     * 审核代销流水单
     * @param request
     */

    @RequestMapping("/examineConsignmentBill")
    @ResponseBody
    public ZLResult examineConsignmentBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("examineflag","1");
        params.put("recstate","0");
        ZLRpcResult rpcResult = consignmentBillService.updateExamineConsignmentBill(params);
        return parseRpcResultForMessage(rpcResult);
    }


    /**
     * 查询对账单详情
     * @param request
     * @return
     */

    @RequestMapping("/consignmentBillDetail")
    @ResponseBody
    public ZLResult purchaseUpdate(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        logger.info("开始查询对账单详情-参数:"+params);
        String id = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        ZLRpcResult rpcResult = consignmentBillService.getConsignmentBillDetail(params);
        long endTime = System.currentTimeMillis();
        logger.info("结束查询对账单详情用时间:"+(endTime-startTime)+"ms");
        return parseRpcResultForData(rpcResult);
    }


    /**
     * 查询代销销售流水
     * @param request
     */

    @RequestMapping("/querySaleForConsignment")
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
        ZLRpcResult rpcResult1 = consignmentBillService.getAccount(data);
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

    @RequestMapping("/exportConsignmentBill")
    public void exportConsignmentBill(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ParamsUtil.setNewBranchParams(params);
        ZLRpcResult rpcResult = consignmentBillService.queryConsignmentBillForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .CONSIGNMENT_BILL_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.CONSIGNMENT_BILL_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .CONSIGNMENT_BILL_FILED
                .values()
                .toArray(new String[ExportFiledContants.CONSIGNMENT_BILL_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "扣率代销账单_", request, response);

    }


}
