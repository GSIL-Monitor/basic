package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitBranchRegionService;
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
@RequestMapping("/system/region")
public class MTBranchRegionController extends MTBaseController {

    @Autowired
    private ApitBranchRegionService apitBranchRegionService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchRegionService.query(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchRegionService.saveOrUpdate(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/delLogic")
    @ResponseBody
    public ZLResult delLogic(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchRegionService.delLogic(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchRegionService.select(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryRegionDetail")
    @ResponseBody
    public ZLResult queryRegionDetail(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchRegionService.queryRegionDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    private ZLResult isSuccess(ZLRpcResult rpcResult){
        boolean success = rpcResult.success();
        if(success){
            return ZLResult.Success();
        }else{
            return ZLResult.Error(rpcResult.getErrorMsg());
        }
    }
}
