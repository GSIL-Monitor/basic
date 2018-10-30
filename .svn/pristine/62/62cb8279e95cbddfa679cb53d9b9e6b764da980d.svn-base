package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitBranchService;
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
@RequestMapping("/system/branch")
public class MTBranchController extends MTBaseController {

    @Autowired
    private ApitBranchService apitBranchService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchService.query(params);

        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryPageList")
    @ResponseBody
    public ZLResult queryPageList(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBranchService.query(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryBranchByBranchcode")
    @ResponseBody
    public ZLResult queryBranchByBranchcode(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.queryBranchByBranchcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryBranchByGroupcode")
    @ResponseBody
    public ZLResult queryBranchByGroupcode(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.queryBranchByGroupcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryBranchList")
    @ResponseBody
    public ZLResult queryBranchList(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.queryBranchList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryBranchListRequire")
    @ResponseBody
    public ZLResult queryBranchListRequire(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.queryBranchListRequire(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/selectBranch")
    @ResponseBody
    public ZLResult selectBranch(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.selectBranch(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/selectBranchs")
    @ResponseBody
    public ZLResult selectBranchs(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.selectBranchs(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryBranchListForSelect")
    @ResponseBody
    public ZLResult queryBranchListForSelect(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.queryBranchListForSelect(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/meituan/query")
    @ResponseBody
    public ZLResult meituanQuery(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.meituanQuery(params);
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
            zlRpcResult = apitBranchService.select(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult save(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.save(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.update(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult updateIsused(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.updateIsused(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return isSuccess(zlRpcResult);
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ZLResult getList(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.getList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getJoinList")
    @ResponseBody
    public ZLResult getJoinBranchList(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.getJoinBranchList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getBranchByGroup")
    @ResponseBody
    public ZLResult getBranchByGroup(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.getBranchByGroup(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryAllBranchList")
    @ResponseBody
    public ZLResult queryAllBranchList(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBranchService.queryAllBranchList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
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
