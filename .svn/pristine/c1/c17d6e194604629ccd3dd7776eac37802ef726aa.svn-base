package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxSaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 10057 on 2018/5/14.
 */
@Controller
@RequestMapping("/wxshop/order")
public class MTWxSaleOrderController extends MTBaseController {

    @Autowired
    private ApitWxSaleOrderService apitWxSaleOrderService;

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 查询所有订单
     * @Title
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxSaleOrderService.query(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("查询所有订单 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 关闭订单
     * @Title
     */
    @RequestMapping("/closeOrder")
    @ResponseBody
    public ZLResult closeOrder(HttpServletRequest request, HttpServletResponse response) {long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxSaleOrderService.closeOrder(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 验证自提码
     * @Title
     */
    @RequestMapping("/confirmPick")
    @ResponseBody
    public ZLResult confirmPick(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = apitWxSaleOrderService.confirmPick(params);
        if (zlRpcResult.success()) {
            return ZLResult.Success();
        }
        return ZLResult.Error(zlRpcResult.getErrorMsg());
    }

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 商户后台配送完成 修改订单状态至已完成
     * @Title
     */
    @RequestMapping("/completeOrder")
    @ResponseBody
    public ZLResult completeOrder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = apitWxSaleOrderService.completeOrder(params);
        if (zlRpcResult.success()) {
            return ZLResult.Success();
        }
        return ZLResult.Error(ServiceException.getMsg(zlRpcResult.getErrorCode()));
    }






}
