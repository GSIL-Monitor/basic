package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMbAssistantPerformanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 87357 on 2018/7/2.
 */
@Controller
@RequestMapping("/member/performance")
public class MTMbAssistantPerformanceController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMbAssistantPerformanceController.class);

    @Autowired
    ApitMbAssistantPerformanceService apitMbAssistantPerformanceService;

    /**
     * 导购员业绩管理-首页查询
     * @param request
     * @return
     */
    @RequestMapping("/queryMbAssistantPerformance")
    @ResponseBody
    public ZLResult queryMbAssistantPerformance(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMbAssistantPerformanceService.queryMbAssistantPerformance(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException("API导购员业绩管理-首页查询系统调用处理异常，输入参数 =" + params);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    /**
     * 导购员业绩管理-新建或保存更新
     * @param request
     * @return
     */
    @RequestMapping("/updateMbAssistantPerformance")
    @ResponseBody
    public ZLResult updateMbAssistantPerformance(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMbAssistantPerformanceService.saveOrUpdateMbAssistantPerformance(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException("API导购员业绩管理-新建或保存更新系统调用处理异常，输入参数 =" + params);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

}
