package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.scpms.web.ApitScpUserService;
import com.wgb.service.dubbo.sms.web.ApiEmailService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.service.dubbo.urms.web.ApitPortalUserService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.Validator;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiuxh on 2018/5/19.
 */
@Controller
@RequestMapping("/entry/getpwd")
public class SCPGetPwdController extends SCPBaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ApitScpUserService apitScpUserService;

    @RequestMapping("/updatePasswordScp")
    @ResponseBody
    public ZLResult updatePasswordScp(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        String password = MapUtils.getString(params, "password");
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm) || StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }

        String _yzm = cacheService.getYzm(account, Contants.SMS_RESET_TYPE);
        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            apitScpUserService.updatePasswordScp(params);
            return ZLResult.Success();
        }
        return ZLResult.Error("验证码已过期");
    }
}
