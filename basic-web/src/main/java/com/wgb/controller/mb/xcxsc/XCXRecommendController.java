package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitXCXRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 推荐活动
 */

@Controller
@RequestMapping("/xcxsc/recommend")
public class XCXRecommendController extends MBXCXBaseController {

    @Autowired
    private ApitXCXRecommendService apitXCXRecommendService;

    // 获取活动信息
    @RequestMapping("queryRecommend")
    @ResponseBody
    public ZLResult queryRecommend() {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXRecommendService.queryRecommend(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    // 新增或者更新活动信息
    @RequestMapping("updateRecommend")
    @ResponseBody
    public ZLResult updateRecommend() {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXRecommendService.updateRecommend(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    // 领取活动
    @RequestMapping("addDetail")
    @ResponseBody
    public ZLResult addDetail() {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apitXCXRecommendService.addDetail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

}
