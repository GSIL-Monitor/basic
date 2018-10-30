package com.wgb.controller.sg;

import com.wgb.bean.ZLResult;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.mbms.web.ApitMemberAssistantService;
import com.wgb.util.MD5Util;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yjw on 2017/11/17.
 */
@Controller
@RequestMapping("/member/assistant")
public class SGXCXLoginController extends SGXCXBaseController {

    private static Logger logger = LoggerFactory.getLogger(SGXCXLoginController.class);

    @Autowired
    private ApitMemberAssistantService apitMemberAssistantService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/entry/login")
    @ResponseBody
    public ZLResult login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String errorMsg = null;
        Map<String, Object> userInfo = null;
        userInfo = new HashMap<>();
        Map<String, Object> params = getParams();

        String account = MapUtils.getString(params, "account");
        String password = MD5Util.GetMD5Code(MapUtils.getString(params, "password"));
        params.put("password", "");

        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            errorMsg = "用户名或密码不能为空!";
        } else {

            try {
                //查询导购员
                ZLRpcResult zlRpcResult = apitMemberAssistantService.searchAssistantInfo(params);
                if (zlRpcResult.success()) {
                    userInfo = zlRpcResult.getMap();
                    String token = UUID.randomUUID().toString();
                    if (MapUtils.isEmpty(userInfo)) {
                        errorMsg = "手机号未激活";
                    } else if (!StringUtils.equals(password, MapUtils.getString(userInfo, "password"))) {
                        errorMsg = "密码错误";
                    } else {
                        userInfo.put("sessionId", sessionId);
                        userInfo.put("access_code", token);
                    }
                } else {
                    errorMsg = zlRpcResult.getErrorMsg();
                }
            } catch (ServiceException e) {
                errorMsg = "系统异常！";
            }
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            return ZLResult.Error(errorMsg);
        }
        return ZLResult.Success(userInfo);
    }

    @RequestMapping("/entry/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        String accessCode = MapUtils.getString(params, "access_code", "");
        if (StringUtils.isNotEmpty(accessCode)) {
            cacheService.removeUserInfo(accessCode);
        }
        return ZLResult.Success();
    }

}
