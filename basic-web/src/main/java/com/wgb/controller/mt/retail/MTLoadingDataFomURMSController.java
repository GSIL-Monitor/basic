package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiLoadingDataService;
import com.wgb.util.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/8/7.
 */
@Controller
@RequestMapping("/newestData")
public class MTLoadingDataFomURMSController extends MTBaseController {

    @Autowired
    private ApiLoadingDataService apiLoadingDataServiceFromURMS;

    @RequestMapping("/getLoadingbranchMapData")
    @ResponseBody
    public ZLResult getLoadingbranchMapData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String branchcode = MapUtils.getString(params, "loginuserbranchcode");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        params.put("branchcode",branchcode);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiLoadingDataServiceFromURMS.getLoadingbranchMapData(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getLoadingCashiergrantData")
    @ResponseBody
    public ZLResult getLoadingCashiergrantData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String branchcode = MapUtils.getString(params, "loginuserbranchcode");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        params.put("branchcode",branchcode);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiLoadingDataServiceFromURMS.getLoadingCashiergrantData(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getLoadingConfigData")
    @ResponseBody
    public ZLResult getLoadingConfigData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String branchcode = MapUtils.getString(params, "loginuserbranchcode");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        params.put("branchcode",branchcode);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiLoadingDataServiceFromURMS.getLoadingConfigData(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getLoadingTicketemplateData")
    @ResponseBody
    public ZLResult getLoadingTicketemplateData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String branchcode = MapUtils.getString(params, "loginuserbranchcode");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        params.put("branchcode",branchcode);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiLoadingDataServiceFromURMS.getLoadingTicketemplateData(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getLoadingUsersData")
    @ResponseBody
    public ZLResult getLoadingCategoryData(HttpServletRequest request) {
        // 获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String branchcode = MapUtils.getString(params, "loginuserbranchcode");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        params.put("branchcode",branchcode);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiLoadingDataServiceFromURMS.getLoadingUsersData(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    private static boolean validateDate(String dateStr) {
        try {
            DateUtils.formatStr2Date(dateStr, DateUtils.C_TIME_PATTON_DEFAULT1);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
