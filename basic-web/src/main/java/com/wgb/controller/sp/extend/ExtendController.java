package com.wgb.controller.sp.extend;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.controller.sp.renew.RenewController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitExtendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/renew/extend")
public class ExtendController extends SPBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendController.class);
    @Autowired
    private ApitExtendService apitExtendService;
    /**
     * 延期列表查询
     * @param request
     * @return
     */
    @RequestMapping("/queryExtendInfoList.action")
    @ResponseBody
    public ZLResult queryMessageDetail(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult zlRpcResult= apitExtendService.queryExtendInfoList(params);
            result = parseRpcResult(zlRpcResult);
        }catch (Exception ex){
            LOGGER.error("延期列表查询异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
    /**
     * 延期列表详情查询
     * @param request
     * @return
     */
    @RequestMapping("/queryExtendDetail.action")
    @ResponseBody
    public ZLResult queryExtendDetail(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult zlRpcResult= apitExtendService.queryExtendDetail(params);
            result = parseRpcResult(zlRpcResult);
        }catch (Exception ex){
            LOGGER.error("延期列表查询异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
    /**
     * 选择门店
     * @param request
     * @return
     */
    @RequestMapping("/findMultipleShop.action")
    @ResponseBody
    public ZLResult findMultipleShop(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult zlRpcResult= apitExtendService.findMultipleShop(params);
            result = parseRpcResult(zlRpcResult);
        }catch (Exception ex){
            LOGGER.error("延期列表查询异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
    /**
     * 保存信息
     * @param request
     * @return
     */
    @RequestMapping("/saveExtendInfo.action")
    @ResponseBody
    public ZLResult saveExtendInfo(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult zlRpcResult= apitExtendService.saveExtendInfo(params);
            result = parseRpcResultForMsg(zlRpcResult);
        }catch (Exception ex){
            LOGGER.error("延期列表查询异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
