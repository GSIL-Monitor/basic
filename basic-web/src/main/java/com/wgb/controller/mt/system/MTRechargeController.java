package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitRechargeOrderService;
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
@RequestMapping("/system/recharge")
public class MTRechargeController extends MTBaseController {

    @Autowired
    private ApitRechargeOrderService apitRechargeOrderService;

    @RequestMapping("/delBill")
    @ResponseBody
    public ZLResult delBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        try {
        apitRechargeOrderService.delBill(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success();
    }

    @RequestMapping("/closeBill")
    @ResponseBody
    public ZLResult closeBill(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        try {
        apitRechargeOrderService.closeBill(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success();
    }

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitRechargeOrderService.query(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryOrderList")
    @ResponseBody
    public ZLResult queryOrderList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitRechargeOrderService.queryOrderList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/rechargeOrderUpdateDetail")
    @ResponseBody
    public ZLResult rechargeOrderUpdateDetails(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitRechargeOrderService.rechargeOrderUpdateDetails(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        boolean success = zlRpcResult.success();
        if (success) {
            return ZLResult.Success(zlRpcResult.getData());
        } else {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
    }

    @RequestMapping("/toRechargeOrder")
    @ResponseBody
    public ZLResult toRechargeOrder(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitRechargeOrderService.toRechargeOrder(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitRechargeOrderService.addRecharge(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        boolean success = zlRpcResult.success();
        if (success) {
            return ZLResult.Success(zlRpcResult.getData());
        } else {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
    }

    @RequestMapping("/updateBusinessInfo")
    @ResponseBody
    public ZLResult updateBusinessInfo(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitRechargeOrderService.updateBusinessInfo(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
