package com.wgb.controller.scp.setting;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.scpms.web.ApitPubScpService;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/system/scp")
public class SCPPubScpController extends SCPBaseController {

    @Autowired
    private ApitPubScpService apitPubScpService;

    @RequestMapping("/getScpInfo")
    @ResponseBody
    public ZLResult getScpInfo(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPubScpService.getScpInfo(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/updatePubScp")
    @ResponseBody
    public ZLResult updatePubScp(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPubScpService.updatePubScp(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        String account = MapUtils.getString(params, Contants.LOGIN_USER_ACCOUNT);
        //如果处于登录状态，清除缓存
        if (StringUtils.isNotEmpty(account)) {
            String userKey = "scp_user_" + account;
            //更新用户数据到redis中
            RedisFactory.getPassportClient().setMapToJedis(null, userKey);
        }
        return ZLResult.Success();
    }
}
