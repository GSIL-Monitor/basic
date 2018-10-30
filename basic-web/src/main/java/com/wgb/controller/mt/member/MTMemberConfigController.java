package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMemberAssistantRateService;
import com.wgb.service.dubbo.mbms.web.ApitShopConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/member/memberConfig")
public class MTMemberConfigController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberConfigController.class);

    @Autowired
    private ApitShopConfigService apitShopConfigService;


    @Autowired
    private ApitMemberAssistantRateService apitMemberAssistantRateService;


    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = apitShopConfigService.updateConfig(params);

        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = apitShopConfigService.getConfig(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = apitShopConfigService.updateConfig(params);
        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/deleteSaleAssistantRate")
    @ResponseBody
    public ZLResult deleteSaleAssistantRate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitMemberAssistantRateService.deleteSaleAssistantRate(params);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }
}
