package com.wgb.controller.sg.saleassistant;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sg.SGXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitAssistantAchiService;
import com.wgb.service.dubbo.mbms.web.ApitMemberTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 11609
 */
@Controller
@RequestMapping("/assistant")
public class SGXCXAssistantController extends SGXCXBaseController {

    @Autowired
    private ApitMemberTagsService apitMemberTagsService;

    @Autowired
    private ApitAssistantAchiService apitAssistantAchiService;

    /**
     * 查询业绩目标
     *
     * @return
     */
    @RequestMapping("/queryAchievementInfos")
    @ResponseBody
    public ZLResult queryAchievementInfos() {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitAssistantAchiService.queryAchievementInfos(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 查询新进会员和存量会员
     *
     * @return
     */
    @RequestMapping("/queryBindVipInfos")
    @ResponseBody
    public ZLResult queryBindVipInfos() {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitAssistantAchiService.queryBindVipInfos(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 查询会员标签
     *
     * @return
     */
    @RequestMapping("/queryMemberTags")
    @ResponseBody
    public ZLResult queryMemberTags() {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitMemberTagsService.queryMemberTags(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 更新会员标签
     *
     * @return
     */
    @RequestMapping("/updateMemberTags")
    @ResponseBody
    public ZLResult updateMemberTags() {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitMemberTagsService.updateMemberTags(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 查询会员
     *
     * @return
     */
    @RequestMapping("/queryVips")
    @ResponseBody
    public ZLResult queryVips() {
        //获取入参数据
        Map<String, Object> params = getParams();

        params.put("branchcode", params.get("loginuserbranchcode"));
        //定义返回对象
        Map<String, Object> result = new HashMap<>();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitAssistantAchiService.queryVips(params);
            if (!zlRpcResult.success()) {
                return ZLResult.Error(zlRpcResult.getErrorMsg());
            }
            result = zlRpcResult.getMap();
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(result);
    }

    /**
     * 发送优惠券
     *
     * @return
     */
    @RequestMapping("/sendCoupons")
    @ResponseBody
    public ZLResult sendCoupons() {
        //获取入参数据
        Map<String, Object> params = getParams();

        params.put("branchcode", params.get("loginuserbranchcode"));
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> result = new HashMap<>();
        try {
            //系统调用
            zlRpcResult = apitAssistantAchiService.sendCoupons(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

}
