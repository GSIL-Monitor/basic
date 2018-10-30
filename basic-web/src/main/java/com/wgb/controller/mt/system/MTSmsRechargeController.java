package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.sms.web.ApitRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lzy on 2018/5/15.
 */
@Controller
@RequestMapping("/sms/recharge")
public class MTSmsRechargeController extends MTBaseController {

    @Autowired
    private ApitRechargeService apitRechargeService;


    /**
     * 短信订单管理-删除
     *
     * @param request
     * @return
     */
    @RequestMapping("/delBill")
    @ResponseBody
    public ZLResult delBill(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            apitRechargeService.deleteBill(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }


        return ZLResult.Success();
    }

    /**
     * 短信订单管理-查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/orderListManage")
    @ResponseBody
    public ZLResult ordeList(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitRechargeService.getOrderList(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }


        return ZLResult.Success((Page<?>) zlRpcResult.getData());
    }

    /**
     * 短信订单管理 - 查询短信配置
     *
     * @param request
     * @return
     */
    @RequestMapping("/orderConfig")
    @ResponseBody
    public ZLResult orderConfig(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitRechargeService.getOrderConfig(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }


        return ZLResult.Success(zlRpcResult.getMap());
    }

    /**
     * 短信充值-查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/toSmsRechargeDetail")
    @ResponseBody
    public ZLResult toPay(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitRechargeService.getPubSmsConfig(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }


        return ZLResult.Success(zlRpcResult.getList());
    }

    /**
     * 短信充值-提交,选择支付方式
     *
     * @param request
     * @return
     */
    @RequestMapping("/choosePaytype")
    @ResponseBody
    public ZLResult paySelect(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitRechargeService.paySelect(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }


        return ZLResult.Success(zlRpcResult.getMap());
    }

}
