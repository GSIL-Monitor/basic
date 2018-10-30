package com.wgb.controller.sg.saleassistant;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sg.SGXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.mbms.web.ApitMemberAssistantService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/1.
 */
@Controller
@RequestMapping("/member/memberassistant")
public class SGXCXSaleAssistanrMemberController extends SGXCXBaseController {

    @Autowired
    private ApitMemberAssistantService apitMemberAssistantService;

    //首页
    @RequestMapping("/homepage")
    @ResponseBody
    public ZLResult homepage(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.queryAssistantMemberPage(params);

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

    //首页-列表
    @RequestMapping("/homepageDetail")
    @ResponseBody
    public ZLResult homepageDetail(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.queryAssistantMemberPageDetail(params);

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

    //会员
    @RequestMapping("/queryAssistantMemberInfo")
    @ResponseBody
    public ZLResult queryAssistantMemberInfo(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.queryAssistantMemberInfo(params);

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

    //会员-列表
    @RequestMapping("/queryAssistantMemberInfoDetail")
    @ResponseBody
    public ZLResult queryAssistantMemberInfoDetail(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.queryAssistantMemberInfoDetail(params);

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

    //查询未绑定会员
    @RequestMapping("/queryUnbindMember")
    @ResponseBody
    public ZLResult queryUnbindMember(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.queryUnbindMember(params);

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

    //绑定会员和导购员
    @RequestMapping("/bindMemberAssistant")
    @ResponseBody
    public ZLResult bindMemberAssistant(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.bindMemberAssistant(params);

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


    //会员维护(查询)
    @RequestMapping("/maintainMemberInfo")
    @ResponseBody
    public ZLResult maintainMemberInfo(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.maintainMemberInfo(params);

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

    //会员维护(修改)
    @RequestMapping("/updateMaintainMemberInfo")
    @ResponseBody
    public ZLResult updateMaintainMemberInfo(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.updateMaintainMemberInfo(params);

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

    //导购员激活会员
    @RequestMapping("/assistantActiveMember")
    @ResponseBody
    public ZLResult assistantActiveMember(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAssistantService.assistantActiveMember(params);

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
