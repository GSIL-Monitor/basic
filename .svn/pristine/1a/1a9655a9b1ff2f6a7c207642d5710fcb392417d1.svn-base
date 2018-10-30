package com.wgb.controller.mt.dada;

import com.alibaba.fastjson.JSONObject;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.osrms.admin.ApiDadaService;
import com.wgb.service.dubbo.osrms.web.ApitDadaService;
import com.wgb.util.SystemConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/dada")
public class MTDadaController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTDadaController.class);

    @Autowired
    private ApitDadaService apitDadaService;

    @Autowired
    private ApiDadaService apiDadaService;


    @RequestMapping("/shop/addnewShop")
    @ResponseBody
    public ZLResult addOrder(HttpServletRequest request) {

        Map<String, Object> params =  getParams();

        ZLRpcResult zlRpcResult = null;
        try {
            //   系统调用
            zlRpcResult = apitDadaService.addnewShop(params);
        }catch (ServiceException sx){
            //系统级别异常
            sx.getStackTrace();
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if(!zlRpcResult.success()){
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        String shopcode=(String) zlRpcResult.getData();

        return  ZLResult.Success(shopcode);
    }

    @RequestMapping("/order/querydeliveryfee")
    @ResponseBody
    public ZLResult querydeliveryfee(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        String url="http://2o063233w7.imwork.net:49267/dada/entry/acceptorder";
        ZLRpcResult rpcResult = null;
        try {
            rpcResult = apiDadaService.querydeliveryfee(params,url);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!rpcResult.success()) {
            return ZLResult.Error(rpcResult.getErrorMsg()) ;
        }
        JSONObject json=(JSONObject)rpcResult.getData();
        return ZLResult.Success(json);
    }

    @RequestMapping("/order/addnewOrder.action")
    @ResponseBody
    public ZLResult addnewOrder(HttpServletRequest request) throws Exception{
        Map<String, Object> params = getParams();
        String testUrl="http://2o063233w7.imwork.net:49267/api/api-bin/dada/entry/acceptorder.action";
        String devTestUrl="http://2o063233w7.imwork.net:49267/api/api-bin/dada/entry/acceptorder.action";
        String ISUrl="https://ls.zhonglunnet.com/api/api-bin/dada/entry/acceptorder.action";
        ZLRpcResult rpcResult = null;
        try {
            rpcResult = apiDadaService.addnewOrder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!rpcResult.success()) {
            return ZLResult.Error(rpcResult.getErrorMsg()) ;
        }
        JSONObject json=(JSONObject)rpcResult.getData();
        return ZLResult.Success(json);
    }
}

