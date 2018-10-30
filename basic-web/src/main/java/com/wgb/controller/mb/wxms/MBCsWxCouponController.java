package com.wgb.controller.mb.wxms;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCsCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/wxcs/coupon")
public class MBCsWxCouponController extends MBBaseController {

    @Autowired
    private ApitCsCouponService apitCsCouponService;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 优惠券--查询所有可见的优惠券
     * @params shopcode、membermodel
     *
     */
    @RequestMapping("/queryCoupon")
    @ResponseBody
    public Object queryCoupon(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.queryCoupon(params);
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

    /**
     * 优惠券--领取优惠券
     * @params couponname shopcode 和 branchcode
     *
     */
    @RequestMapping("/getCoupon")
    @ResponseBody
    public Object getCoupon(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.getCoupon(params);
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



    /**
     * 优惠券--可用优惠券的查询
     * @params
     *
     */
    @RequestMapping("/queryUsableCoupon")
    @ResponseBody
    public Object queryUsableCoupon(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.queryUsableCoupon(params);
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


    /**
     * 优惠券--优惠券使用记录
     * @params
     *
     */
    @RequestMapping("/queryCouponUseRecord")
    @ResponseBody
    public Object queryCouponUseRecord(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.queryCouponUseRecord(params);
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


    /**
     * 优惠券--查询优惠券详情
     * @params
     *
     */
    @RequestMapping("/queryCouponDetail")
    @ResponseBody
    public Object queryCouponDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.queryCouponDetail(params);
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

    /**
     * 优惠券--下拉选的优惠券展示
     * @params
     *
     */
    @RequestMapping("/queryWeChatCouponDropDown")
    @ResponseBody
    public Object queryWeChatCouponDropDown(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.queryWeChatCouponDropDown(params);
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

    /**
     * 优惠券--使用优惠券
     * @params
     *
     */
    @RequestMapping("/useCoupon")
    @ResponseBody
    public Object useCoupon(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = (ZLRpcResult) apitCsCouponService.useCoupon(params);
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
