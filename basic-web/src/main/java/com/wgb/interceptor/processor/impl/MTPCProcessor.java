package com.wgb.interceptor.processor.impl;

import com.wgb.cache.RedisFactory;
import com.wgb.exception.ServiceException;
import com.wgb.interceptor.processor.AdapterProcessor;
import com.wgb.service.LoginInfoService;
import com.wgb.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2018/7/11 0011.
 */
public class MTPCProcessor extends AdapterProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 登录Session超期时间 ，24小时
     */
    private static final int SESSION_TIME_SECONDS = 60 * 60 * 24;

    @Autowired
    private LoginInfoService loginInfoService;

    @Override
    public boolean adapter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        if(uri.startsWith("/mt/pos/")){
            return false;
        }
        return uri.startsWith(this.path);
    }

    @Override
    public boolean adapterSign(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        String sign = MapUtils.getString(params, HttpRequestConstant.ZL_REQUEST_SIGN, "");

        if (StringUtils.isNotEmpty(sign) && !SystemConfig.SYSTEM_START_ENV.equalsIgnoreCase(SystemStartEnv.prod.toString())) {
            return sign.equals(this.sign);
        } else {
            String uri = request.getRequestURI();
            if(uri.startsWith("/mt/pos/")){
                return false;
            }
            String serverName = request.getServerName();
            return serverName.startsWith(this.sign);
        }
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        String test = MapUtils.getString(params, HttpRequestConstant.ZL_REQUEST_TEST, "");

        String account;
        if (StringUtils.isNotEmpty(test) && !SystemConfig.SYSTEM_START_ENV.equalsIgnoreCase(SystemStartEnv.prod.toString())) {
            account = test;
        } else {
            //cookie
            Map<String, Object> cookieMap = HttpRequestUtil.getRequestCookies(request);

            //token
            String token = MapUtils.getString(cookieMap, HttpRequestConstant.MT_TOKEN_ID);
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException(ServiceException.SESSION_TIME_OUT);
            }

            //account
            account = RedisFactory.getPassportClient().get(HttpRequestConstant.MT_PC_ACCOUNT_PREFIX + token, SESSION_TIME_SECONDS);
            if (StringUtils.isEmpty(account)) {
                HttpRequestUtil.removeRootCookies(request, response, HttpRequestConstant.MT_TOKEN_ID);
                throw new ServiceException(ServiceException.SESSION_TIME_OUT);
            }
        }

        //判断account是否正确
        Map<String, Object> user = loginInfoService.getRemoteUserInfo(account);
        if (MapUtils.isEmpty(user)) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断菜单是否有权限
        List<Map<String, Object>> menuList = loginInfoService.getRemoteMenuList(user);
        //uri
        String uri = request.getRequestURI();
        if (!checkAuth(menuList, uri)) {
            throw new ServiceException(ServiceException.NO_PRIVILEGE);
        }

        HttpRequestUtil.setParams(request, getParams(request, user));
        return true;
    }

    /**
     * @param request
     * @return
     */
    public static Map<String, Object> getParams(HttpServletRequest request, Map<String, Object> userInfo) {

        //获取请求参数
        Map<String, Object> requestParams = ParamsUtil.getDefaultParams(request);

        //登陆者的ID
        requestParams.put(Contants.LOGIN_USER_ID, MapUtils.getString(userInfo, "id"));

        //登陆者的全称
        requestParams.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(userInfo, "fullname"));

        //登陆者所属商户的编码
        requestParams.put(Contants.LOGIN_USER_SHOP_CODE, MapUtils.getString(userInfo, "shopcode"));

        //登陆者所属的门店编码
        requestParams.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(userInfo, "branchcode"));

        //登陆者商务编码
        requestParams.put(Contants.LOGIN_USER_BUSINESS_CODE, MapUtils.getString(userInfo, "businesscode"));

        //登陆者的门店名称
        requestParams.put(Contants.LOGIN_USER_BRANCH_NAME, MapUtils.getString(userInfo, "branchname"));

        //登陆者是否属于总店
        requestParams.put(Contants.LOGIN_USER_BRANCH_ISHEAD, MapUtils.getString(userInfo, "ishead"));

        //登陆者的商户联系方式
        requestParams.put(Contants.LOGIN_USER_SHOP_TEL, MapUtils.getString(userInfo, "tel"));

        //登陆者门店的配送类型
        requestParams.put(Contants.LOGIN_USER_DEFAULT_DISTPRICETYPE, MapUtils.getString(userInfo, "defaultdistpricetype"));

        //登陆者所属门店的会员模式
        requestParams.put(Contants.LOGIN_USER_MEMBERMODEL, MapUtils.getString(userInfo, "membermodel"));

        //登陆者用户名称
        requestParams.put(Contants.LOGIN_USER_NAME, MapUtils.getString(userInfo, "username"));

        //登陆者的账号
        requestParams.put(Contants.LOGIN_USER_ACCOUNT, MapUtils.getString(userInfo, "account"));

        //登陆者所属门店默认的配送地编码
        requestParams.put(Contants.LOGIN_USER_DEFAULT_DISTCENTERCODE, MapUtils.getString(userInfo, "defaultdistcentercode"));

        //登陆者所属门店参考门店价钱
        requestParams.put(Contants.LOGIN_USER_REFER_PRICE_BRANCHCODE, MapUtils.getString(userInfo, "referpricebranchcode"));

        //登陆者美团是否映射
        requestParams.put(Contants.LOGIN_USER_IS_MEITUAN_MAPPING, MapUtils.getString(userInfo, "ismeituanmapping"));

        //登陆者所属门店的支付方式
        requestParams.put(Contants.LOGIN_USER_IS_BRANCH_PAY, MapUtils.getString(userInfo, "isbranchpay"));

        //登陆者的商户名称
        requestParams.put(Contants.LOGIN_USER_SHOP_NAME, MapUtils.getString(userInfo, "shopname"));

        //登陆者的商户名称
        requestParams.put(Contants.LOGIN_USER_SHOP_SOFTWARETYPE, MapUtils.getString(userInfo, "softwaretype"));

        //登陆者所属商户所属行业编码
        requestParams.put(Contants.INDUSTRYID, MapUtils.getString(userInfo, "industryid"));

        //登陆者所属商户所属行业名称
        requestParams.put(Contants.INDUSTRYNAME, MapUtils.getString(userInfo, "industryname"));

        return requestParams;
    }

    /**
     * 判断请求的路径是否需要鉴权
     *
     * @param menuList
     * @param uri
     * @return
     */
    private boolean checkAuth(List<Map<String, Object>> menuList, String uri) {

        if (CollectionUtils.isNotEmpty(menuList)) {
            String currentSysName = SystemEnum.basic.toString();
            for (Map<String, Object> menuItem : menuList) {
                String type = MapUtils.getString(menuItem, "type", "");
                String menuSysName = MapUtils.getString(menuItem, "sysname", "");
                if (type.equals(CommonConstant.MENU_TYPE_COMMON) && currentSysName.equals(menuSysName)) {//菜单
                    String url = MapUtils.getString(menuItem, "url", "");
                    String owned = MapUtils.getString(menuItem, "owned", "");
                    if (url.equals(uri) && owned.equals("0")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
