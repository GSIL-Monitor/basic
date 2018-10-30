package com.wgb.controller.mb.wxms;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCsBargainService;
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
@RequestMapping("/wxcs/bargain")
public class MBCsBargainController extends MBBaseController {
    //砍价列表
    @Autowired
    private ApitCsBargainService apitCsBargainService;

    @RequestMapping("/queryBargainPageList")
    @ResponseBody
    public ZLResult queryBargainPageList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsBargainService.queryBargainPageList(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData()); //返回值是page<>
    }

    //发起砍价
    @RequestMapping("/startBargain")
    @ResponseBody
    public ZLResult startBargain(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsBargainService.startBargain(params);
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

    //砍价详情
    @RequestMapping("/getBargainRegistrationDetail")
    @ResponseBody
    public ZLResult getBargainRegistrationDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            // 系统调用
            zlRpcResult = apitCsBargainService.getBargainRegistrationDetail(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }


    //帮忙砍价
    @RequestMapping("/helpBargain")
    @ResponseBody
    public ZLResult helpBargain(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsBargainService.helpBargain(params);
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

    /*
  * 分享砍价活动
  * 提供前端调微信JS接口所需参数
  * */
    @RequestMapping("/weixinShareForBargain")
    @ResponseBody
    public ZLResult weixinShareForBargain(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            // 系统调用
            zlRpcResult = apitCsBargainService.weixinShareForBargain(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }


}
