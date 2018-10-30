package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.scpms.web.ApitScpRegService;
import com.wgb.util.Contants;
import com.wgb.util.HttpRequestConstant;
import com.wgb.util.UUIDGenerator;
import com.wgb.util.Validator;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 11609 on 2018/8/8.
 */
@Controller
@RequestMapping("/entry/reg")
public class SCPRegController extends SCPBaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ApitScpRegService apitScpRegService;

    @RequestMapping("/regScp")
    @ResponseBody
    public ZLResult reg(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getPubParams();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        String password = MapUtils.getString(params, "password");
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm)) {
            return ZLResult.Error("参数错误");
        }

        String _yzm;
        if (Validator.isMobile(account)) {
            _yzm = cacheService.getYzm(account, Contants.SMS_REG_TYPE);
        } else {
            return ZLResult.Error("账号格式错误");
        }
        if (!Validator.isPassword(password)) {
            return ZLResult.Error("密码必须为6-18位数字、字母、符号");
        }
        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            apitScpRegService.insertRegScp(params);

            String token = UUIDGenerator.getUUID();

            //添加sid到服务根路径
            Cookie cookie = new Cookie(HttpRequestConstant.SCP_TOKEN_ID, token);
            cookie.setMaxAge(2592000);
            cookie.setPath("/");
            response.addCookie(cookie);

            RedisFactory.getPassportClient().set(HttpRequestConstant.SCP_PC_ACCOUNT_PREFIX + token, account);
            return ZLResult.Success();
        }
        return ZLResult.Error("验证码已过期");
    }

}
