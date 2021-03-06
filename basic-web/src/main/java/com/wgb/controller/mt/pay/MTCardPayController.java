package com.wgb.controller.mt.pay;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.admin.ApiMemberRechargeService;
import com.wgb.service.dubbo.mbms.admin.ApiMemberService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.service.dubbo.wxms.admin.ApiBranchService;
import com.wgb.util.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
@Controller
@RequestMapping("/cardpay")
public class MTCardPayController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTCardPayController.class);

    @Autowired
    private ApiMemberService apiMemberService;

    @Autowired
    private ApiMemberRechargeService apiMemberRechargeService;

    @Autowired
    private ApiSmsService apiSmsService;

    @Autowired
    private ApiBranchService apiBranchService;


    @RequestMapping("/memberpay")
    @ResponseBody
    public ZLResult memberpay(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> params = getParams();
        //1 校验参数
        String shopcode = MapUtils.getString(params, "shopcode");
        String payamount = MapUtils.getString(params, "payamount");//支付金额
        String memberid = MapUtils.getString(params, "memberid");
        String branchcode = MapUtils.getString(params, "branchcode");
        String loginuserid = MapUtils.getString(params, "loginuserid");
        String branchname = MapUtils.getString(params, ZLConstant.LOGIN_USER_BRANCH_NAME);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payamount)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            //2 修改会员支付记录
            zlRpcResult = apiMemberService.updateMemberPay(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorCode());
        }
        //4 发送提示短信
        Map<String, Object> payresult = zlRpcResult.getMap();

        //检查微信设置的sms的状态
        int flag = checkSmsStatus(params);

        if (MapUtils.isNotEmpty(payresult) && flag==1) {
            Map<String, Object> data = new HashMap<String, Object>();
            double amount = MapUtils.getDoubleValue(payresult, "payamount",0);
            double amountdouble = MapUtils.getDouble(payresult, "payamount");
            String templateCode = Contants.SMS_147410130;
            String smsnotifiyflag = MapUtils.getString(payresult, "smsnotifiyflag", "");
            //String account = MapUtils.getString(payresult, "account", "");
            String telephone = MapUtils.getString(payresult, "telephone", "");
            //data.put("account", account);
            data.put("shopcode", shopcode);
            data.put("loginuserid", loginuserid);
            //data.put("payamount", amount);
            data.put("branchcode", branchcode);
            data.put("branchname", branchname);
//            String product = "会员";
//            java.net.URLEncoder.encode(product, "GBK");
//            data.put("product", product);

            double changecredit =MapUtils.getDoubleValue(params, "changecredit", 0.00);

            double credit = MapUtils.getDoubleValue(payresult, "credit", 0.00);

            //当前会员账户余额
            String account = MapUtils.getString(payresult, "account", "0");
            data.put("amount", account);
            //当前积分
            data.put("credit", ArithUtil.add(credit,changecredit));
            //本次账户变动余额
            data.put("changeamount", 0-amount);
            //本次账户变动积分
            data.put("changecredit", changecredit);
            data.put("type", Contants.SMS_MEMBER_AMOUNT_UPDATE_TYPE);
            try {
                if (amountdouble > 0) {
                    apiSmsService.sendSms(telephone, templateCode, data);
                }
            } catch (Exception e) {
                logger.error("发送短信异常！", e);
            }

        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    private int checkSmsStatus(Map<String, Object> params) {

        ZLRpcResult zlRpcResult = new ZLRpcResult();

        Map<String, Object> result = new HashMap<>();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String smsparam = Contants.SMS_STOREDCHANGE;

        result.put("shopcode",shopcode );
        result.put("branchcode",branchcode );
        result.put("smsparam", smsparam);

        zlRpcResult = apiBranchService.querySMSStatus(result);
        int flag = (int) zlRpcResult.getData();
        return flag;
    }

    @RequestMapping("/memberreturnpay")
    @ResponseBody
    public ZLResult memberreturnpay(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> params = getParams();
        //1 校验参数
        String shopcode = MapUtils.getString(params, "shopcode");
        String payamount = MapUtils.getString(params, "payamount");//支付金额
        String memberid = MapUtils.getString(params, "memberid");
        String branchcode = MapUtils.getString(params, "branchcode");
        String loginuserid = MapUtils.getString(params, "loginuserid");
        String branchname = MapUtils.getString(params, ZLConstant.LOGIN_USER_BRANCH_NAME);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payamount)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            //2 修改会员支付记录
            zlRpcResult = apiMemberService.updateMemberReturnPay(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorCode());
        }
        //4 发送提示短信
        Map<String, Object> payresult = zlRpcResult.getMap();
        if (MapUtils.isNotEmpty(payresult)) {
            Map<String, Object> data = new HashMap<String, Object>();
            String amount = MapUtils.getString(payresult, "payamount");
            double amountdouble = MapUtils.getDouble(payresult, "payamount");
            String templateCode = SmsConstants.SMS_67100539;
            String smsnotifiyflag = MapUtils.getString(payresult, "smsnotifiyflag", "");
            String account = MapUtils.getString(payresult, "account", "");
            String telephone = MapUtils.getString(payresult, "telephone", "");
            data.put("account", account);
            data.put("shopcode", shopcode);
            data.put("loginuserid", loginuserid);
            data.put("payamount", amount);
            data.put("branchcode", branchcode);
            data.put("branchname", branchname);
            String product = "会员";
            java.net.URLEncoder.encode(product, "GBK");
            data.put("product", product);
            data.put("type", Contants.SMS_MEMBER_AMOUNT_UPDATE_TYPE);
            try {
                if (smsnotifiyflag.equals("1") && amountdouble > 0) {
                    apiSmsService.sendSms(telephone, templateCode, data);
                }
            } catch (Exception e) {
                logger.error("发送短信异常！", e);
            }

        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/getMemberPayStatus")
    @ResponseBody
    public ZLResult getMemberPayStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String ticketcode = MapUtils.getString(params, "ticketcode");
        String type = MapUtils.getString(params, "ticketcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(ticketcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(type)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;
        try {
            // 查询会员支付状态
            zlRpcResult = apiMemberRechargeService.getMemberPayStatus(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorCode());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }
}
