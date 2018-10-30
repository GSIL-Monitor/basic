package com.wgb.controller.sp.menu;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitSrvMenuService;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fxs
 * @create 2018-10-09 16:50
 **/
@Controller
@RequestMapping("srv/menu")
public class SrvMenuController extends SPBaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvMenuController.class);

    @Autowired
    private ApitSrvMenuService apitSrvMenuService;

    @RequestMapping("queryMenuListByLoginUser")
    @ResponseBody
    public ZLResult queryMenuListByLoginUser(HttpServletRequest request){
        ZLResult result = ZLResult.Success();
        try {

            Map<String, Object> params = getParams();
            Map<String ,Object> queryParams = new HashMap<>();
            queryParams.put("account" ,MapUtils.getString(params ,Contants.LOGIN_USER_SERVER_ACCOUNT));
            ZLRpcResult rpcResult = apitSrvMenuService.getRemoteMenuList(queryParams);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询当前服务商菜单列表系统异常!" ,ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;

    }

}
