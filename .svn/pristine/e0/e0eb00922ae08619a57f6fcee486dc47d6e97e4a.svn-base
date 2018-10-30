package com.wgb.controller.sp.auth;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.srvms.web.ApitSrvAuthService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author fxs
 * @create 2018-10-08 17:39
 **/
@Controller
@RequestMapping("auth")
public class SrvAuthController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvAuthController.class);

    @Autowired
    private ApitSrvAuthService apitSrvAuthService;

    @RequestMapping("/queryAuthByRoleId")
    @ResponseBody
    public ZLResult queryAuthByRoleId(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            String id = MapUtils.getString(params, "roleid", "");
            if (StringUtils.isEmpty(id)) {
                result.setErrorMsg("缺失参数");
                return result;
            }
            ZLRpcResult rpcResult = apitSrvAuthService.queryAuthByRoleId(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception e){
            LOGGER.error("查询服务商所有角色系统异常!",e);
            result.setErrorMsg("操作失败，请联系管理员!");
        }
        return result;
    }

    @RequestMapping("/updateAuth")
    @ResponseBody
    public ZLResult updateAuth(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvAuthService.updateAuth(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception e){
            LOGGER.error("修改角色权限系统异常!",e);
            result.setErrorMsg("操作失败，请联系管理员!");
        }
        return result;
    }
}
