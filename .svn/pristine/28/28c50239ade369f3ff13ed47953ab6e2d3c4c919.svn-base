package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiNewestDataService;
import com.wgb.service.dubbo.mbms.admin.ApiNewestDataFromMBMS;
import com.wgb.service.dubbo.pays.admin.ApiNewestDataFromPAYS;
import com.wgb.service.dubbo.stms.admin.ApiNewestDataFromSTMS;
import com.wgb.service.dubbo.urms.admin.ApiNewestDataFromURMS;
import com.wgb.util.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 收银端初始化数据
 * Created by wgb on 2017/2/17.
 */
@Controller
@RequestMapping("/retail")
public class MTGetDataController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiNewestDataService apiNewestDataService;

    @Autowired
    private ApiNewestDataFromMBMS apiNewestDataFromMBMS;

    @Autowired
    private ApiNewestDataFromPAYS apiNewestDataFromPAYS;

    @Autowired
    private ApiNewestDataFromSTMS apiNewestDataFromSTMS;

    @Autowired
    private ApiNewestDataFromURMS apiNewestDataFromURMS;

    /**
     * 获取最新数据请求 - DCMS
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNewestDataFromDCMS")
    @ResponseBody
    public ZLResult getNewestDataFromDCMS(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String branchcode = MapUtils.getString(params, "branchcode");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiNewestDataService.getNewestData(params);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 获取最新数据请求 - MBMS
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNewestDataFromMBMS")
    @ResponseBody
    public ZLResult getNewestDataFromMBMS(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        String branchcode = MapUtils.getString(params, "branchcode", "");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiNewestDataFromMBMS.getNewestData(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 获取最新数据请求 - PAYS
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNewestDataFromPAYS")
    @ResponseBody
    public ZLResult getNewestDataFromPAYS(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiNewestDataFromPAYS.getNewestData(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 获取最新数据请求 - STMS
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNewestDataFromSTMS")
    @ResponseBody
    public ZLResult getNewestDataFromSTMS(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiNewestDataFromSTMS.getNewestData(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 获取最新数据请求 - URMS
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNewestDataFromURMS")
    @ResponseBody
    public ZLResult getNewestDataFromURMS(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        logger.info("获取最新数据请求 - URMS-参数:"+params);
        String shopcode = MapUtils.getString(params, "shopcode", "");
        String lastrequesttime = MapUtils.getString(params, "lastrequesttime", "");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isNotEmpty(lastrequesttime) && (lastrequesttime.length() != 17 || !validateDate(lastrequesttime))) {
            throw new ServiceException(ServiceException.CODE_20201);
        }

        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apiNewestDataFromURMS.getNewestData(params);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
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
