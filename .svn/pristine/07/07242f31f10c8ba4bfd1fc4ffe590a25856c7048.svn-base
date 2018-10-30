package com.wgb.controller.sp.getpwd;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.controller.sp.home.SrvHomeController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitGetPwdService;
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
 * Created by wgb on 2017/2/14.
 */
@Controller
@RequestMapping("/getpwd")
public class GetPwdController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPwdController.class);

    @Autowired
    private ApitGetPwdService apitGetPwdService;



    @RequestMapping("/entry/sendYzm")
    @ResponseBody
    public ZLResult sendYzm(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, "account");
        if (StringUtils.isEmpty(account)) {
            return ZLResult.Error("账号输入为空");
        }
        try {
            ZLRpcResult rpcResult = apitGetPwdService.sendYzm(params);
            result = parseRpcResultForMsg(rpcResult);
        } catch (Exception ex){
            LOGGER.error("忘记密码发送验证码失败!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/entry/checkYzm")
    @ResponseBody
    public ZLResult checkYzm(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLResult result = ZLResult.Success();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm)) {
            return ZLResult.Error("参数缺失");
        }
        try {
            ZLRpcResult rpcResult = apitGetPwdService.checkYzm(params);
            result = parseRpcResult(rpcResult);
        } catch (Exception ex){
            LOGGER.error("忘记密码校验验证码系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/entry/updatePassword")
    @ResponseBody
    public ZLResult updatePassword(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        String password = MapUtils.getString(params, "password");
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm) || StringUtils.isEmpty(password)) {
            return ZLResult.Error("缺失参数");
        }
        try {
            ZLRpcResult rpcResult = apitGetPwdService.updatePassword(params);
            result = parseRpcResultForMsg(rpcResult);
        } catch (Exception ex){
            LOGGER.error("服务商修改密码系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/entry/checkAccount")
    @ResponseBody
    public ZLResult checkAccount(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, "account");
        if (StringUtils.isEmpty(account)) {
            return ZLResult.Error("缺失参数");
        }
        try {
            ZLRpcResult rpcResult = apitGetPwdService.checkAccount(params);
            result = parseRpcResult(rpcResult);
        } catch (Exception ex){
            LOGGER.error("服务商修改密码校验帐号系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }
}
