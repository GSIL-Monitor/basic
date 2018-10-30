package com.wgb.controller.sp.message;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitSrvMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yjw on 2017/11/8.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private ApitSrvMessageService apitSrvMessageService;

    @RequestMapping("/queryMessageDetail")
    @ResponseBody
    public ZLResult queryMessageDetail(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvMessageService.queryMessageDetail(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询消息详情系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/queryAdminMessageForPage")
    @ResponseBody
    public ZLResult manage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLResult result = ZLResult.Success();
        try {
            ZLRpcResult rpcResult = apitSrvMessageService.manage(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询消息列表系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
