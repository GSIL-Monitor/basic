package com.wgb.controller.mt.component;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitComponentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/5/31.
 */
@Controller
@RequestMapping("/component/component")
public class MTComponentController extends MTBaseController {

       public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTComponentController.class);

       @Autowired
       private ApitComponentService apitComponentService;

       /*我的订单*/
        @RequestMapping("/queryShopOrder")
        @ResponseBody
        public ZLResult queryShopOrder(HttpServletRequest request) {


            //获取入参数据
            Map<String, Object> params =  getParams();

            //定义返回对象
            ZLRpcResult zlRpcResult  = new ZLRpcResult();
            //校验参数
            try {
                    //系统调用
                    zlRpcResult = apitComponentService.queryShopOrder(params);
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

        /*更新订单状态*/
        @RequestMapping("/updateOrderStatus")
        @ResponseBody
        public ZLResult updateOrderStatus(HttpServletRequest request) {


            //获取入参数据
            Map<String, Object> params =  getParams();

            //定义返回对象
            ZLRpcResult zlRpcResult  = new ZLRpcResult();
            //校验参数
            try {
                    //系统调用
                    zlRpcResult = apitComponentService.updateOrderStatus(params);
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
    
    @RequestMapping("/queryComponentBriefList")
    @ResponseBody
    public ZLResult queryComponentBriefList(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentBriefList(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getMap());
    }

    @RequestMapping("/updateComponentStart")
    @ResponseBody
    public ZLResult updateComponentStart(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitComponentService.updateComponentStart(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getMap());
    }

    @RequestMapping("/updateComponentRecharge")
    @ResponseBody
    public ZLResult updateComponentRecharge(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitComponentService.updateComponentRecharge(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getMap());
    }

    @RequestMapping("/createComponentOrder")
    @ResponseBody
    public ZLResult createComponentOrder(HttpServletRequest request){


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult = apitComponentService.addComponentOrder(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getMap());
    }

    @RequestMapping("/queryComponentIsOpen")
    @ResponseBody
    public ZLResult queryComponentIsOpen(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentIsOpen(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

    /*查询组件价格（升级连锁）*/
    @RequestMapping("/queryComponentPriceForSoftwarttype")
    @ResponseBody
    public ZLResult queryComponentPriceForSoftwarttype(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentPriceForSoftwarttype(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

    @RequestMapping("/queryComponentByBill")
    @ResponseBody
    public ZLResult queryComponentByBill(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentOrderByBillcode(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

    /*查询组件价格（升级连锁）*/
    @RequestMapping("/queryComponentRechargeList")
    @ResponseBody
    public ZLResult queryComponentRechargeList(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentRechargeList(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

    /**
     * 查询组件明细
     * @param request
     * @return
     */
    @RequestMapping("/queryComponentRechargePage")
    @ResponseBody
    public ZLResult queryComponentRechargePage(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentRechargePage(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }


    /**
     * 公共接口查询当前用户匹配的组件数据
     * @param request
     * @return
     */
    @RequestMapping("/queryComponentList")
    @ResponseBody
    public ZLResult queryComponentList(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryComponentList(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

 /**
     * 查询基础应用
     * @param request
     * @return
     */
    @RequestMapping("/queryFreeComponent")
    @ResponseBody
    public ZLResult queryFreeComponent(HttpServletRequest request) {


        Map<String, Object> params = getParams();

        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitComponentService.queryFreeComponent(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }else{
            return ZLResult.Success(zlRpcResult.getData());
        }
    }

}
