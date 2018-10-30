package com.wgb.controller.sp.information;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.srvms.web.ApitSrvInformationService;
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
@RequestMapping("/srv/information")
public class SrvInformationController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvInformationController.class);
    @Autowired
    private ApitSrvInformationService apitSrvInformationService;

    /**
     * 跳转用户管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult manage(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvInformationService.detail(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception e){
            LOGGER.error("查询用户资料信息失败!",e);
            result = ZLResult.Error("操作失败，请联系管理员");
        }
        return result;
    }
}
