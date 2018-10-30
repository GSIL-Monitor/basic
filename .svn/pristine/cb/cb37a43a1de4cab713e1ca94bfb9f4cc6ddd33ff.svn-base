package com.wgb.controller.mt.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.DispatcherService;
import com.wgb.service.dubbo.dcms.web.ApitBrandService;
import com.wgb.util.ServiceConstant;
import com.wgb.util.ServiceParams;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/commodity/brand")
public class MTBrandController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitBrandService apitBrandService;

    //添加品牌
    @RequestMapping("/addBrand")
    @ResponseBody
    public ZLResult addBrand(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        checkAddBrandParams(params);
        try {
            //系统调用
            zlRpcResult = apitBrandService.addBrand(params);
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

    //选择品牌
    @RequestMapping("/selectBrand")
    @ResponseBody
    public ZLResult selectBrand(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult = apitBrandService.selectBrand(params);

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

    //品牌查询
    @RequestMapping("/queryBrand")
    @ResponseBody
    public ZLResult queryBrand(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult = apitBrandService.queryBrand(params);

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

    //更新品牌
    @RequestMapping("/updateBrand")
    @ResponseBody
    public ZLResult updateBrand(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBrandService.updateBrand(params);
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

    //删除品牌
    @RequestMapping("/delBrand")
    @ResponseBody
    public ZLResult delBrand(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitBrandService.delBrand(params);
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
     * 添加品牌校验参数
     * 字段备注  brandname:品牌名称
     */
    private void checkAddBrandParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "brandname"))) {
            throw new ServiceException("品牌名称不能为空");
        }
    }
}
