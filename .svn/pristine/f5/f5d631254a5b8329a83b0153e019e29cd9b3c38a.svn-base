package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.mbms.web.ApitMbAssistantCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 曾经沧海 on 2018/6/22.
 */
@Controller
@RequestMapping("/member/saleassistantcode")
public class MTMbSaleAssistantCodeController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMbSaleAssistantCodeController.class);

    @Autowired
    ApitMbAssistantCodeService apitMbAssistantCodeService;

    @RequestMapping("/querySaleAssistantCode")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult rpcResult = apitMbAssistantCodeService.queryAssistantCode(params);

        return parseRpcResultForData(rpcResult);
    }

}
