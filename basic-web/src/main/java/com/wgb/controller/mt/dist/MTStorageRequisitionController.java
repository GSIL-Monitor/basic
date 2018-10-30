package com.wgb.controller.mt.dist;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageRequisitionService;
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
@RequestMapping("/dist/storageRequisition")
public class MTStorageRequisitionController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTStorageRequisitionController.class);

    @Autowired
    ApitStorageRequisitionService apitStorageRequisitionService;

    @RequestMapping("/detele")
    @ResponseBody
    public ZLResult detele(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequisitionService.delStorageRequisition(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/queryStorageRequisitionList")
    @ResponseBody
    public ZLResult queryStorageRequisitionList(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequisitionService.getStorageRequisitionPage(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> storageRequisitionPage  = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(storageRequisitionPage);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult addOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
                zlRpcResult = apitStorageRequisitionService.saveStorageRequisition(params);
            }else{
                zlRpcResult = apitStorageRequisitionService.updateStorageRequisition(params);
            }
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /*
     * 选择出入库调拨单
     */
    @RequestMapping("/chooseRequisition")
    @ResponseBody
    public ZLResult ChooseRequisition(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequisitionService.getToStorageRequestion(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> storageRequisitionPage  = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(storageRequisitionPage);
    }

    @RequestMapping("/storageRequisitionDel")
    @ResponseBody
    public ZLResult storageCheckDel(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequisitionService.delStorageRequisition(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 对调拨单的审核
     * @param request
     * @return
     */
    @RequestMapping("/storageRequisitionExamine")
    @ResponseBody
    public ZLResult RequisitionExamine(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageRequisitionService.examineRequisition(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageRequisitionToView")
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
            zlRpcResult = apitStorageRequisitionService.getStorageRequisitionMain(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> storageRequisitionMap  = (Map<String, Object>)zlRpcResult.getData();

        return ZLResult.Success(storageRequisitionMap);
    }

}
