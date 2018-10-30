package com.wgb.controller.sp.industry;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitIndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("industry")
public class SrvIndustryController extends SPBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrvIndustryController.class);
    @Autowired
    private ApitIndustryService apitIndustryService;

    @RequestMapping("/getList")
    @ResponseBody
    public ZLResult getList(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        try {
            ZLRpcResult rpcResult = apitIndustryService.getList(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询行业数据系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

}
