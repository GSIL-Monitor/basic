package com.wgb.controller.sp.shop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitSrvShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by yjw on 2017/11/8.
 */
@Controller
@RequestMapping("/srv/shop")
public class SrvShopController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvShopController.class);

    @Autowired
    private ApitSrvShopService apitSrvShopService;

    @RequestMapping("/querySrvShopPage")
    @ResponseBody
    public ZLResult querySrvShopPage(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvShopService.querySrvShopPage(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询服务商商户信息系统异常",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    /*
     *更新商户标注信息(可批量)
     */
    @RequestMapping("/updateShopfollow")
    @ResponseBody
    public ZLResult updateShopfollow(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvShopService.updateShopfollow(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception ex){
            LOGGER.error("修改商户跟进状态/备注系统异常",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryShopDetail")
    @ResponseBody
    public ZLResult queryShopDetail(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvShopService.queryShopDetail(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询商户详情系统异常",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryBranchList")
    @ResponseBody
    public ZLResult queryBranchList(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvShopService.queryBranchList(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询指定商户下的门店系统异常",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/rechargeDetail")
    @ResponseBody
    public ZLResult rechargeDetail(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvShopService.rechargeDetail(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询指定商户下的门店系统异常",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
