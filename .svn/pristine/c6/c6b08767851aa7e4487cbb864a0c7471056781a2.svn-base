package com.wgb.controller.scp.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSCPBrandFromDcmsService;
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
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/scpcommodity/brand")
public class SCPBrandController extends SCPBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitSCPBrandFromDcmsService apitSCPBrandFromDcmsService;

    //添加修改品牌
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        checkAddBrandParams(params);
        try {
            //系统调用
            zlRpcResult = apitSCPBrandFromDcmsService.saveOrUpdate(params);
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
            zlRpcResult = apitSCPBrandFromDcmsService.selectBrand(params);

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
            zlRpcResult = apitSCPBrandFromDcmsService.queryBrand(params);

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

    //删除品牌
    @RequestMapping("/delBrand")
    @ResponseBody
    public ZLResult delBrand(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPBrandFromDcmsService.delBrand(params);
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
    @RequestMapping("/getScpBrandById")
    @ResponseBody
    public ZLResult getScpBrandById(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPBrandFromDcmsService.getScpBrandById(params);
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
