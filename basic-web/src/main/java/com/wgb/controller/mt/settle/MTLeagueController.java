
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitLeagueService;
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
 * 加盟结算
 *
 * @author fxs
 * @create 2018-05-14 10:52
 **/

@Controller
@RequestMapping("/settle/league")
public class MTLeagueController extends MTBaseController {

    @Autowired
    private ApitLeagueService leagueService;

    @RequestMapping("/leagueDetail")
    @ResponseBody
    public ZLResult storageOutDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = leagueService.getLeaguePage(params);
        return parseRpcResultForData(rpcResult);
    }


    @RequestMapping("/leaguebillDel")
    @ResponseBody
    public ZLResult leaguebillDel(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = leagueService.delLeague(params);
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult;
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            rpcResult = leagueService.addLeague(params);
        }else{
            rpcResult = leagueService.updateLeague(params);
        }
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/leaguebillToView")
    @ResponseBody
    public ZLResult storageOutToView(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String id = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        ZLRpcResult rpcResult = leagueService.getLeagueMain(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/leaguebillExamine")
    @ResponseBody
    public ZLResult leaguebillExamine(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        params.put("examineflag", "1");
        ZLRpcResult rpcResult = leagueService.updateLeagueExamineById(params);
        return parseRpcResultForMessage(rpcResult);
    }


    @RequestMapping("/exportLeague")
    public void exportLeague(HttpServletRequest request ,HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult rpcResult = leagueService.getLeagueForExport(params);
        List<Map<String ,Object>> exportResult = (List<Map<String ,Object>>)parseRpcResultForData(rpcResult).getData();
        String[] columns = ExportFiledContants
                .FRANCHISEE_SETTLE_FILED
                .keySet()
                .toArray(new String[ExportFiledContants.FRANCHISEE_SETTLE_FILED.keySet().size()]);

        String[] columnNames =  ExportFiledContants
                .FRANCHISEE_SETTLE_FILED
                .values()
                .toArray(new String[ExportFiledContants.FRANCHISEE_SETTLE_FILED.values().size()]);

        ExcelUtil.exportExcel(exportResult, columns, columnNames, "加盟店结算单_", request, response);
    }
}

