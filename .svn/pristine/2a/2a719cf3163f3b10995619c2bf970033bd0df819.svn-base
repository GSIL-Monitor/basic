package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.admin.ApiLoadingDataService;
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
public class MTLoadingDataFomMBMSController extends MTBaseController {

    @Autowired
    private ApiLoadingDataService apiLoadingDataServiceFromMBMS;

    @RequestMapping("/getLoadingMemberlevelsData")
    @ResponseBody
    public ZLResult getLoadingMemberlevelsIdsData(HttpServletRequest request) {
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
            zlRpcResult = apiLoadingDataServiceFromMBMS.getLoadingMemberlevelsData(params);
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

    @RequestMapping("/getLoadingMemberConfigData")
    @ResponseBody
    public ZLResult getLoadingMemberConfigData(HttpServletRequest request) {
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
            zlRpcResult = apiLoadingDataServiceFromMBMS.getMemberShopConfig(params);
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
