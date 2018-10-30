package com.wgb.controller.mt.customer;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitCustomerService;
import com.wgb.util.ExcelUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/customer/customer")
public class MTCustomerController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTCustomerController.class);

    @Autowired
    private ApitCustomerService apitCustomerService;

    /*下拉*/
    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCustomerService.getStorageCheckPage(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    /*档案明细*/
    @RequestMapping("/manageDetail")
    @ResponseBody
    public ZLResult manageDetail(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCustomerService.manage_detail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    /*删除*/
    @RequestMapping("/delCustomer")
    @ResponseBody
    public ZLResult delCustomer(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCustomerService.delCustomer(params);

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

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCustomerService.saveOrUpdate(params);

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

    @RequestMapping("/queryCustomerDetail")
    @ResponseBody
    public ZLResult queryCustomerDetail(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCustomerService.queryCustomerDetail(params);
        } catch (ServiceException e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    /*导出客户*/
    @RequestMapping("/exportCustomer")
    @ResponseBody
    public ZLResult exportCustomer(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitCustomerService.exportCustomer(params);
            // 字段名数组
            String[] purchase = {"number", "customername","mnemonic","regionname",
                    "branchname", "settlemethod", "checkoutcycle", "moncheckcyclse", "defaultprice",
                    "discountrate"};
            // 字段名数组
            String[] purchaseNames = {"编号", "客户名称","助记码", "客户区域",
                    "所属机构", "结算方式", "结账周期", "月结账周期", "默认价格",
                    "折扣"};
            String purchaseName = "客户资料_";
            ExcelUtil.exportExcel(zlRpcResult.getList(), purchase, purchaseNames, purchaseName, request, response);
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

}
