package com.wgb.controller.mt.storage;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageCheckService;
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
@RequestMapping("/storage/storageCheck")
public class MTStorageCheckController extends MTBaseController {

    @Autowired
    private ApitStorageCheckService apitStorageCheckService;

    @RequestMapping("/storageCheckDetail")
    @ResponseBody
    public ZLResult getStorageCheckPage(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitStorageCheckService.getStorageCheckPage(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("库存盘点信息分页 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


    @RequestMapping("/storageCheckToUpdateDetail")
    @ResponseBody
    public ZLResult storageCheckToUpdateDetail(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitStorageCheckService.storageCheckToUpdateDetail(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("库存盘点信息更新 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/storageCheckAddOrUpdate")
    @ResponseBody
    public ZLResult storageCheckAddOrUpdate(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {

            //系统调用
            zlRpcResult = apitStorageCheckService.storageCheckAddOrUpdate(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("库存盘点信息保存 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/storageCheckDel")
    @ResponseBody
    public ZLResult storageCheckDel(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCheckService.storageCheckDel(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("库存盘点信息删除 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/storageCheckExamine")
    @ResponseBody
    public ZLResult storageCheckExamine(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCheckService.storageCheckExamine(params);

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


}
