package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.service.LoginInfoService;
import com.wgb.util.*;
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
public class MTLoginCommController extends MTBaseController {

    @Autowired
    private LoginInfoService loginInfoService;

    @RequestMapping("/common/getInfo")
    @ResponseBody
    public ZLResult getInfo(HttpServletRequest request) {
        Map<String, Object> params = HttpRequestUtil.getParams(request);
        String account = MapUtils.getString(params, Contants.LOGIN_USER_ACCOUNT);
        //用户信息
        Map<String, Object> user = loginInfoService.getRemoteUserInfo(account);
        if (MapUtils.isEmpty(user)) {
            return ZLResult.Error();
        }

        //菜单数据
        List<Map<String, Object>> menuList = getOwnedMenus(loginInfoService.getRemoteMenuList(user));

        //门店信息
        Map<String, Object> branch = loginInfoService.getRemoteBranchInfo(user);

        //商户信息
        Map<String, Object> shop = loginInfoService.getRemoteShopInfo(user);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("user", user);
        result.put("branch", branch);
        result.put("shop", shop);
        result.put("menuList", menuList);
        return ZLResult.Success(result);
    }

    /**
     * @param menuList
     * @return
     */
    private List<Map<String, Object>> getOwnedMenus(List<Map<String, Object>> menuList) {
        if (CollectionUtils.isEmpty(menuList)) {
            return null;
        }

        for (Map<String, Object> menuItem : menuList) {
            String type = MapUtils.getString(menuItem, "type", "");
            String owned = MapUtils.getString(menuItem, "owned", "");
            String url = MapUtils.getString(menuItem, "url", "");
            if (type.equals("0") && owned.equals("1") && StringUtils.isNotEmpty(url)) {
                Map<String, Object> cur = menuItem;
                while (cur != null) {

                    cur.put("owned", "1");
                    String pid = MapUtils.getString(cur, "pid");

                    if (pid.equals(Contants.NODE_PARENT_PID)) {
                        cur = null;
                    } else {
                        cur = CommonUtil.getMapFromList(menuList, "id", pid);
                    }
                }
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> menuItem : menuList) {
            String owned = MapUtils.getString(menuItem, "owned", "");
            String type = MapUtils.getString(menuItem, "type", "");
            if (type.equals("0") && owned.equals("1")) {
                resultList.add(menuItem);
            }
        }

        return resultList;
    }
}
