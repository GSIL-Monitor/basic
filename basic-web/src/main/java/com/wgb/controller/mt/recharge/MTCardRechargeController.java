package com.wgb.controller.mt.recharge;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiCardRechargeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/2/7.
 */

@Controller
@RequestMapping("/recharge")
public class MTCardRechargeController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiCardRechargeOrderService apiCardRechargeOrderService;

    @RequestMapping("/cardRecharge")
    @ResponseBody
    public ZLResult memberRecharge(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCardRechargeOrderService.saveCardRechargeOrder(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorCode());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/cardRechargeLog")
    @ResponseBody
    public ZLResult cardRechargeList(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        ZLRpcResult rpcResult = null;
        try {
            rpcResult = apiCardRechargeOrderService.queryrRechargeLog(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!rpcResult.success()) {
            return ZLResult.Error(ServiceException.getMsg(rpcResult.getErrorCode()));
        }

        return ZLResult.Success(rpcResult.getData());
    }

}
