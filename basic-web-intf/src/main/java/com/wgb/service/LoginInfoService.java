package com.wgb.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2018/3/17 0017.
 */
public interface LoginInfoService {

    String getAccountByToken(String token, int timeout);

    Map<String, Object> getRemoteUserInfo(String account);

    Map<String, Object> getRemoteBranchInfo(Map<String, Object> user);

    Map<String, Object> getRemoteShopInfo(Map<String, Object> user);

    List<Map<String, Object>> getRemoteMenuList(Map<String, Object> user);

    List<Map<String, Object>> getAllMenuList();

    Map<String, Object> getLoginMemberForWeiXin(String account,String shopcode, HttpServletRequest request);

    Map<String, Object> getMemberInfo(String openid,String shopcode);

    void updateMemberInfoInRedis(String shopcode,String account);

    List<Map<String, Object>> getWxBranchList(String shopcode);

    Map<String,Object> getWxBranchInfo(String shopcode, String branchcode);

    Map<String,Object> getMemberInfo(Map<String, Object> params);

    Map<String,Object> getLoginMemberForXCX(Map<String, Object> params);

    Map<String,Object> getRemoteAssistanrInfo(String accessCode, String account);

    Map<String, Object> getLmsUserInfo(String accessCode);
}
