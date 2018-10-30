package com.wgb.controller.sg.saleassistant;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.sg.SGXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.mbms.web.ApitMemberAssistantService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.MD5Util;
import com.wgb.util.Validator;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/1.
 */
@Controller
@RequestMapping("/member/assistant")
public class SGXCXSaleAssistanrController extends SGXCXBaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ApiSmsService apiSmsService;

    @Autowired
    private ApitMemberAssistantService apitMemberAssistantService;

    @RequestMapping("/entry/sendYzm")
    @ResponseBody
    public ZLResult sendYzm(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        if (StringUtils.isEmpty(account)) {
            return ZLResult.Error("账号输入为空");
        }
        zlRpcResult = apitMemberAssistantService.searchAssistantInfo(params);
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        if (zlRpcResult.getData() == null) {
            return ZLResult.Error("账号不存在");
        }
        Map<String, Object> data = new HashMap<String, Object>();
        if (Validator.isMobile(account)) {
            //如果短信发送间隔不满1分钟，则提示：手机验证码发送过于频繁，请您稍后再试
            String mobileLockKey = Contants.SMS_SG_XCX + account + "_lock";
            String lock = RedisFactory.getDefaultClient().get(mobileLockKey);
            if (StringUtils.isNotEmpty(lock)) {
                return ZLResult.Error("手机验证码发送过于频繁，请您稍后再试");
            }

            //生成验证码，如果上一次的验证码未过期，则继续使用
            String mobileKey = Contants.SMS_SG_XCX + account;
            String yzm = RedisFactory.getDefaultClient().get(mobileKey);
            if (StringUtils.isEmpty(yzm)) {
                //生成6位数字验证码
                yzm = CommonUtil.createRandom(true, 6);
            }

            //保存短信验证码到缓存，短信5分钟有效，300秒
            RedisFactory.getDefaultClient().set(mobileKey, yzm, 300);
            //保存短信验证锁到缓存，1分钟内不能继续发送验证码，60秒
            RedisFactory.getDefaultClient().set(mobileLockKey, yzm, 60);
            System.out.println(yzm);
            data.put("code", yzm);
            data.put("product", "中仑导购助手");

            String templateCode = "SMS_50605095";
            apiSmsService.sendPlatformSms(account, templateCode, data);
            return ZLResult.Success("");
        }
        return ZLResult.Error("账号输入错误");
    }

    //忘记密码
    @RequestMapping("/entry/updatePassword")
    @ResponseBody
    public ZLResult updatePassword(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        String password = MapUtils.getString(params, "password");
        params.put("password", MD5Util.GetMD5Code(password));
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm) || StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }

        String mobileKey = Contants.SMS_SG_XCX + account;
        String _yzm = RedisFactory.getDefaultClient().get(mobileKey);
        if (StringUtils.isEmpty(_yzm)) {
            return ZLResult.Error("验证码已过期");
        }
        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            ZLRpcResult zlRpcResult = new ZLRpcResult();
            zlRpcResult = apitMemberAssistantService.updatePassword(params);
            //判断返回结果
            if (!zlRpcResult.success()) {
                throw new ServiceException(zlRpcResult.getErrorMsg());
            }
            return ZLResult.Success("");
        }
        return ZLResult.Error("验证码错误");
    }


    //修改密码
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public ZLResult modifyPassword(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        String oldpassword = MapUtils.getString(params, "oldpassword");
        String newpassword1 = MapUtils.getString(params, "newpassword1");
        String newpassword2 = MapUtils.getString(params, "newpassword2");
        params.put("password", MD5Util.GetMD5Code(oldpassword));
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(newpassword1) || StringUtils.isEmpty(newpassword2)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        zlRpcResult = apitMemberAssistantService.modifyPassword(params);
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        //判断返回结果
        if (zlRpcResult.success()) {
            return ZLResult.Success("密码修改成功");
        }

        return ZLResult.Error("两次密码不一致");
    }

    //上传头像
    @RequestMapping("/updateHeadpic")
    @ResponseBody
    public ZLResult updateHeadpic(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, "account");
        if (StringUtils.isEmpty(account)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        apitMemberAssistantService.updateAssistantInfo(params);
        return ZLResult.Success("上传成功");
    }


}
