package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.LoginInfoService;
import com.wgb.service.ScpLoginInfoService;
import com.wgb.service.dubbo.scpms.web.ApitScpLoginService;
import com.wgb.service.portal.PortalUserService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/entry")
public class SCPPCLoginController extends SCPBaseController {

    @Autowired
    private ApitScpLoginService apitScpLoginService;

    @RequestMapping("/login/loginScp")
    @ResponseBody
    public ZLResult login(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getPubParams();
        params.put("_yzcode", request.getSession().getAttribute("yzcode"));

        //校验登录参数
        String errorMsg = checkLoginParams(params);

        if (StringUtils.isNotEmpty(errorMsg)) {
            request.setAttribute("errorMsg", errorMsg);
            return ZLResult.Error(errorMsg);
        }

        ZLRpcResult rpcResult = apitScpLoginService.getLoginUser(params);
        Map<String, Object> user = rpcResult.getMap();
        //校验登录参数
        errorMsg = checkLoginUser(user);

        if (StringUtils.isNotEmpty(errorMsg)) {
            return ZLResult.Error(errorMsg);
        }

        String account = MapUtils.getString(params, "account");
        String token = UUIDGenerator.getUUID();

        //添加sid到服务根路径
        Cookie cookie = new Cookie(HttpRequestConstant.SCP_TOKEN_ID, token);
        cookie.setMaxAge(2592000);
        cookie.setPath("/");
        response.addCookie(cookie);

        RedisFactory.getPassportClient().set(HttpRequestConstant.SCP_PC_ACCOUNT_PREFIX + token, account);
        return ZLResult.Success(token);
    }

    /**
     * @param params
     * @return
     */
    private String checkLoginParams(Map<String, Object> params) {

        String account = MapUtils.getString(params, ("account"));
        String password = MapUtils.getString(params, ("password"));

        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return "用户名或密码不能为空";
        }
        params.put("password", MD5Util.GetMD5Code(password));

        String yzcode = MapUtils.getString(params, ("yzcode"));
        String _yzcode = MapUtils.getString(params, ("_yzcode"));

        if (StringUtils.isEmpty(yzcode)) {
            return "参数错误，请与管理员联系";
        }

        if (StringUtils.isEmpty(_yzcode)) {
            return "验证码已过期，请重新输入";
        }

        if (!yzcode.toLowerCase().equals(_yzcode.toLowerCase())) {
            return "验证码不正确，请重新输入";
        }
        return "";
    }

    /**
     * @param user
     * @return
     */
    private String checkLoginUser(Map<String, Object> user) {

        if (MapUtils.isEmpty(user)) {
            return "登录失败，用户名或密码错误";
        }

        return "";
    }

}
