package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitComponentService;
import com.wgb.service.dubbo.wxms.web.ApitWxShopBuyPlugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/6/29.
 */
@Controller
@RequestMapping("/wxshop/plug")
public class MTWxBuyPlugController extends MTBaseController {
    @Autowired
    private ApitWxShopBuyPlugService apitWxShopBuyPlugService;
    @Autowired
    private ApitComponentService apitComponentService;

    /**
     * @param @param  request
     * @param @return
     * @Description 查询购买插件订单
     * @Title
     */
    @RequestMapping("/queryOrder")
    @ResponseBody
    public ZLResult queryOrder(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            //zlRpcResult = apitWxShopBuyPlugService.queryOrder(params);
            zlRpcResult = apitComponentService.queryComponentOrderForWxms(params);
        } catch (ServiceException ex) {
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
     * 购买插件
     *
     * @param request
     * @return
     */
    @RequestMapping("/buyPlug")
    @ResponseBody
    public ZLResult buyPlug(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxShopBuyPlugService.buyPlug(params);
        } catch (ServiceException ex) {
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
     * 删除未支付订单
     *
     * @param request
     * @return
     */
    @RequestMapping("/delOrder")
    @ResponseBody
    public ZLResult delOrder(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxShopBuyPlugService.del(params);
        } catch (ServiceException ex) {
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
