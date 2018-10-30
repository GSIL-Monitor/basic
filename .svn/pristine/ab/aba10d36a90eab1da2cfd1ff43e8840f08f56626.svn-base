package com.wgb.controller.sp.servicebill;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitServiceBillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 维修工单
 *
 * @author fxs
 * @create 2018-07-09 10:22
 **/
@Controller
@RequestMapping("serviceBill")
public class ServiceBillController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBillController.class);

    @Autowired
    private ApitServiceBillService apitServiceBillService;

    @RequestMapping("queryServiceBillPage")
    @ResponseBody
    public ZLResult queryServiceBillPage(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {
            ZLRpcResult rpcResult = apitServiceBillService.queryServiceBillPage(getParams());
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询维修单据信息系统异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        //查询数据
        return result;
    }

    @RequestMapping("saveServiceBill")
    @ResponseBody
    public ZLResult saveServiceBill(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceBillService.saveServiceBill(getParams());
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception e) {
            LOGGER.error("保存维修单据信息系统异常!" ,e);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("findPaymentDetail")
    @ResponseBody
    public ZLResult findPaymentDetail(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceBillService.findPaymentDetail(getParams());
            result = parseRpcResult(rpcResult);
        }catch (Exception e) {
            LOGGER.error("查询维修单据支付信息系统异常!" ,e);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("saveServiceBillPaymentMethod")
    @ResponseBody
    public ZLResult saveServiceBillPaymentMethod(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceBillService.saveServiceBillPaymentMethod(params);
            result = parseRpcResultForMsg(rpcResult);
        } catch (Exception e) {
            LOGGER.error("保存维修单据支付信息系统异常!" ,e);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("cancelService")
    @ResponseBody
    public ZLResult cancelService(){
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceBillService.cancelService(params);
            result = parseRpcResultForMsg(rpcResult);
        } catch (Exception e) {
            LOGGER.error("取消返修系统异常!" ,e);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("confirmReceipt")
    @ResponseBody
    public ZLResult confirmReceipt(HttpServletRequest request)  {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceBillService.confirmReceipt(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception e) {
            LOGGER.error("确认收货系统异常!" ,e);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }


    @RequestMapping("queryServiceBillDetail")
    @ResponseBody
    public ZLResult queryServiceBillDetail(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceBillService.queryServiceBillDetail(params);
            result = parseRpcResult(rpcResult);
        } catch (Exception e) {
            LOGGER.error("查询维修单据详情系统异常!" ,e);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
