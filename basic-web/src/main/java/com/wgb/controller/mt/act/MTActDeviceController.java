package com.wgb.controller.mt.act;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.acts.web.ApitPortalDeviceService;
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
@RequestMapping("/act/device")
public class MTActDeviceController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTActDeviceController.class);

    @Autowired
    private ApitPortalDeviceService apitPortalDeviceService;

    /**
     * 2.0终端授权查找
     * @param request
     * @return
     */
    @RequestMapping("/queryManageDetail")
    @ResponseBody
    public ZLResult queryManageDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult = apitPortalDeviceService.queryManageDetail(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

    /**
     * 2.0终端授权修改
     * @param request
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult = apitPortalDeviceService.saveOrUpdate(params);
            boolean success = zlRpcResult.success();
            if(success){
                return ZLResult.Success();
            }else{
                return ZLResult.Error(zlRpcResult.getErrorMsg());
            }
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }


    @RequestMapping("/querymonitoringManageDetail")
    @ResponseBody
    public ZLResult querymonitoringManageDetail(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalDeviceService.query(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/queryDeviceByDevicetype")
    @ResponseBody
    public ZLResult queryDeviceByDevicetype(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalDeviceService.queryDeviceByDevicetype(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/addDeviceRecharge")
    @ResponseBody
    public ZLResult addDeviceRecharge(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalDeviceService.addDeviceRecharge(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/queryDeviceDetail")
    @ResponseBody
    public ZLResult queryDeviceDetail(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalDeviceService.queryDeviceDetail(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/queryDeviceRechargeDetail")
    @ResponseBody
    public ZLResult queryDeviceRechargeDetail(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalDeviceService.queryDeviceRechargeDetail(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }
}
