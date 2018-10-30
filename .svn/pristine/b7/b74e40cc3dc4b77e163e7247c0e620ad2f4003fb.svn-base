package com.wgb.controller.mt.dist;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageOutService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/dist/storageOut")
public class MTStorageOutController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTStorageOutController.class);

    @Autowired
    ApitStorageOutService apitStorageOutService;

    @RequestMapping("/storageOutDetail")
    @ResponseBody
    public ZLResult storageOutDetail(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageOutService.getStorageOutPage(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    @RequestMapping("/storageOutDel")
    @ResponseBody
    public ZLResult storageOutDel(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageOutService.delStorageOut(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/showStorageOutBill")
    @ResponseBody
    public ZLResult showStorageInBill(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageOutService.showStorageOutMain(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return  ZLResult.Success(pageInfo);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageOutService.saveOrUpdate(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageOutToView")
    @ResponseBody
    public ZLResult storageOutToView(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 校验参数
        String id = MapUtils.getString(params, "id");
        params.put("viewflag","1");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
                zlRpcResult = apitStorageOutService.getStorageOutMain(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> storageOutMain = zlRpcResult.getMap();

        return ZLResult.Success(storageOutMain);
    }

    @RequestMapping("/querystorageInOrOut")
    @ResponseBody
    public ZLResult querystorageOutToView(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageOutService.querystorageOutToView(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> storageOutToViewList = zlRpcResult.getList();

        return ZLResult.Success(storageOutToViewList);
    }

    @RequestMapping("/storageOutExamine")
    @ResponseBody
    public ZLResult storageOutExamine(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageOutService.examineStorageOut(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

}
