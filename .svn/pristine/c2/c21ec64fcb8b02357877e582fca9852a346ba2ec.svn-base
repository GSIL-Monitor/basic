package com.wgb.controller.mt.dist;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageInService;
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
@RequestMapping("/dist/storageIn")
public class MTStorageInController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTStorageInController.class);

    @Autowired
    ApitStorageInService apitStorageInService;

    @RequestMapping("/queryStorageInListDetail")
    public ZLResult queryStorageInListDetail(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.getStorageInPage(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>) zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    @RequestMapping("/storageInAdd")
    @ResponseBody
    public ZLResult add(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.saveStorageIn(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageInUpdate")
    @ResponseBody
    public ZLResult storageInUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.updateStorageIn(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 零售2.0
     */
    @RequestMapping("/queryStorageInList")
    @ResponseBody
    public ZLResult queryStorageInList(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.getStorageInPage(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>) zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    @RequestMapping("/showStorageInBill")
    @ResponseBody
    public ZLResult showStorageInBill(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.showStorageInMain(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    /**
     * 选择出库单(入库)
     *
     * @param request
     * @return
     */
    @RequestMapping("/chooseStorageOut")
    @ResponseBody
    public ZLResult ChooseRequire(HttpServletRequest request) {

        Map<String, Object> params = getParams();


        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.getToStorageOutPage(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    /**
     * 根据出库单显示商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/showInGoodsByStorageOut")
    @ResponseBody
    public ZLResult showReceiptGoodsByOrder(HttpServletRequest request) {

        Map<String, Object> params = getParams();


        // 校验参数
        String billcode = MapUtils.getString(params, "billcode");
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.getStorageOutMain(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> requisitionMain = zlRpcResult.getMap();


        return ZLResult.Success(requisitionMain);
    }

    /**
     * 根据要货单显示商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/showInGoodsByRequire")
    @ResponseBody
    public ZLResult showInGoodsByRequire(HttpServletRequest request) {


        Map<String, Object> params = getParams();


        String billcode = MapUtils.getString(params, "billcode");
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.getRequireMain(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> requireMain = zlRpcResult.getMap();

        return ZLResult.Success(requireMain);
    }

    @RequestMapping("/storageInDel")
    @ResponseBody
    public ZLResult storageInDel(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.deleteStorageIn(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/storageInToUpdateDetail")
    @ResponseBody
    public ZLResult storageInToUpdateDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String id = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.getStorageInMain(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> storageInMain = zlRpcResult.getMap();

        return ZLResult.Success(storageInMain);
    }


    @RequestMapping("/storageInAddOrUpdate")
    @ResponseBody
    public ZLResult addOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.saveOrUpdateStorageIn(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }


    @RequestMapping("/storageInExamine")
    @ResponseBody
    public ZLResult storageInExamine(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitStorageInService.examineStorageIn(params);
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
