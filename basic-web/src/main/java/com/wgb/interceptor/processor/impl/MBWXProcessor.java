package com.wgb.interceptor.processor.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.LoginInfoService;
import com.wgb.service.dubbo.pays.admin.ApiPayService;
import com.wgb.service.dubbo.wxms.web.ApitVisitLogService;
import com.wgb.service.dubbo.wxms.web.ApitWxShopMemberService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2018/7/30 0030.
 */
public class MBWXProcessor extends AdapterProcessor {

    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private ApiPayService apiPayService;
    @Autowired
    private ApitVisitLogService apitVisitLogService;
    @Autowired
    private LoginInfoService loginInfoService;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitWxShopMemberService apitWxShopMemberService;
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        logger.info("请求开始的参数：" + params);
        //商户编码必传
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params,"branchcode");
        logger.info("商户编码：" + shopcode+"门店编码："+branchcode);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }
        String test = MapUtils.getString(params, HttpRequestConstant.ZL_REQUEST_TEST, "");
        String openid = "";
        //test = "oztU-1Yf-qbkYRBIg-s1mSfkixZc";

        if (StringUtils.isNotEmpty(test) && !SystemConfig.SYSTEM_START_ENV.equalsIgnoreCase(SystemStartEnv.prod.toString())) {
            openid = test;
        } else {

            //cookie
            Map<String, Object> cookieMap = HttpRequestUtil.getRequestCookies(request);
            logger.info("查询一下cookie里的参数:" + cookieMap);
            //token
            openid = MapUtils.getString(cookieMap, HttpRequestConstant.MB_TOKEN_ID);
            logger.info("cookie里面存的openid:"+openid);

        }

        if (StringUtils.isNotEmpty(openid)) {

            Map<String, Object> user = loginInfoService.getLoginMemberForWeiXin(openid, shopcode, request);
            logger.info("最终获取的用户信息user:" + user);

            HttpRequestUtil.setParams(request, getParams(request, user));

            // 记录访问日志
           if (user != null && StringUtils.isNotEmpty(branchcode)) {
                Map<String, Object> logParams = new HashMap<>();
                logParams.put("shopcode", MapUtils.getString(user, "shopcode"));
                logParams.put("openid", MapUtils.getString(user, "openid"));
                apitVisitLogService.addLog(logParams);
               //统计不同身份用户的访问记录
                user.put("branchcode",branchcode);
                apitWxShopMemberService.saveLoginLogs(user);
            }
            return  true;
        }
        return false;
    }

    /**
     * @param request
     * @param memberInfo
     * @return
     */
    public Map<String, Object> getParams(HttpServletRequest request, Map<String, Object> memberInfo) {

        //获取请求参数
        Map<String, Object> requestParams = ParamsUtil.getDefaultParams(request);

        requestParams.put("loginmemberid", MapUtils.getString(memberInfo, "id"));
        requestParams.put("loginmembershopcode", MapUtils.getString(memberInfo, "shopcode"));
        requestParams.put("loginmemberopenid", MapUtils.getString(memberInfo, "openid"));
        requestParams.put("loginmemberheadpic", MapUtils.getString(memberInfo, "headpic"));
        requestParams.put("loginmemberweixin", MapUtils.getString(memberInfo, "weixin"));
        requestParams.put("nickname", MapUtils.getString(memberInfo, "nickname"));
        requestParams.put("headpic", MapUtils.getString(memberInfo, "headpic"));
        requestParams.put("loginmembercode", MapUtils.getString(memberInfo, "membercode"));
        requestParams.put("shopname", MapUtils.getString(memberInfo, "shopname"));
        requestParams.put("openid", MapUtils.getString(memberInfo, "openid"));
        //requestParams.put("shopcode",MapUtils.getString(memberInfo, "shopcode"));
        requestParams.put("loginuserid", MapUtils.getString(memberInfo, "id"));
        requestParams.put("membermodel", MapUtils.getString(memberInfo, "membermodel"));
        requestParams.put("loginbranchcode", MapUtils.getString(memberInfo, "branchcode"));
        requestParams.put("loginbranchname", MapUtils.getString(memberInfo, "branchname"));
        requestParams.put("loginmobile", MapUtils.getString(memberInfo, "mobile"));


       /* //获取访问页面的URL
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append('?');
            url.append(request.getQueryString());
        }
        String jsUrl = url.toString();
        requestParams.put("jsUrl",jsUrl);*/
        return requestParams;
    }
}
