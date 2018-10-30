package com.wgb.controller.mt.dist;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageRequireService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
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
@RequestMapping("/dist/storageRequire")
public class MTStorageRequireController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTStorageRequireController.class);

    @Autowired
    ApitStorageRequireService apitStorageRequireService;

    @RequestMapping("/queryStorageRequire")
    @ResponseBody
    public ZLResult queryStorageRequire(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.getStorageRequirePage(params);
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

    @RequestMapping("/detele")
    @ResponseBody
    public ZLResult detele(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.delStorageRequire(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 选择要货单(入库)
     * @param request
     * @return
     */
    @RequestMapping("/chooseRequire")
    @ResponseBody
    public ZLResult ChooseRequire(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.getToStorageRequire(params);
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

    @RequestMapping("/storageRequireToView")
    @ResponseBody
    public ZLResult storageRequisitionToView(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 校验参数
        String id = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.getStorageRequireMain(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> storageRequireMap = zlRpcResult.getMap();

        return ZLResult.Success(storageRequireMap);
    }

    @RequestMapping("/storageRequireAdd")
    @ResponseBody
    public ZLResult add(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.saveStorageRequire(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageRequireUpdate")
    @ResponseBody
    public ZLResult storageRequisitionUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.updateStorageRequire(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageRequireAddOrUpdate")
    @ResponseBody
    public ZLResult storageRequireAddOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
                zlRpcResult = apitStorageRequireService.saveStorageRequire(params);
            }else {
                zlRpcResult = apitStorageRequireService.updateStorageRequire(params);
            }
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageRequireExamine")
    @ResponseBody
    public ZLResult storageRequisitionExamine(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.examineStorageRequire(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageRequireDel")
    @ResponseBody
    public ZLResult storageCheckDel(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequireService.delStorageRequire(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

}
