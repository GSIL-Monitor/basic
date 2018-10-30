package com.wgb.controller.scp;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.scpms.web.ApitScpRegService;
import com.wgb.service.dubbo.scpms.web.ApitScpUserService;
import com.wgb.service.dubbo.scpms.web.ApitScpXcxUserService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
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
 * Created by 11609 on 2018/8/8.
 */
@Controller
@RequestMapping("/scp/entry/yzm")
public class SCPYzmController extends SCPBaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ApiSmsService apiSmsService;

    @Autowired
    private ApitScpRegService apitScpRegService;

    @Autowired
    private ApitScpUserService apitScpUserService;

    @Autowired
    private ApitScpXcxUserService apitScpXcxUserService;

    @RequestMapping("/sendYzm")
    @ResponseBody
    public ZLResult sendYzm(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        String type = MapUtils.getString(params, "type");

        String account = MapUtils.getString(params, "account");
        if (StringUtils.isEmpty(account)) {
            return ZLResult.Error("账号必须输入");
        }
        if (StringUtils.isEmpty(type)) {
            return ZLResult.Error("参数错误");
        }
        String msg = "";
        if (type.equals(Contants.SCP_YZM_REG)) {
            msg = sendYzmReg(params);
        } else if (type.equals(Contants.SCP_YZM_FORGET)) {
            msg = sendYzmForget(params);
        } else if (type.equals(Contants.SCP_YZM_XCX_TEL)) {
            msg = xcxSendYzmBindTel(params);
        } else if (type.equals(Contants.SCP_YZM_XCX_SHOP)) {
            msg = xcxSendYzmBindShop(params);
        }
        if (StringUtils.isNotEmpty(msg)) {
            return ZLResult.Error(msg);
        }

        return ZLResult.Success();
    }

    @RequestMapping("/checkYzm")
    @ResponseBody
    public ZLResult checkYzm(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        String type = MapUtils.getString(params, "type");
        if (StringUtils.isEmpty(yzm)) {
            return ZLResult.Error("验证码必须填写");
        }

        if (StringUtils.isEmpty(account)) {
            return ZLResult.Error("账号必须输入");
        }
        if (StringUtils.isEmpty(type)) {
            return ZLResult.Error("参数错误");
        }
        String msg = "";
        if (type.equals(Contants.SCP_YZM_REG)) {
            msg = checkYzmReg(params);
        } else if (type.equals(Contants.SCP_YZM_FORGET)) {
            msg = checkYzmForget(params);
        }
        if (StringUtils.isNotEmpty(msg)) {
            return ZLResult.Error(msg);
        }
        return ZLResult.Success();
    }

    /**
     * 发送忘记验证码
     *
     * @param params
     * @return
     */
    public String sendYzmForget(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        zlRpcResult = apitScpUserService.checkScpAccountPwd(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }
        Map<String, Object> data = new HashMap<String, Object>();
        if (Validator.isMobile(account)) {
            //如果短信发送间隔不满1分钟，则提示：手机验证码发送过于频繁，请您稍后再试
            String mobileLockKey = Contants.SMS_RESET_TYPE + account + "_lock";
            String lock = RedisFactory.getDefaultClient().get(mobileLockKey);
            if (StringUtils.isNotEmpty(lock)) {
                return "手机验证码发送过于频繁，请您稍后再试";
            }

            //生成验证码，如果上一次的验证码未过期，则继续使用
            String mobileKey = Contants.SMS_RESET_TYPE + account;
            String yzm = RedisFactory.getDefaultClient().get(mobileKey);
            if (StringUtils.isEmpty(yzm)) {
                //生成6位数字验证码
                yzm = CommonUtil.createRandom(true, 6);
            }

            //保存短信验证码到缓存，短信5分钟有效，300秒
            RedisFactory.getDefaultClient().set(mobileKey, yzm, 300);
            //保存短信验证锁到缓存，1分钟内不能继续发送验证码，60秒
            RedisFactory.getDefaultClient().set(mobileLockKey, yzm, 60);

            data.put("code", yzm);
            data.put("product", "中仑供应链平台");

            String templateCode = "SMS_50605095";
            apiSmsService.sendPlatformSms(account, templateCode, data);
            return "";
        }
        return "账号输入错误";
    }

    /**
     * 校验忘记验证码
     *
     * @param params
     * @return
     */
    public String checkYzmForget(Map<String, Object> params) {
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(yzm)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }

        String _yzm;
        if (Validator.isMobile(account)) {
            _yzm = cacheService.getYzm(account, Contants.SMS_RESET_TYPE);
        } else if (Validator.isEmail(account)) {
            _yzm = cacheService.getYzm(account, Contants.EMAIL_RESET_TYPE);
        } else {
            return "账号格式错误";
        }

        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            cacheService.setYzm(yzm, account, Contants.SMS_RESET_TYPE);
            return "";
        }
        if (StringUtils.isNotEmpty(_yzm)) {
            cacheService.setYzm(_yzm, account, Contants.SMS_RESET_TYPE);
        }
        return "验证码输入错误";
    }

    /**
     * 发送注册验证码
     *
     * @param params
     * @return
     */
    public String sendYzmReg(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        zlRpcResult = apitScpRegService.checkScpAccount(params);
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
            System.out.println(yzm);

            //保存短信验证码到缓存，短信5分钟有效，300秒
            RedisFactory.getDefaultClient().set(mobileKey, yzm, 300);
            //保存短信验证锁到缓存，1分钟内不能继续发送验证码，60秒
            RedisFactory.getDefaultClient().set(mobileLockKey, yzm, 60);

            //发短信
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", yzm);
            data.put("product", "中仑供应链平台");
            apiSmsService.sendPlatformSms(account, "SMS_50605097", data);
            return "";
        }
        return "账号输入错误";
    }

    /**
     * 校验注册验证码
     *
     * @param params
     * @return
     */
    public String checkYzmReg(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        String yzm = MapUtils.getString(params, "yzm");

        zlRpcResult = apitScpRegService.checkScpAccount(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }

        String _yzm = cacheService.getYzm(account, Contants.SMS_REG_TYPE);

        if (StringUtils.isEmpty(_yzm)) {
            return "请点击发送验证码";
        }

        if (StringUtils.isNotEmpty(_yzm) && yzm.equals(_yzm)) {
            cacheService.setYzm(_yzm, account, Contants.SMS_REG_TYPE);
            return "";
        }
        cacheService.setYzm(_yzm, account, Contants.SMS_REG_TYPE);
        return "验证码错误";
    }

    /**
     * 小程序发送绑定手机号验证码
     *
     * @param params
     * @return
     */
    public String xcxSendYzmBindTel(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        zlRpcResult = apitScpXcxUserService.checkXcxUser(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }
        if (Validator.isMobile(account)) {

            //如果短信发送间隔不满1分钟，则提示：手机验证码发送过于频繁，请您稍后再试
            String mobileLockKey = Contants.SMS_XCX_BIND_TEL_TYPE + account + "_lock";
            String lock = RedisFactory.getDefaultClient().get(mobileLockKey);
            if (StringUtils.isNotEmpty(lock)) {
                throw new ServiceException(ServiceException.CODE_100011);
            }

            //生成验证码，如果上一次的验证码未过期，则继续使用
            String mobileKey = Contants.SMS_XCX_BIND_TEL_TYPE + account;
            String yzm = RedisFactory.getDefaultClient().get(mobileKey);
            if (StringUtils.isEmpty(yzm)) {
                //生成6位数字验证码
                yzm = CommonUtil.createRandom(true, 6);
            }
            System.out.println(yzm);

            //保存短信验证码到缓存，短信5分钟有效，300秒
            RedisFactory.getDefaultClient().set(mobileKey, yzm, 300);
            //保存短信验证锁到缓存，1分钟内不能继续发送验证码，60秒
            RedisFactory.getDefaultClient().set(mobileLockKey, yzm, 60);

            //发短信
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", yzm);
            data.put("product", "中仑供应链平台");
            apiSmsService.sendPlatformSms(account, "SMS_50605097", data);
            return "";
        }
        return "账号输入错误";
    }

    /**
     * 小程序发送绑定商户验证码
     *
     * @param params
     * @return
     */
    public String xcxSendYzmBindShop(Map<String, Object> params) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        String account = MapUtils.getString(params, "account");
        String shopcode = MapUtils.getString(params, "shopcode");
        zlRpcResult = apitScpXcxUserService.checkXcxUser(params);
        if (!zlRpcResult.success()) {
            return zlRpcResult.getErrorMsg();
        }
        if (Validator.isMobile(account)) {

            //如果短信发送间隔不满1分钟，则提示：手机验证码发送过于频繁，请您稍后再试
            String mobileLockKey = Contants.SMS_XCX_BIND_SHOP_TYPE + shopcode + "_" + account + "_lock";
            String lock = RedisFactory.getDefaultClient().get(mobileLockKey);
            if (StringUtils.isNotEmpty(lock)) {
                throw new ServiceException(ServiceException.CODE_100011);
            }

            //生成验证码，如果上一次的验证码未过期，则继续使用
            String mobileKey = Contants.SMS_XCX_BIND_SHOP_TYPE + shopcode + "_" + account;
            String yzm = RedisFactory.getDefaultClient().get(mobileKey);
            if (StringUtils.isEmpty(yzm)) {
                //生成6位数字验证码
                yzm = CommonUtil.createRandom(true, 6);
            }
            System.out.println(yzm);

            //保存短信验证码到缓存，短信5分钟有效，300秒
            RedisFactory.getDefaultClient().set(mobileKey, yzm, 300);
            //保存短信验证锁到缓存，1分钟内不能继续发送验证码，60秒
            RedisFactory.getDefaultClient().set(mobileLockKey, yzm, 60);

            //发短信
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("code", yzm);
            data.put("product", "中仑供应链平台");
            apiSmsService.sendPlatformSms(account, "SMS_50605097", data);
            return "";
        }
        return "账号输入错误";
    }


}
