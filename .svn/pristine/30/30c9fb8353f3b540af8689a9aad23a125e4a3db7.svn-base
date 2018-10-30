package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.scpms.web.ApitScpXcxUserService;
import com.wgb.util.HttpClientUtil;
import org.apache.commons.lang.StringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/entry")
public class SCPXCXLoginController extends SCPXCXBaseController {

    @Autowired
    private ApitScpXcxUserService apitScpXcxUserService;

    @RequestMapping("/login/loginScpXcx")
    @ResponseBody
    public ZLResult login(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();
        String appid = "wxd59a87fe52b29b33";
        String secret = "dde28ffcda536d378d8285cbadf7d22b";
        String js_code = MapUtils.getString(params, "code");

        //访问微信接口获取用户openid
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + js_code +
                "&grant_type=authorization_code";
        String httpResult = HttpClientUtil.httpGetRequest(requestUrl);
        JSONObject data = JSONObject.fromObject(httpResult);
        String openid = MapUtils.getString(data, "openid");
        if (StringUtils.isEmpty(openid)) {
            throw new ServiceException("权限验证失败,请联系客服!");
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("openid", openid);
        String errorMsg = "";
        ZLRpcResult rpcResult = apitScpXcxUserService.getXcxUserInfo(userInfo);
        Map<String, Object> user = rpcResult.getMap();
        //校验登录参数
        errorMsg = checkLoginUser(user);

        if (StringUtils.isNotEmpty(errorMsg)) {
            return ZLResult.Error(errorMsg);
        }

        return ZLResult.Success(user);
    }

    /**
     * @param user
     * @return
     */
    private String checkLoginUser(Map<String, Object> user) {

        if (MapUtils.isEmpty(user)) {
            return "获取用户信息失败";
        }

        return "";
    }

    /**
     * 更新小程序用户信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login/updateScpXcxUser")
    @ResponseBody
    public ZLResult updateScpXcxUser(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> params = getParams();

        apitScpXcxUserService.updateXcxUser(params);

        return ZLResult.Success();
    }

}
