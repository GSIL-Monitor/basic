package com.wgb.controller.mb;

import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.LoginInfoService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller通用父类
 */
@Controller
@Qualifier("baseController")
public abstract class MBXCXBaseController {

    private static final String PAGE = "page";
    private static final String PAGESIZE = "pageSize";

    @Autowired
    private LoginInfoService loginInfoService;

    public Map<String, Object> getXcxParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> params = HttpRequestUtil.getParams(request);
        return params;
    }

    public Map<String, Object> getParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> params = HttpRequestUtil.getParams(request);
        return params;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getBranchParams(HttpServletRequest request, Map<String, Object> branchInfo) {
        Map<String, Object> requestParams = ParamsUtil.handleServletParameter(request);

        if (StringUtils.isEmpty(requestParams.get(PAGE) == null ? null : requestParams.get(PAGE).toString())) {
            requestParams.put(PAGE, Contants.PAGE_START);

        } else {
            requestParams.put(PAGE, MapUtils.getIntValue(requestParams, PAGE, Contants.PAGE_START));
        }
        if (StringUtils.isEmpty(requestParams.get(PAGESIZE) == null ? null : requestParams.get(PAGESIZE).toString())) {
            requestParams.put(PAGESIZE, Contants.PAGE_SIZE);
        } else {
            requestParams.put(PAGESIZE, MapUtils.getIntValue(requestParams, PAGESIZE, Contants.PAGE_SIZE));
        }
        requestParams.put(Contants.USER_IP, CommonUtil.getRemortIP(request));
        requestParams.put("membermodel",MapUtils.getString(branchInfo, "membermodel"));
        requestParams.put("loginuserbranchcode",MapUtils.getString(branchInfo, "branchcode"));
        requestParams.put("loginuserbranchname",MapUtils.getString(branchInfo, "branchname"));
        return requestParams;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getParams(HttpServletRequest request, Map<String, Object> memberInfo) {
        Map<String, Object> requestParams = ParamsUtil.handleServletParameter(request);

        if (StringUtils.isEmpty(requestParams.get(PAGE) == null ? null : requestParams.get(PAGE).toString())) {
            requestParams.put(PAGE, Contants.PAGE_START);

        } else {
            requestParams.put(PAGE, MapUtils.getIntValue(requestParams, PAGE, Contants.PAGE_START));
        }
        if (StringUtils.isEmpty(requestParams.get(PAGESIZE) == null ? null : requestParams.get(PAGESIZE).toString())) {
            requestParams.put(PAGESIZE, Contants.PAGE_SIZE);
        } else {
            requestParams.put(PAGESIZE, MapUtils.getIntValue(requestParams, PAGESIZE, Contants.PAGE_SIZE));
        }

        requestParams.put(Contants.USER_IP, CommonUtil.getRemortIP(request));
        requestParams.put("loginmemberid", MapUtils.getString(memberInfo, "memberid"));
        requestParams.put("memberid", MapUtils.getString(memberInfo, "memberid"));
        requestParams.put("loginmembername", MapUtils.getString(memberInfo, "membername"));
        requestParams.put("loginnickname", MapUtils.getString(memberInfo, "buyernickname"));
        requestParams.put("telephone", MapUtils.getString(memberInfo, "mobile"));
        requestParams.put("loginmembershopcode", MapUtils.getString(memberInfo, "shopcode"));
        requestParams.put("loginmemberxcxopenid", MapUtils.getString(memberInfo, "xcxopenid"));
        requestParams.put("loginmemberheadpic", MapUtils.getString(memberInfo, "headpic"));
        requestParams.put("loginmemberweixin", MapUtils.getString(memberInfo, "weixin"));
        requestParams.put("loginmembercode", MapUtils.getString(memberInfo, "membercode"));
        requestParams.put("shopname", MapUtils.getString(memberInfo, "shopname"));
        requestParams.put("xcxopenid",MapUtils.getString(memberInfo, "xcxopenid"));
        requestParams.put("loginuserid",MapUtils.getString(memberInfo, "id"));
        requestParams.put("userid",MapUtils.getString(memberInfo, "id"));
        requestParams.put("membermodel",MapUtils.getString(memberInfo, "membermodel"));
        requestParams.put("loginbranchcode",MapUtils.getString(memberInfo, "branchcode"));
        requestParams.put("loginbranchname",MapUtils.getString(memberInfo, "branchname"));
        requestParams.put("loginuserbranchcode",MapUtils.getString(memberInfo, "branchcode"));
        requestParams.put("loginuserbranchname",MapUtils.getString(memberInfo, "branchname"));
        requestParams.put("dadacode",MapUtils.getString(memberInfo, "dadacode"));
        requestParams.put("recommendstatus",MapUtils.getString(memberInfo, "recommendstatus"));

        return requestParams;
    }

    /**
     * 获取当前登录微信用户信息
     *
     * @param request
     * @return
     */
    public Map<String, Object> getWxUserInfo(HttpServletRequest request) {
        String shopcode = request.getParameter("shopcode");
        String branchcode = request.getParameter("branchcode");
        String xcxopenid = request.getParameter("xcxopenid");
        if (StringUtils.isEmpty(branchcode) || StringUtils.isEmpty(shopcode) || StringUtils.isEmpty(xcxopenid)) {
            throw new ServiceException("未获取用户必要参数!");
        }

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        params.put("shopcode", shopcode);
        params.put("branchcode", branchcode);
        params.put("xcxopenid", xcxopenid);

        Map<String, Object> branchInfo = loginInfoService.getWxBranchInfo(shopcode, branchcode);
        Map<String, Object> memberInfo = loginInfoService.getMemberInfo(params);
        userInfo.put("xcxopenid", xcxopenid);
        userInfo.put("shopname", MapUtils.getString(memberInfo, "shopname"));
        userInfo.put("shopcode", shopcode);
        userInfo.put("branchcode", branchcode);
        userInfo.put("branchname", MapUtils.getString(branchInfo, "branchname"));
        userInfo.put("dadacode", MapUtils.getString(branchInfo, "dadacode"));
        userInfo.put("membermodel", MapUtils.getString(branchInfo, "membermodel"));
        userInfo.put("id", MapUtils.getString(memberInfo, "id"));
        userInfo.put("headpic", MapUtils.getString(memberInfo, "headpic"));
        userInfo.put("weixin", MapUtils.getString(memberInfo, "weixin"));
        userInfo.put("mobile", MapUtils.getString(memberInfo, "mobile"));
        userInfo.put("membercode", MapUtils.getString(memberInfo, "membercode"));
        userInfo.put("memberid", MapUtils.getString(memberInfo, "memberid"));
        userInfo.put("membername", MapUtils.getString(memberInfo, "realname"));
        userInfo.put("buyernickname", MapUtils.getString(memberInfo, "nickname"));

        return userInfo;
    }

    public Map<String, Object> getPubParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        return params;
    }

    public Object FailResult(int errorCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "0");
        result.put("errcode", errorCode);
        result.put("errmsg", ServiceException.getEnMsg(errorCode));
        return getFinalResult(result);
    }

    public Object RpcSuccessResult(ZLRpcResult rpcResult) {
        Object data = null;
        if (rpcResult != null) {
            data = rpcResult.getData();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "1");
        result.put("result", data);
        return getFinalResult(result);
    }

    /**
     * 用于处理jsonp兼容性返回
     *
     * @param result
     * @return
     */
    private Object getFinalResult(Map<String, Object> result) {
        ApiUtil.formatObjectForApi(result);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String callback = request.getParameter("callback");
        if (StringUtils.isEmpty(callback)) {
            return result;
        } else {
            return new JSONPObject(callback, result);
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @param request
     * @return
     */
    public Map<String, Object> getBranchInfo(HttpServletRequest request) {
        String shopcode = request.getParameter("shopcode");
        String branchcode = request.getParameter("branchcode");
        Map<String, Object> branchInfo = loginInfoService.getWxBranchInfo(shopcode, branchcode);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("branchname", MapUtils.getString(branchInfo, "branchname"));
        userInfo.put("membermodel", MapUtils.getString(branchInfo, "membermodel"));
        return userInfo;
    }

    /**
     * 设置门店参数
     *
     * @param params
     */
    public void setBranchParams(Map<String, Object> params) {
        params.put("branchcode", MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE));
        params.put("branchname", MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_NAME));
    }
}
