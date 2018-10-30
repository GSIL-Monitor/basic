package com.wgb.controller.mt.bossassistant;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.osrms.web.ApitMonitorPassengerFlowService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 老板助手-客流统计服务
 * Created by lzy on 2018/8/6.
 */
@Controller
@RequestMapping("/boss/camera/passenger")
public class MTPassengerFlowDeviceController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTPassengerFlowDeviceController.class);

    @Autowired
    private ApitMonitorPassengerFlowService apitMonitorPassengerFlowService;

    /**
     * 老板助手查询设备信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/flow/device")
    @ResponseBody
    public ZLResult getMonitorDeviceDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult =  apitMonitorPassengerFlowService.getMonitorDeviceDetail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 老板助手查询设备信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/live/address")
    @ResponseBody
    public ZLResult getLiveAddress(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitMonitorPassengerFlowService.getLiveAddress(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 老板助手查询设备信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/flow/passengerdevice")
    @ResponseBody
    public ZLResult getPassengerMonitorDeviceDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitMonitorPassengerFlowService.getPassengerMonitorDeviceDetail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }
}
