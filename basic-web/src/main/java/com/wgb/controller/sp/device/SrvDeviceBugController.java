package com.wgb.controller.sp.device;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitDeviceBugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/1/23.
 */
@Controller
@RequestMapping("/device/bug")
public class SrvDeviceBugController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvDeviceBugController.class);

    @Autowired
    private ApitDeviceBugService apitDeviceBugService;

    @RequestMapping("/queryCollectDeviceInfo")
    @ResponseBody
    public ZLResult queryCollectDeviceInfo(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitDeviceBugService.queryCollectDeviceInfo(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询硬件检测汇总信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }


    @RequestMapping("/getDeviceDataCount")
    @ResponseBody
    public ZLResult getDeviceDataCount(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitDeviceBugService.getDeviceDataCount(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询在线设备和故障设备总计系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    /**
     * 查询在线设备
     * @param request
     * @return
     */
    @RequestMapping("/getOnlineDeviceAnalyzeData")
    @ResponseBody
    public ZLResult getOnlineDeviceAnalyzeData(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitDeviceBugService.getOnlineDeviceAnalyzeData(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询在线设备系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/getHardwareBugCountData")
    @ResponseBody
    public ZLResult getHardwareBugCountData(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitDeviceBugService.getHardwareBugCountData(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询硬件故障系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
