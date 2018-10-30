package com.wgb.controller.scp.xcx;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSCPCommodityFromDcmsService;
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
@RequestMapping("/entry/xcxCommodity/commodity")
public class ScpXcxCommodityController extends SCPXCXBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitSCPCommodityFromDcmsService apitSCPCommodityFromDcmsService;

    /**
     * 小程序端-查出商品类别信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCommodityCategoryList")
    @ResponseBody
    public ZLResult getCommodityCategoryList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.getCommodityCategory(params);
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

    /**
     * 根据商品类别查询商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCommodityListByCategory")
    @ResponseBody
    public ZLResult getCommodityListByCategory(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.getCommodityListByCategory(params);
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


    /**
     * 供应商小程序调-查询供应商商品信息by  scpcode  commodityname
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryScpCommodityByName")
    @ResponseBody
    public ZLResult queryScpCommodityByName(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.queryScpCommodityByName(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ex.getMessage());
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 供应商小程序调-查询供应商商品详情by  scpcode  commoditycode
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryScpCommodityDetailByCode")
    @ResponseBody
    public ZLResult queryScpCommodityDetailByCode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.queryScpCommodityDetailByCode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ex.getMessage());
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

}
