package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.service.LoginInfoService;
import com.wgb.service.dubbo.urms.web.ApitPortalRoleService;
import com.wgb.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限控制类
 */
@Controller
@RequestMapping("/auth")
public class MTAuthController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String[] whites = {
            "/entry/"};

    @Autowired
    private LoginInfoService loginInfoService;

    @Autowired
    private ApitPortalRoleService apitPortalRoleService;

    @RequestMapping("/checkUrlAuth")
    @ResponseBody
    public ZLResult checkUrlAuth(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        //获取入参数据
        Map<String, Object> params = getParams();
        logger.info("开始判断角色权限:" + params);
        String account = MapUtils.getString(params, Contants.LOGIN_USER_ACCOUNT);
        String url = MapUtils.getString(params, "url", "");
        String sysName = MapUtils.getString(params, "sysname", "");

        //判断是否需要鉴权
        if (!isNeedCheckLogin(url)) {
            return ZLResult.Success(true);
        }

        if (StringUtils.isEmpty(account)) {
            return ZLResult.Success(false);
        }

        //判断account是否正确
        Map<String, Object> user = loginInfoService.getRemoteUserInfo(account);
        if (MapUtils.isEmpty(user)) {
            return ZLResult.Success(false);
        }

        //判断菜单是否有权限
        List<Map<String, Object>> menuList = loginInfoService.getRemoteMenuList(user);
        if (!checkAuth(menuList, url, sysName)) {
            return ZLResult.Success(false);
        }
        long endTime = System.currentTimeMillis();
        logger.info("结束判断角色权限:" + (endTime - startTime) + "ms");
        return ZLResult.Success(true);
    }

    /**
     * @param uri
     * @return
     */
    private boolean isNeedCheckLogin(String uri) {
        if (whites == null || whites.length == 0) {
            return true;
        }
        for (String white : whites) {
            if (uri.indexOf(white) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断请求的路径是否需要鉴权
     *
     * @param menuList
     * @param uri
     * @return
     */
    private boolean checkAuth(List<Map<String, Object>> menuList, String uri, String sysName) {

        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Map<String, Object> menuItem : menuList) {
                String type = MapUtils.getString(menuItem, "type", "");
                String menuSysName = MapUtils.getString(menuItem, "sysname", "");
                if (type.equals(CommonConstant.MENU_TYPE_COMMON) && sysName.equals(menuSysName)) {//菜单
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

    /**
     * 授权菜单
     *
     * @param request
     * @return
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public ZLResult getDetail(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        logger.info("开始获取授权菜单:" + params);
        List<Map<String, Object>> menuList = apitPortalRoleService.getMenuDetail(params).getList();
        Map<String, Object> roleMap = apitPortalRoleService.getRole(params).getMap();

        String menus = MapUtils.getString(roleMap, "menus", "");
        String[] menuArr = menus.split(",");

        setMenuChecked(menuList, menuArr);
        checkMenus(menuList);
        Map<String, Object> menuObject = new HashMap<String, Object>();
        menuObject.put("detailList", CommonUtil.getTreeList(menuList, "-1", "name"));
        long endTime = System.currentTimeMillis();
        logger.info("结束获取授权菜单:" + (endTime - startTime) + "ms");
        return ZLResult.Success(menuObject);
    }

    private void checkMenus(List<Map<String, Object>> menuList) {
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Map<String, Object> menu : menuList) {
                String level = MapUtils.getString(menu, "level");
                String checked = MapUtils.getString(menu, "checked", "");
                String pid = MapUtils.getString(menu, "pid", "");
                if (level.equals("4") && checked.isEmpty()) {
                    for (Map<String, Object> menus : menuList) {
                        String level1 = MapUtils.getString(menus, "level");
                        String id1 = MapUtils.getString(menus, "id", "");
                        String pid1 = MapUtils.getString(menus, "pid", "");
                        if (level1.equals("3") && id1.equals(pid)) {
                            menus.remove("checked");
                            for (Map<String, Object> menus1 : menuList) {
                                String level2 = MapUtils.getString(menus1, "level");
                                String checked2 = MapUtils.getString(menus1, "checked", "");
                                String id2 = MapUtils.getString(menus1, "id", "");
                                String pid2 = MapUtils.getString(menus1, "pid", "");
                                if (level2.equals("2") && id2.equals(pid1)) {
                                    menus1.remove("checked");
                                    for (Map<String, Object> menus2 : menuList) {
                                        String level3 = MapUtils.getString(menus2, "level");
                                        String checked3 = MapUtils.getString(menus2, "checked", "");
                                        String id3 = MapUtils.getString(menus2, "id", "");
                                        if (level3.equals("1") && checked3.equals("1") && id3.equals(pid2)) {
                                            menus2.remove("checked");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据用户角色菜单，设置菜单列表选中
     *
     * @param menuList
     * @param menuArr
     */
    private void setMenuChecked(List<Map<String, Object>> menuList, String[] menuArr) {
        if (CollectionUtils.isNotEmpty(menuList) && menuArr.length != 0) {
            for (Map<String, Object> menu : menuList) {
                for (String menuid : menuArr) {
                    if (MapUtils.getString(menu, "id").equals(menuid)) {
                        menu.put("checked", "1");
                        break;
                    }
                }
            }
        }
    }

}
