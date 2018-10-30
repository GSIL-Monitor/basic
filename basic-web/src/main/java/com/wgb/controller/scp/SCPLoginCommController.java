package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.service.LoginInfoService;
import com.wgb.service.ScpLoginInfoService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.HttpRequestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class SCPLoginCommController extends SCPBaseController {

    @Autowired
    private ScpLoginInfoService scpLoginInfoService;

    @RequestMapping("/common/getScpInfo")
    @ResponseBody
    public ZLResult getInfo(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, Contants.LOGIN_USER_ACCOUNT);

        //用户信息
        Map<String, Object> user = scpLoginInfoService.getRemoteUserInfo(account);

        if (MapUtils.isEmpty(user)) {
            return ZLResult.Error();
        }

        return ZLResult.Success(user);
    }

}
