package com.wgb.controller.mt.act;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.acts.web.ApitPortalActGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/act/group")
public class MTActGroupController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTActGroupController.class);

    @Autowired
    private ApitPortalActGroupService apitPortalActGroupService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalActGroupService.query(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalActGroupService.saveOrUpdate(params);
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

    @RequestMapping("/toUpdate")
    @ResponseBody
    public ZLResult toUpdateDetail(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalActGroupService.toUpdateDetail(params);
            return ZLResult.Success(zlRpcResult.getData());
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitPortalActGroupService.delete(params);
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

}
