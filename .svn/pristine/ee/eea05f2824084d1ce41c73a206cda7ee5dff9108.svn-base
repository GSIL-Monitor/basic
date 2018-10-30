package com.wgb.controller.mt.sms;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.SmsConstants;
import com.wgb.util.ZLConstant;
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
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Controller
@RequestMapping("/sms/sms")
public class MTSmsController extends MTBaseController {

    @Autowired
    private ApiSmsService apiSmsService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/sendYzm")
    @ResponseBody
    public ZLResult sendYzm(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String type = MapUtils.getString(params, "type");
        String telephone = MapUtils.getString(params, "telephone");
        String loginuserid = MapUtils.getString(params, "loginuserid");
        String branchcode = MapUtils.getString(params, "branchcode");
        String branchname = MapUtils.getString(params, ZLConstant.LOGIN_USER_BRANCH_NAME);

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(type)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(telephone)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //如果短信发送间隔不满1分钟，则提示：手机验证码发送过于频繁，请您稍后再试
        String mobileLockKey = type + telephone + "_lock";
        String lock = RedisFactory.getDefaultClient().get(mobileLockKey);
        if (StringUtils.isNotEmpty(lock)) {
            throw new ServiceException(ServiceException.CODE_100011);
        }

        String templateCode = null;

        String mobileKey = null;
        //无卡模式
        if (type.equals(Contants.SMS_MEMBER_PAY_AUTH_TYPE)) {
            //生成验证码，如果上一次的验证码未过期，则继续使用
            mobileKey = Contants.SMS_MEMBER_PAY_AUTH_TYPE + telephone;
            templateCode = SmsConstants.SMS_67035040;
            //有卡模式
        } else if (type.equals(Contants.SMS_MEMBER_CARDPAY_AUTH_TYPE)) {
            //生成验证码，如果上一次的验证码未过期，则继续使用
            mobileKey = Contants.SMS_MEMBER_CARDPAY_AUTH_TYPE + telephone;
            templateCode = "SMS_143713884";
        }

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
        data.put("type", type);
        data.put("shopcode", shopcode);
        data.put("loginuserid", loginuserid);
        data.put("branchcode", branchcode);
        data.put("branchname", branchname);

        apiSmsService.sendSms(telephone, templateCode, data);

        return ZLResult.Success();
    }

}
