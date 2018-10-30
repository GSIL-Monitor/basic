package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMemberAnalyzeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 87357 on 2018/5/31.
 */
@Controller
@RequestMapping("/member/analyze")
public class MTMemberAnalyzeController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberAnalyzeController.class);
    @Autowired
    ApitMemberAnalyzeService apitMemberAnalyzeService;

    /**
     * 会员分析-查询会员
     * @param request
     * @return
     */
    @RequestMapping("/queryMember")
    @ResponseBody
    public ZLResult queryMember(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMemberAnalyzeService.queryMember(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> memberPage = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(memberPage);
    }

    /**
     * 会员分析-查询会员基础信息
     * @param request
     * @return
     */
    @RequestMapping("/queryMemberBasicInfo")
    @ResponseBody
    public ZLResult queryMemberBasicInfo(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMemberAnalyzeService.queryMemberBasicInfo(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> memberBasicInfoMap = (Map<String, Object>)zlRpcResult.getData();

        return ZLResult.Success(memberBasicInfoMap);
    }

    /**
     * 会员分析-更新会员基础信息
     * @param request
     * @return
     */
    @RequestMapping("/updateMemberBasicInfo")
    @ResponseBody
    public ZLResult updateMemberBasicInfo(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMemberAnalyzeService.updateMemberBasicInfo(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 会员分析-查询会员积分记录
     * @param request
     * @return
     */
    @RequestMapping("/queryMemberCreditRecord")
    @ResponseBody
    public ZLResult queryMemberCreditRecord(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMemberAnalyzeService.queryMemberCreditRecord(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> memberCreditRecordPage = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(memberCreditRecordPage);
    }

    /**
     * 会员分析-查询会员购物习惯
     * @param request
     * @return
     */
    @RequestMapping("/queryMemberBuyingHabit")
    @ResponseBody
    public ZLResult queryMemberBuyingHabit(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMemberAnalyzeService.queryMemberBuyingHabit(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> memberBuyingHabitPage = (Map<String, Object>)zlRpcResult.getData();

        return ZLResult.Success(memberBuyingHabitPage);
    }

}
