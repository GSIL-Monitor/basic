package com.wgb.controller.sp.shop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitSrvAssociationShopService;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/srv/shop")
public class SrvAssociationShopController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvAssociationShopController.class);

    @Autowired
    private ApitSrvAssociationShopService apitSrvAssociationShopService;

    /*
     *获取关联商户数据
     */
    @RequestMapping("/getShopInfo")
    @ResponseBody
    public ZLResult getShopInfo(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        String keyword = MapUtils.getString(params, "keyword", "");
        if (StringUtils.isEmpty(keyword)) {
            return ZLResult.Error("请输入正确的商户ID或商户联系人手机号！");
        }
        try {
            ZLRpcResult rpcResult = apitSrvAssociationShopService.getShopInfo(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询服务商商户关联名单系统异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/associationShop/sendYzm")
    @ResponseBody
    public ZLResult sendYzm(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        if (StringUtils.isEmpty(shopcode)) {
            result = ZLResult.Error("商户ID必须输入");
        }
        try {
            ZLRpcResult rpcResult = apitSrvAssociationShopService.sendYzm(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception ex){
            LOGGER.error("关联商户发送验证码系统异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    /*
     *关联商户
     */
    @RequestMapping("/addAssociationInfo")
    @ResponseBody
    public ZLResult addAssociationInfo(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        try {
            ZLRpcResult rpcResult = apitSrvAssociationShopService.addAssociationInfo(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception ex){
            LOGGER.error("关联商户发送验证码系统异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}