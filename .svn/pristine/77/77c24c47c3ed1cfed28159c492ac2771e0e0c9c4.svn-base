package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/5/14.
 */
@Controller
@RequestMapping("/wxshop/commodity")
public class MTWxCommodityController extends MTBaseController {
    @Autowired
    private ApitWxCommodityService apitWxCommodityService;

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 商品列表
     * @Title
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCommodityService.query(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("商品列表 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 获取微信商品分页列表(选择商品用 - 只显示属性为展示的商品)
     * @Title
     */
    @RequestMapping("/queryIsShowCommodityPageList")
    @ResponseBody
    public ZLResult queryIsShowCommodityPageList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCommodityService.queryIsShowCommodityPageList(params);
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
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 是否上线
     * @Title
     */
    @RequestMapping("/updateShow")
    @ResponseBody
    public ZLResult updateShow(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCommodityService.updateShow(params);
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
     * @Description 修改商品
     * @Title
     */
    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCommodityService.update(params);
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
     * @Description 删除商品
     * @Title
     */
    @RequestMapping("/delCommodity")
    @ResponseBody
    public ZLResult delCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCommodityService.delCommodity(params);
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
