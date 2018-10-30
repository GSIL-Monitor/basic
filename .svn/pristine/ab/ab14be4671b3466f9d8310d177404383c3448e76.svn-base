
package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.osrms.admin.ApiDadaService;
import com.wgb.service.dubbo.wxms.web.ApitCsDaDaService;
import com.wgb.service.dubbo.wxms.web.ApitXCXOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/22 0022.
 */

@Controller
@RequestMapping("/xcxsc/order")
public class XCXOrderController extends MBXCXBaseController {

    @Autowired
    private ApitXCXOrderService apitXCXOrderService ;

    @Autowired
    private ApitCsDaDaService apitCsDaDaService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/getDefaultAddress")
    @ResponseBody
    public ZLResult edit(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.getDefaultAddress(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/home")
    @ResponseBody
    public ZLResult homepage(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.homepage(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    //注册会员
    @RequestMapping("/bindMember")
    public ZLResult bindMember(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.bindMember(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        //获取该用户的会员信息
        return ZLResult.Success(zlRpcResult.getData());
    }

    //会员信息页面
    @RequestMapping("/memberInfo")
    @ResponseBody
    public ZLResult memberInfo(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.memberInfo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    //会员充值页面
    @RequestMapping("/memberRecharge")
    @ResponseBody
    public ZLResult memberRecharge(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.memberRecharge(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 获取配送费
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/getDeliveryFee")
    @ResponseBody
    public ZLResult getDeliveryFee(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.getDeliveryFee(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }


    //自配送查询运费
    @RequestMapping("/queryzpsdeliveryfee")
    @ResponseBody
    public ZLResult queryzpsdeliveryfee(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsDaDaService.getZpsFee(params);
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
     * 保存订单
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/saveorder")
    @ResponseBody
    public ZLResult saveorder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.saveorder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员订单
     *
     * @param request
     * @return
     */

    @RequestMapping("/orderlist")
    @ResponseBody
    public ZLResult orderList(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.orderlist(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 订单详情
     *
     * @param request
     * @return
     */

    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult detail(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.detail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 支付成功后
     *
     * @param request
     * @return
     */

    @RequestMapping("/paysuccess")
    @ResponseBody
    public ZLResult paysuccess(HttpServletRequest request) {
        //获取一些门店的相关信息
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.paysuccess(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }


    //微信充值成功后，回调这个接口把充值记录保存到mb_log_recharge中
    @RequestMapping("/rechargesuccess")
    @ResponseBody
    public Object rechargesuccess(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.rechargesuccess(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return RpcSuccessResult(zlRpcResult);
    }

    @RequestMapping("/cancleorder")
    @ResponseBody
    public ZLResult cancleorder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.cancleorder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    @RequestMapping("/deleteorder")
    @ResponseBody
    public ZLResult deleteorder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.deleteorder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    //申请退货页面
    @RequestMapping("/applyReturn")
    @ResponseBody
    public ZLResult applyReturn(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXOrderService.applyReturn(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }


}