package com.wgb.controller.sp.monitor;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitEquipMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgb on 2017/1/23.
 */
@Controller
@RequestMapping("/equipmonitor")
public class EquipMonitorController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipMonitorController.class);

    @Autowired
    private ApitEquipMonitorService apitEquipMonitorService;

    @RequestMapping("/queryFaultDetect")
    @ResponseBody
    public ZLResult queryFaultDetect(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitEquipMonitorService.queryFaultDetect(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询故障检测列表信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryShopDetails")
    @ResponseBody
    public ZLResult queryShopDetails(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitEquipMonitorService.queryShopDetails(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询故障检测商户详情信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryHistoryFaultDetect")
    @ResponseBody
    public ZLResult queryHistoryFaultDetect(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitEquipMonitorService.queryHistoryFaultDetect(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询设备历史上报故障信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/updateShopRepairStatus")
    @ResponseBody
    public ZLResult updateShopRepairStatus(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitEquipMonitorService.updateShopRepairStatus(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询设备历史上报故障信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
