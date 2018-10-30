package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.mbms.web.ApitShopConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;



@Controller
@RequestMapping("/member/shopconfig")
public class MTMemberShopConfigController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberShopConfigController.class);

    @Autowired
    private ApitShopConfigService apitShopConfigService;

    @RequestMapping("/updateConfig")
    @ResponseBody
    public ZLResult updateShopState(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = apitShopConfigService.updateConfig(params);
        return parseRpcResultForData(rpcResult);
    }

}
