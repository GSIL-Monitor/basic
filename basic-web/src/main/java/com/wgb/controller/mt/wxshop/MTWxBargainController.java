package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxBargainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 砍价
AS */

@Controller
@RequestMapping("/wxshop/bargain")
public class MTWxBargainController extends MTBaseController {

    @Autowired
    private ApitWxBargainService apitWxBargainService;

    /**
     * 查询砍价活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPageList")
    @ResponseBody
    public ZLResult queryPageList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxBargainService.queryPageList(params);
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
     * 新增砍价活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/addBargain")
    @ResponseBody
    public ZLResult addBargain(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            apitWxBargainService.addBargain(params);
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
     * 更新砍价活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateBargain")
    @ResponseBody
    public ZLResult updateBargain(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            apitWxBargainService.updateBargain(params);
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
     * 删除砍价活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/delBargain")
    @ResponseBody
    public ZLResult delBargain(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxBargainService.delBargain(params);
            logger.info("点击删除砍价活动的返回值："+zlRpcResult);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            logger.info("错误提示："+zlRpcResult.getErrorMsg());
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /**
     * 更新砍价活动状态
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateBargainStatus")
    @ResponseBody
    public ZLResult updateBargainStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            apitWxBargainService.updateBargainStatus(params);
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
