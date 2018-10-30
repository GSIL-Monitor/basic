package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiUserService;
import com.wgb.service.dubbo.urms.web.ApitPortalUserService;
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
@RequestMapping("/portal/user")
public class MTPortalUserController extends MTBaseController {

    @Autowired
    private ApitPortalUserService apitPortalUserService;

    @Autowired
    private ApiUserService apiUserService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.query(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.select(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/selectUser")
    @ResponseBody
    public ZLResult selectUser(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.selectUser(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/selectUserByGroupcode")
    @ResponseBody
    public ZLResult selectUserByGroupcode(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.selectUserByGroupcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getObject")
    @ResponseBody
    public ZLResult getObject(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.getObject(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/updatepwd")
    @ResponseBody
    public ZLResult undoPassword(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.undoPassword(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public ZLResult updatePwd(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apiUserService.updatePwd(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        boolean success = zlRpcResult.success();
        if(success){
            return ZLResult.Success();
        }else{
            return ZLResult.Error(zlRpcResult.getErrorCode());
        }
    }

    @RequestMapping("/queryMbms")
    @ResponseBody
    public ZLResult queryMbms(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.queryMbms(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.saveOrUpdate(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        boolean success = zlRpcResult.success();
        if(success){
            return ZLResult.Success();
        }else{
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalUserService.delete(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        boolean success = zlRpcResult.success();
        if(success){
            return ZLResult.Success();
        }else{
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
    }
}
