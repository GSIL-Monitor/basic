package com.wgb.controller.scp.xcx;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.scpms.web.ApitScpXcxUserService;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yjw
 * @date 2018/8/15
 */
@Controller
@RequestMapping("/entry/xcxuser")
public class ScpXcxUserController extends SCPXCXBaseController {

    @Autowired
    private ApitScpXcxUserService apitScpXcxUserService;

    @Autowired
    private CacheService cacheService;

    /**
     * 小程序个人中心
     *
     * @param request
     * @return
     */
    @RequestMapping("/getUserPersonalInfo")
    @ResponseBody
    public ZLResult getUserPersonalInfo(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitScpXcxUserService.getUserPersonalInfo(params);

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

    /**
     * 绑定身份
     *
     * @param request
     * @return
     */
    @RequestMapping("/bindIdentity")
    @ResponseBody
    public ZLResult bindIdentity(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String type = MapUtils.getString(params, "type");

        String msg = "";
        if (type.equals(Contants.SCP_YZM_XCX_TEL)) {
            msg = xcxCheckYzmBindTel(params);
        } else if (type.equals(Contants.SCP_YZM_XCX_SHOP)) {
            msg = xcxCheckYzmBindShop(params);
        }

        if (StringUtils.isNotEmpty(msg)) {
            return ZLResult.Error(msg);
        }

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitScpXcxUserService.updateXcxUser(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    /**
     * 小程序校验绑定手机号验证码
     *
     * @param params
     * @return
     */
    public String xcxCheckYzmBindTel(Map<String, Object> params) {
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");

        String _yzm = cacheService.getYzm(account, Contants.SMS_XCX_BIND_TEL_TYPE);

        if (StringUtils.isEmpty(_yzm)) {
            return "请点击发送验证码";
        }

        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            cacheService.setYzm(_yzm, account, Contants.SMS_XCX_BIND_TEL_TYPE);
            return "";
        }
        cacheService.setYzm(_yzm, account, Contants.SMS_XCX_BIND_TEL_TYPE);
        return "验证码错误";
    }

    /**
     * 小程序校验绑定商户验证码
     *
     * @param params
     * @return
     */
    public String xcxCheckYzmBindShop(Map<String, Object> params) {
        String account = MapUtils.getString(params, "account");
        String shopcode = MapUtils.getString(params, "shopcode");
        ZLRpcResult zlRpcResult = apitScpXcxUserService.checkXcxUser(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }
        String yzm = MapUtils.getString(params, "yzm");

        String _yzm = cacheService.getYzm(shopcode + "_" + account, Contants.SMS_XCX_BIND_SHOP_TYPE);

        if (StringUtils.isEmpty(_yzm)) {
            return "请点击发送验证码或输入正确的商户id";
        }

        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            cacheService.setYzm(_yzm, shopcode + "_" + account, Contants.SMS_XCX_BIND_SHOP_TYPE);
            return "";
        }
        cacheService.setYzm(_yzm, shopcode + "_" + account, Contants.SMS_XCX_BIND_SHOP_TYPE);
        return "验证码错误";
    }

    @RequestMapping("/queryXcxUserInfo")
    @ResponseBody
    public ZLResult queryXcxUserInfo(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitScpXcxUserService.queryXcxUserInfo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

}
