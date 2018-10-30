package com.wgb.controller.scp.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSCPCategoryFromDcmsService;
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
@RequestMapping("/scpcommodity/category")
public class SCPCategoryController extends SCPBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitSCPCategoryFromDcmsService apitSCPCategoryFromDcmsService;


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
            zlRpcResult = apitSCPCategoryFromDcmsService.saveOrUpdate(params);
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

    //选折分类
    @RequestMapping("/selectCategory")
    @ResponseBody
    public ZLResult selectCategory(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCategoryFromDcmsService.selectCategory(params);
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

    //查询分类
    @RequestMapping("/queryPageCategory")
    @ResponseBody
    public ZLResult queryPageCategory(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCategoryFromDcmsService.queryPageCategory(params);
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

    //删除分类
    @RequestMapping("/delCategory")
    @ResponseBody
    public ZLResult delCategory(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCategoryFromDcmsService.delCategory(params);
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
    @RequestMapping("/getScpCategoryById")
    @ResponseBody
    public ZLResult getScpCategoryById(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
           zlRpcResult = apitSCPCategoryFromDcmsService.getScpCategoryById(params);
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
     * 字段备注  category:分类名称
     */
    private void checkAddBrandParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "categoryname"))) {
            throw new ServiceException("分类名称不能为空");
        }
    }

}
