package com.wgb.controller.mt;

import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.sms.web.ApiEmailService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.service.dubbo.urms.web.ApitPortalUserService;
import com.wgb.service.dubbo.urms.web.ApitRegService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
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
 * Created by qiuxh on 2018/5/19.
 */
@Controller
@RequestMapping("/entry/reg")
public class MTRegController extends MTBaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ApiEmailService apiEmailService;

    @Autowired
    private ApiSmsService apiSmsService;

    @Autowired
    private ApitPortalUserService apitPortalUserService;

    @Autowired
    private ApitRegService apitRegService;

    @RequestMapping("/reg")
    @ResponseBody
    public String reg(HttpServletRequest request){
        Map<String, Object> params = getParams();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        String password = MapUtils.getString(params, "password");
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm)) {
            return "参数错误";
        }

        String _yzm;
        if (Validator.isMobile(account)) {
            _yzm = cacheService.getYzm(account, Contants.SMS_REG_TYPE);
        } else if (Validator.isEmail(account)) {
            _yzm = cacheService.getYzm(account, Contants.EMAIL_REG_TYPE);
        } else {
            return "账号格式错误";
        }
        if (!Validator.isPassword(password)) {
            return "密码必须为6-18位数字、字母、符号";
        }
        if ((StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) || StringUtils.equals(yzm,Contants.YZMCODE)) {
            apitRegService.insertReg(params);
            return "";
        }
        if(StringUtils.isNotEmpty(_yzm)){
            cacheService.setYzm(_yzm, account, Contants.SMS_REG_TYPE);
        }
        return "验证码已过期";
    }

    @RequestMapping("/sendYzm")
    @ResponseBody
    public String sendYzm(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        if (StringUtils.isEmpty(account)) {
            return "账号必须输入";
        }
        zlRpcResult = apitPortalUserService.checkAccount(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }
        if (Validator.isMobile(account)) {

            //如果短信发送间隔不满1分钟，则提示：手机验证码发送过于频繁，请您稍后再试
            String mobileLockKey = Contants.SMS_REG_TYPE + account + "_lock";
            String lock = RedisFactory.getDefaultClient().get(mobileLockKey);
            if (StringUtils.isNotEmpty(lock)) {
                throw new ServiceException(ServiceException.CODE_100011);
            }

            //生成验证码，如果上一次的验证码未过期，则继续使用
            String mobileKey = Contants.SMS_REG_TYPE + account;
            String yzm = RedisFactory.getDefaultClient().get(mobileKey);
            if (StringUtils.isEmpty(yzm)) {
                //生成6位数字验证码
                yzm = CommonUtil.createRandom(true, 6);
            }

            //保存短信验证码到缓存，短信5分钟有效，300秒
            RedisFactory.getDefaultClient().set(mobileKey, yzm, 300);
            //保存短信验证锁到缓存，1分钟内不能继续发送验证码，60秒
            RedisFactory.getDefaultClient().set(mobileLockKey, yzm, 60);

            //发短信
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", yzm);
            data.put("product", "中仑云平台");
            apiSmsService.sendPlatformSms(account, "SMS_50605097", data);
            return "";
        }
        if (Validator.isEmail(account)) {
            //生成6位数字验证码
            String yzm = CommonUtil.createRandom(true, 6);
            System.out.print("YZM=" + yzm);
            apiEmailService.sendEmail(yzm, account, Contants.EMAIL_REG_TYPE);
            cacheService.setYzm(yzm, account, Contants.EMAIL_REG_TYPE);
            return "";
        }
        return "账号输入错误";
    }

    @RequestMapping("/checkYzm")
    @ResponseBody
    public String checkYzm(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        if (StringUtils.isEmpty(yzm)) {
            return"验证码必须填写";
        }

        if (StringUtils.isEmpty(account)) {
            return "账号必须输入";
        }

        zlRpcResult = apitPortalUserService.checkAccount(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }

        String _yzm = cacheService.getYzm(account, Contants.SMS_REG_TYPE);

        if (StringUtils.isEmpty(_yzm)) {
            return "请点击发送验证码";
        }

        if (StringUtils.equals(yzm,Contants.YZMCODE)) {
            cacheService.setYzm(_yzm, account, Contants.SMS_REG_TYPE);
            return "";
        }

        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            cacheService.setYzm(_yzm, account, Contants.SMS_REG_TYPE);
            return "";
        }
        if(StringUtils.isNotEmpty(_yzm)){
            cacheService.setYzm(_yzm, account, Contants.SMS_REG_TYPE);
        }
        return "验证码错误";
    }
}
