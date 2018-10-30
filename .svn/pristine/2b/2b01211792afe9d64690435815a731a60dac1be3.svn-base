package com.wgb.controller.mt.storage;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageCombCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10194 on 2018/5/15.
 */
@Controller
@RequestMapping("/storage/combCommodity")
public class MTStorageCombCommodityController extends MTBaseController {

    @Autowired
    private ApitStorageCombCommodityService apitStorageCombCommodityService;

    @RequestMapping("/combCommodityList")
    @ResponseBody
    public ZLResult manger(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.manger(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/combCommodityDetail")
    @ResponseBody
    public ZLResult getCombCommodityDetail(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.getCombCommodityDetail(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("组合商品详情 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/combCommodityAddOrUpdate")
    @ResponseBody
    public ZLResult storageCheckAddOrUpdate(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.storageCheckAddOrUpdate(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("组合商品保存 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/combCommodityDel")
    @ResponseBody
    public ZLResult combCommodityDel(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.combCommodityDel(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("组合商品删除 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/updateExamineFlag")
    @ResponseBody
    public ZLResult updateExamineFlag(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.updateExamineFlag(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("更新检查标志 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/updateExamineFlagNew")
    @ResponseBody
    public ZLResult updateExamineFlagNew(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.updateExamineFlagNew(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("新更新检查标志 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryCommodityBatchCode")
    @ResponseBody
    public ZLResult queryCommodityBatchCode(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.queryCommodityBatchCode(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("查询批次号 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryCommoditycodeList")
    @ResponseBody
    public ZLResult queryCommoditycodeList(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitStorageCombCommodityService.queryCommoditycodeList(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("查询组合商品门店编码列表 用时:"+(endTime-startTime)+"ms");
        return   ZLResult.Success(zlRpcResult.getData());
    }

}
