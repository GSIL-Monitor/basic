package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiUserService;
import com.wgb.util.HttpRequestConstant;
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
 * Created by qiuxh on 2018/8/6.
 */
@Controller
@RequestMapping("/boss")
public class MTBOSSLoginController extends MTBaseController{

    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private ApiUserService apiUserService;

    @RequestMapping("/entry/login")
    @ResponseBody
    public ZLResult login(HttpServletRequest request) {

        Map<String, Object> params = getPubParams();

        /*params.put("account","13952036041");
        params.put("password","96e79218965eb72c92a549dd5a330112");*/

        String account = MapUtils.getString(params, "account");
        String password = MapUtils.getString(params, "password");
        String shopcode = MapUtils.getString(params, "shopcode");
        String username = MapUtils.getString(params, "username");
        /*String type = MapUtils.getString(params, "type");*/

        //密码不能为空
        if (StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult rpcResult = null;
        if ((StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(shopcode)) ||
                (StringUtils.isNotEmpty(account))) {

            //查询老板助手用户信息
            rpcResult = apiUserService.getLoginUserForBOSS(params);

            if (!rpcResult.success()) {
                return ZLResult.Error(rpcResult.getErrorCode());
            }

            //查询用户信息
            Map<String, Object> resultMap = rpcResult.getMap();
            if (resultMap != null) {
                Map<String, Object> userInfo = MapUtils.getMap(resultMap, "user_info");
                String token = MapUtils.getString(resultMap, "token");

                //将account放到缓存中
                RedisFactory.getPassportClient().set(HttpRequestConstant.MT_BOSS_ACCOUNT_PREFIX + token, MapUtils.getString(userInfo, "account"), SESSION_TIME_SECONDS);

                Map<String, Object> result = new HashMap<String, Object>();
                result.put("access_code", token);
                result.put("user_info", userInfo);
                return ZLResult.Success(result);
            } else {
                return ZLResult.Error(ServiceException.CODE_20104);
            }
        } else{
            /*errorMsg = "用户名或手机号不能为空!";*/
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
    }

    @RequestMapping("/entry/logout")
    @ResponseBody
    public ZLResult logout(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String token = MapUtils.getString(params, "access_code", "");
        if (StringUtils.isNotEmpty(token)) {
            RedisFactory.getPassportClient().del(HttpRequestConstant.MT_BOSS_ACCOUNT_PREFIX + token);
        }
        return ZLResult.Success();
    }
}
