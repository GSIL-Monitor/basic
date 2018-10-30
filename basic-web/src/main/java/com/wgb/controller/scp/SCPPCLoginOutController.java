package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/logout")
public class SCPPCLoginOutController extends SCPBaseController {

    @RequestMapping("/logoutScp")
    @ResponseBody
    public ZLResult logout(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params,Contants.LOGIN_USER_ACCOUNT);
        //如果处于登录状态，清除缓存
        if (StringUtils.isNotEmpty(account)) {
            String userKey = "scp_user_" + account;
            //更新用户数据到redis中
            RedisFactory.getPassportClient().setMapToJedis(null, userKey);
        }

        //注销登录
        HttpSession session = request.getSession();
        session.invalidate();

        //清除Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(HttpRequestConstant.SCP_TOKEN_ID)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return ZLResult.Success();
    }
}
