package com.wgb.controller.mb.wxms;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCsRecommendService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/8/9.
 */
@Controller
@RequestMapping("/wxcs/recommend")
public class MBCsRecommendController extends MBBaseController {
    @Autowired
    private ApitCsRecommendService apitCsRecommendService;

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // 领取、添加活动任务
    // ?recommendid=&shopcode=&branchcode=&openidfrom=&openidto=
    @RequestMapping("/addDetail")
    @ResponseBody
    public ZLResult addDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsRecommendService.addDetail(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryRecommend")
    @ResponseBody
    public ZLResult queryRecommend(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsRecommendService.queryRecommend(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
