package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/4 0004.
 */

@Controller
@RequestMapping("/xcxsc/pay")
public class XCXWxPayController extends MBXCXBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitWxPayService apitWxPayService;

    @RequestMapping("/memberpay")
    @ResponseBody
    public ZLResult memberpay() {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitWxPayService.memberpay(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }


    /**
     * 次卡支付
     */

    @RequestMapping("/preferentialpay")
    @ResponseBody
    public ZLResult preferentialpay(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitWxPayService.preferentialpay(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }


    /**
     * 次卡/优惠券退款
     */

    @RequestMapping("/preferential")
    @ResponseBody
    public ZLResult preferential(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitWxPayService.preferential(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }
}

