package com.wgb.controller.scp.xcx;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.scpms.web.ApitPubScpService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 曾经沧海 on 2018/8/21.
 */
@Controller
@RequestMapping("/entry/supplier/xcxPubScp")
public class ScpXcxPubScpController extends SCPXCXBaseController {

    @Autowired
    private ApitPubScpService apitPubScpService;

    @RequestMapping("/getCommodityScpInfo")
    @ResponseBody
    public ZLResult getCommodityScpInfo(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitPubScpService.getCommodityScpInfo(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getScpIndustryList")
    @ResponseBody
    public ZLResult getScpIndustry(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitPubScpService.getScpIndustryList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

}
