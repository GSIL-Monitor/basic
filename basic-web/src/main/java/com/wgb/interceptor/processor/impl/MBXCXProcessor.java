package com.wgb.interceptor.processor.impl;

import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.LoginInfoService;
import com.wgb.service.dubbo.wxms.web.ApitVisitLogService;
import com.wgb.service.dubbo.wxms.web.ApitXCXMemberService;
import com.wgb.util.HttpRequestUtil;
import com.wgb.util.ParamsUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2018/7/30 0030.
 */
public class MBXCXProcessor extends AdapterProcessor {

    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private ApitVisitLogService apitVisitLogService;

    @Autowired
    private ApitXCXMemberService apitXCXMemberService;
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String xcxopenid = MapUtils.getString(params, "xcxopenid");

        if (StringUtils.isEmpty(branchcode) || StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.OPER_ERROR);
        }

            Map<String, Object> user = loginInfoService.getLoginMemberForXCX(params);

        // 记录访问日志
        if (user != null){
            Map<String, Object> logParams = new HashMap<>();
            logParams.put("shopcode", MapUtils.getString(user, "shopcode"));
            logParams.put("openid", MapUtils.getString(user, "xcxopenid"));
            apitVisitLogService.addLog(logParams);
            //各种身份的用户的访问记录
            user.put("branchcode",branchcode);
            apitXCXMemberService.saveLoginLogs(user);

        }

        HttpRequestUtil.setParams(request, getParams(request, user));
        return true;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getParams(HttpServletRequest request, Map<String, Object> user) {

        //获取请求参数
        Map<String, Object> requestParams = ParamsUtil.getDefaultParams(request);

        requestParams.put("loginmemberid", MapUtils.getString(user, "memberid"));
        requestParams.put("memberid", MapUtils.getString(user, "memberid"));
        requestParams.put("loginmembername", MapUtils.getString(user, "realname"));
        requestParams.put("loginnickname", MapUtils.getString(user, "nickname"));
        requestParams.put("telephone", MapUtils.getString(user, "mobile"));
        requestParams.put("loginmembershopcode", MapUtils.getString(user, "shopcode"));
        requestParams.put("loginmemberxcxopenid", MapUtils.getString(user, "xcxopenid"));
        requestParams.put("loginmemberheadpic", MapUtils.getString(user, "headpic"));
        requestParams.put("loginmemberweixin", MapUtils.getString(user, "weixin"));
        requestParams.put("loginmembercode", MapUtils.getString(user, "membercode"));
        requestParams.put("shopname", MapUtils.getString(user, "shopname"));
        requestParams.put("xcxopenid", MapUtils.getString(user, "xcxopenid"));
        requestParams.put("loginuserid", MapUtils.getString(user, "id"));
        requestParams.put("userid", MapUtils.getString(user, "id"));
        requestParams.put("membermodel", MapUtils.getString(user, "membermodel"));
        requestParams.put("loginbranchcode", MapUtils.getString(user, "branchcode"));
        requestParams.put("loginbranchname", MapUtils.getString(user, "branchname"));
        requestParams.put("loginuserbranchcode", MapUtils.getString(user, "branchcode"));
        requestParams.put("loginuserbranchname", MapUtils.getString(user, "branchname"));
        requestParams.put("dadacode", MapUtils.getString(user, "dadacode"));
        requestParams.put("recommendstatus", MapUtils.getString(user, "recommendstatus"));
        return requestParams;
    }
}
