package com.wgb.controller.scp.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSCPUnitFromDcmsService;
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
@RequestMapping("/scpcommodity/unit")
public class SCPUnitController extends SCPBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitSCPUnitFromDcmsService apitSCPUnitFromDcmsService;

    //选择单位
    @RequestMapping("/selectUnit")
    @ResponseBody
    public ZLResult selectUnit(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult = apitSCPUnitFromDcmsService.selectUnit(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getList());
    }

    //展示所有单位
    @RequestMapping("/getPageUnitList")
    @ResponseBody
    public ZLResult getPageUnitList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPUnitFromDcmsService.getPageUnitList(params);
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

    //增加修改单位
    @RequestMapping("/saveOrUpdateUnit")
    @ResponseBody
    public ZLResult saveOrUpdateUnit(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPUnitFromDcmsService.saveOrUpdateUnit(params);
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

    //删除单位
    @RequestMapping("/delUnit")
    @ResponseBody
    public ZLResult delUnit(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPUnitFromDcmsService.delUnit(params);
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

    /**
     * 修改商品分类-查询
     * @param request
     * @return
     */
    @RequestMapping("/getScpUnitById")
    @ResponseBody
    public ZLResult getScpUnitById(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPUnitFromDcmsService.getScpUnitById(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getMap());
    }

}
