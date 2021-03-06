package com.wgb.controller.sp.address;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitServiceAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author fxs
 * @create 2018-07-12 11:58
 **/
@Controller
@RequestMapping("seviceaddress")
public class   ServiceAddressController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAddressController.class);

    @Autowired
    private ApitServiceAddressService apitServiceAddressService;

    @RequestMapping("queryServiceAddress")
    @ResponseBody
    public ZLResult queryServiceAddress(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        try {
            ZLRpcResult rpcResult = apitServiceAddressService.queryServiceAddress(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.info("查询服务商最新使用地址系统异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
