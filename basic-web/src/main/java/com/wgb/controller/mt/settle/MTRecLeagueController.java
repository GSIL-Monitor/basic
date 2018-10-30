
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitRecLeagueService;
import com.wgb.util.Contants;
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
import java.util.List;
import java.util.Map;


/**
 * 加盟对账单
 *
 * @author fxs
 * @create 2018-05-14 10:40
 **/

@Controller
@RequestMapping("/settle/recLeague")
public class MTRecLeagueController extends MTBaseController {

    @Autowired
    private ApitRecLeagueService recLeagueService;

    @RequestMapping("/recLeagueDetail")
    @ResponseBody
    public ZLResult storageOutDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = recLeagueService.getRecLeaguePage(params);
        return parseRpcResultForData(rpcResult);
    }


    @RequestMapping("/recleaguebillDel")
    @ResponseBody
    public ZLResult recleaguebillDel(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("outbranchcode",MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE));
        ZLRpcResult rpcResult = recLeagueService.delRecLeague(params);
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/showRecLeagueBill")
    @ResponseBody
    public ZLResult showStorageInBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = recLeagueService.showRecLeagueGoods(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult;
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            rpcResult = recLeagueService.addRecLeague(params);
        }else{
            rpcResult = recLeagueService.updateRecLeague(params);
        }
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/recleaguebillToView")
    @ResponseBody
    public ZLResult recleaguebillToView(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String id = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        ZLRpcResult rpcResult = recLeagueService.getRecLeagueMain(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/recleaguebillExamine")
    @ResponseBody
    public ZLResult recleaguebillExamine(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("examineflag", "1");
        ZLRpcResult rpcResult = recLeagueService.examineRecLeague(params);
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/exportRecLeague")
    public void exportRecLeague(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = recLeagueService.getRecLeagueForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .FRANCHISEE_STATEMENT_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.FRANCHISEE_STATEMENT_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .FRANCHISEE_STATEMENT_FILED
                .values()
                .toArray(new String[ExportFiledContants.FRANCHISEE_STATEMENT_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "加盟店对账单_", request, response);
    }

}

