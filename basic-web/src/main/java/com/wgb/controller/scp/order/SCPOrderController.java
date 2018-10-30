package com.wgb.controller.scp.order;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.scpms.web.ApitSupplierOrderService;
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
@RequestMapping("/supplier/order")
public class SCPOrderController extends SCPBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(SCPOrderController.class);

    @Autowired
    private ApitSupplierOrderService apitSupplierOrderService;

    @RequestMapping("/manage")
    @ResponseBody
    public ZLResult querySupplierOrderMain(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.querySupplierOrderMain(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/checkReason")
    @ResponseBody
    public ZLResult checkReason(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.querySupplierOrderGoods(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/count")
    @ResponseBody
    public ZLResult orderCount(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.orderCount(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult querySupplierOrderDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.querySupplierOrderDetail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/cancle")
    @ResponseBody
    public ZLResult cancleOrder(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.cancleOrder(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/agreeReturn")
    @ResponseBody
    public ZLResult agreeReturn(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.agreeReturn(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/confirmGoods")
    @ResponseBody
    public ZLResult confirmGoods(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.confirmGoods(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/applyReturn")
    @ResponseBody
    public ZLResult applyReturn(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.applyReturn(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/complete")
    @ResponseBody
    public ZLResult completeOrder(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.completeOrder(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/save")
    @ResponseBody
    public ZLResult supplierOrderAddOrUpdate(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.supplierOrderAddOrUpdate(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/return")
    @ResponseBody
    public ZLResult supplierOrderReturn(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.supplierOrderReturn(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/wxorder")
    @ResponseBody
    public ZLResult querySupplierOrderGoods(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.querySupplierOrderGoods(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/deliver")
    @ResponseBody
    public ZLResult deliverOrderGoods(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.deliverOrderGoods(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/updateOrderStatusForStms")
    @ResponseBody
    public ZLResult updateOrderStatus(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.updateOrderStatus(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }


    /*Stms查询订单信息(List)*/
    @RequestMapping("/queryOrderList")
    @ResponseBody
    public ZLResult queryOrderList(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.queryOrderList(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    /*Stms查询订单的商品信息*/
    @RequestMapping("/queryOrderGoodsList")
    @ResponseBody
    public ZLResult queryOrderGoodsList(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitSupplierOrderService.queryOrderGoodsList(params);
        }catch (Exception ex){
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }


}

