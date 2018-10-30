package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiUserService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pos")
public class MTPOSLoginController extends MTBaseController {

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

        String account = MapUtils.getString(params, "account");
        String password = MapUtils.getString(params, "password");
        String shopcode = MapUtils.getString(params, "shopcode");
        String username = MapUtils.getString(params, "username");
        String deviceuniquecode = MapUtils.getString(params, "deviceuniquecode");

        //密码不能为空
        if (StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //设备唯一识别码为空
        if (StringUtils.isEmpty(deviceuniquecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult rpcResult = null;
        if ((StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(shopcode)) ||
                (StringUtils.isNotEmpty(account))) {

            //查询用户信息
            rpcResult = apiUserService.getLoginUser(params);

            if (!rpcResult.success()) {
                return ZLResult.Error(rpcResult.getErrorCode());
            }

            //查询用户信息
            Map<String, Object> resultMap = rpcResult.getMap();
            if (resultMap != null) {

                //用户信息
                Map<String, Object> userInfo = MapUtils.getMap(resultMap, "user_info");

                //返回TOKEN
                String token = MapUtils.getString(resultMap, "token");

                //返回全局配置参数
                Map<String, Object> config = MapUtils.getMap(resultMap, "config");

                //将account放到缓存中
                RedisFactory.getPassportClient().set(HttpRequestConstant.MT_POS_ACCOUNT_PREFIX + token, MapUtils.getString(userInfo, "account"), SESSION_TIME_SECONDS);

                //封装返回结果
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("access_code", token);
                result.put("user_info", userInfo);
                result.put("config", config);
                return ZLResult.Success(result);
            } else {
                return ZLResult.Error(ServiceException.CODE_20104);
            }
        } else {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
    }

    @RequestMapping("/entry/logout")
    @ResponseBody
    public ZLResult logout(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String token = MapUtils.getString(params, "access_code", "");
        if (StringUtils.isNotEmpty(token)) {
            RedisFactory.getPassportClient().del(HttpRequestConstant.MT_POS_ACCOUNT_PREFIX + token);
        }
        return ZLResult.Success();
    }

    @RequestMapping("/entry/poslogin")
    @ResponseBody
    public ZLResult poslogin(HttpServletRequest request) {

        //获取前端参数
        Map<String, Object> params = getPubParams();
        String account = MapUtils.getString(params, "account");
        String password = MapUtils.getString(params, "password");
        String shopcode = MapUtils.getString(params, "shopcode");
        String username = MapUtils.getString(params, "username");
        String deviceuniquecode = MapUtils.getString(params, "deviceuniquecode");

        //密码不能为空
        if (StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //设备唯一识别码为空
        if (StringUtils.isEmpty(deviceuniquecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult rpcResult = null;
        if ((StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(shopcode)) ||
                (StringUtils.isNotEmpty(account))) {

            //查询用户信息
            rpcResult = apiUserService.getPosLoginUser(params);

            if (!rpcResult.success()) {
                return ZLResult.Error(rpcResult.getErrorCode());
            }

            //查询用户信息
            Map<String, Object> resultMap = rpcResult.getMap();
            if (resultMap != null) {

                //用户信息
                Map<String, Object> userInfo = MapUtils.getMap(resultMap, "userInfo");
                Map<String, Object> branchInfo = MapUtils.getMap(resultMap, "branchInfo");
                Map<String, Object> shopInfo = MapUtils.getMap(resultMap, "shopInfo");
                Map<String, Object> deviceInfo = MapUtils.getMap(resultMap, "deviceInfo");

                //配置文件
                String  ssoInfo = MapUtils.getString(resultMap, "ssoInfo");

                //返回TOKEN
                String token = MapUtils.getString(resultMap, "token");

                //将account放到缓存中
                RedisFactory.getPassportClient().set(HttpRequestConstant.MT_POS_ACCOUNT_PREFIX + token, MapUtils.getString(userInfo, "account"), SESSION_TIME_SECONDS);

                //封装返回结果
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("deviceInfo", deviceInfo);
                result.put("shopInfo", shopInfo);
                result.put("branchInfo", branchInfo);
                result.put("userInfo", userInfo);
                result.put("ssoInfo", ssoInfo);
                result.put("access_code", token);
                return ZLResult.Success(result);
            } else {
                return ZLResult.Error(ServiceException.CODE_20104);
            }
        } else {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
    }
}
