package com.wgb.controller.sp.income;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitServiceIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/serviceincome")
public class ServiceIncomeController  extends SPBaseController {

    @Autowired
    private ApitServiceIncomeService apitServiceIncomeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceIncomeController.class);

    @RequestMapping("/payincome/showShopTable")
    @ResponseBody
    public ZLResult showShopTable(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitServiceIncomeService.showShopTable(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询对账单详情业务异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
