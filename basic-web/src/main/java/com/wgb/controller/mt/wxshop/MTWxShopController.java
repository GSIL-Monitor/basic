package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxShopService;
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
@RequestMapping("/wxshop/config")
public class MTWxShopController extends MTBaseController {

    @Autowired
    private ApitWxShopService apitWxShopService;

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 查询商户信息
     * @Title
     */
    @RequestMapping("/qryShopInfo")
    @ResponseBody
    public ZLResult qryShopInfo(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        // 校验参数
        try {
            // 系统调用
            zlRpcResult = apitWxShopService.qryShopInfo(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("查询商户信息 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 设置微店信息（商户级别）
     * @Title
     */
    @RequestMapping("/toUpdate")
    @ResponseBody
    public ZLResult toUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        // 校验参数
        try {
            // 系统调用
            zlRpcResult = apitWxShopService.toUpdate(params);
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
     * @Description 设置短信通知开关
     * @Title
     */
    @RequestMapping("/updateBranchSMSStatus")
    @ResponseBody
    public ZLResult updateBranchSMSStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        // 校验参数
        try {
            // 系统调用
            zlRpcResult = apitWxShopService.updateBranchSMSStatus(params);
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
}
