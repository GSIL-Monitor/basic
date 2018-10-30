package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitShopConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by whq on 2018/4/19.
 */
@Controller
@RequestMapping("/system/shopconfig")
public class MTShopConfigController extends MTBaseController {

    @Autowired
    private ApitShopConfigService apitShopConfigServiceURMS;

    @RequestMapping("/updateTemplate")
    @ResponseBody
    public ZLResult updateTemplate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitShopConfigServiceURMS.updateTemplate(params);

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

    @RequestMapping("/queryInitTemplate")
    @ResponseBody
    public ZLResult queryInitTemplate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitShopConfigServiceURMS.queryInitTemplate(params);

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

    @RequestMapping("/queryTemplate")
    @ResponseBody
    public ZLResult queryTemplate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitShopConfigServiceURMS.queryTemplate(params);

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

    @RequestMapping("/updateBranchlistTemplate")
    @ResponseBody
    public ZLResult updateBranchlistTemplate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitShopConfigServiceURMS.updateBranchlistTemplate(params);

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

    @RequestMapping("/resetTemplate")
    @ResponseBody
    public ZLResult resetTemplate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitShopConfigServiceURMS.resetTemplate(params);

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

}
