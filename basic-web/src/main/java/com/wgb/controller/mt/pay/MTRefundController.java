package com.wgb.controller.mt.pay;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.mbms.admin.ApiMemberService;
import com.wgb.service.dubbo.pays.web.ApiAliPayService;
import com.wgb.service.dubbo.pays.web.ApiWxPayService;
import com.wgb.service.dubbo.salems.admin.ApiPrepaidcardService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.service.dubbo.wxms.admin.ApiBranchService;
import com.wgb.util.ArithUtil;
import com.wgb.util.Contants;
import com.wgb.util.ZLConstant;
import net.sf.json.JSONObject;
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
 * Created by wgy on 2018/3/22.
 */
@Controller
@RequestMapping("/refund")
public class MTRefundController extends MTBaseController {
    public static final Logger logger = LoggerFactory.getLogger(MTRefundController.class);
    @Autowired
    ApiAliPayService apiAliPayService;

    @Autowired
    ApiWxPayService apiWxPayService;

    @Autowired
    ApiMemberService apiMemberService;

    @Autowired
    ApiPrepaidcardService apiPrepaidcardService;

    @Autowired
    ApiSmsService apiSmsService;

    @Autowired
    ApiBranchService apiBranchService;

    @RequestMapping("/money")
    @ResponseBody
    public ZLResult money(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String refundtype = MapUtils.getString(params, "refundtype");

        ZLRpcResult zlRpcResult = new ZLRpcResult();


        //选择退款种类
        if (StringUtils.isEmpty(refundtype)) {
            zlRpcResult.setErrorCode(80000);
            return ZLResult.Error(zlRpcResult.getErrorCode());
        }

        //支付宝退款--2.0 版本
        if (StringUtils.equals(Contants.AIPAYREFUNDTYPENEW, refundtype)) {
            zlRpcResult = apiAliPayService.aliRefundNew(params);
            if (!zlRpcResult.success()) {
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
            //判断membercode是否存在，若存在则是会员
            String membercode = MapUtils.getString(params, "membercode");
            if (StringUtils.isNotEmpty(membercode)) {
                params.put("telephone", membercode);
                //发送短信
                try {
                    sendMessage(params);
                } catch (Exception e1) {
                    logger.error("充值短信提醒发送失败!");
                }
            }
        }
        //微信退款----2.0 版本
        if (StringUtils.equals(Contants.WXPAYREFUNDTYPENEW, refundtype)) {
            zlRpcResult = apiWxPayService.wxRefundNew(params);

            if (!zlRpcResult.success()) {
                int errorcode = zlRpcResult.getErrorCode();
                //30029 表示调用退款接口失败，返回微信官方错误描述信息
                if (errorcode == 30029) {
                    Map<String, Object> result = new HashMap<String, Object>();
                    ZLResult.Error(zlRpcResult.getErrorCode());
                }
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
            //判断membercode是否存在，若存在则是会员
            String membercode = MapUtils.getString(params, "membercode");
            if (StringUtils.isNotEmpty(membercode)) {
                params.put("telephone", membercode);
                //发送短信
                try {
                    sendMessage(params);
                } catch (Exception e1) {
                    logger.error("充值短信提醒发送失败!");
                }
            }
        }
        //支付宝退款
        if (StringUtils.equals(Contants.AIPAYREFUNDTYPE, refundtype)) {
            zlRpcResult = apiAliPayService.aliRefund(params);
            if (!zlRpcResult.success()) {
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
            //判断membercode是否存在，若存在则是会员
            String membercode = MapUtils.getString(params, "membercode");
            if (StringUtils.isNotEmpty(membercode)) {
                params.put("telephone", membercode);
                //发送短信
                try {
                    sendMessage(params);
                } catch (Exception e1) {
                    logger.error("充值短信提醒发送失败!");
                }
            }
        }
        //微信退款
        if (StringUtils.equals(Contants.WXPAYREFUNDTYPE, refundtype)) {
            zlRpcResult = apiWxPayService.wxRefund(params);

            if (!zlRpcResult.success()) {
                int errorcode = zlRpcResult.getErrorCode();
                //30029 表示调用退款接口失败，返回微信官方错误描述信息
                if (errorcode == 30029) {
                    Map<String, Object> result = new HashMap<String, Object>();
                    ZLResult.Error(zlRpcResult.getErrorCode());
                }
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
            //判断membercode是否存在，若存在则是会员
            String membercode = MapUtils.getString(params, "membercode");
            if (StringUtils.isNotEmpty(membercode)) {
                params.put("telephone", membercode);
                //发送短信
                try {
                    sendMessage(params);
                } catch (Exception e1) {
                    logger.error("充值短信提醒发送失败!");
                }
            }
        }
        //次卡退款
        if (StringUtils.equals(Contants.COUNTREFUNDTYPE, refundtype)) {
            String whichversion = MapUtils.getString(params, "whichversion", "0");
            if ("1018".equals(whichversion)) {
                zlRpcResult = apiMemberService.updateNewCountcardReturnpay(params);
            } else {
                zlRpcResult = apiMemberService.updateCountcardReturnpay(params);
            }
            if (!zlRpcResult.success()) {
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
        }

        //预付卡退款
        if (StringUtils.equals(Contants.PRECARDREFUNDTYPE, refundtype)) {
            zlRpcResult = apiPrepaidcardService.prepaidcardRefund(params);
            if (!zlRpcResult.success()) {
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
        }
        //会员退款
        if (StringUtils.equals(Contants.MEMBERREFUNDTYPE, refundtype)) {
            zlRpcResult = apiMemberService.updateMemberReturnPay(params);
            if (!zlRpcResult.success()) {
                return ZLResult.Error(zlRpcResult.getErrorCode());
            }
            Map<String, Object> payresult = zlRpcResult.getMap();
            //发送充值提醒短信
            params.put("telephone", MapUtils.getString(payresult, "telephone"));
            try {
                sendMessage(params);
            } catch (Exception e1) {
                logger.error("充值短信提醒发送失败!");
            }
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 订单退款短信
     *
     * @param params
     */
    private void sendMessage(Map<String, Object> params) {

        //检查微信设置的sms的状态
        int flag = checkSmsStatus(params);

        if (flag == 1) {
            Map<String, Object> data = new HashMap<String, Object>();
            String templateCode = Contants.SMS_147410115;
            String telephone = MapUtils.getString(params, "telephone", "");

            data.put("shopcode", MapUtils.getString(params, "shopcode"));
            data.put("type", Contants.SMS_MEMBER_REFUND_TYPE);
            data.put("loginuserid", MapUtils.getString(params, "loginuserid"));
            data.put("branchcode", MapUtils.getString(params, "branchcode"));
            data.put("branchname", MapUtils.getString(params, ZLConstant.LOGIN_USER_BRANCH_NAME));

            JSONObject paramsJson = JSONObject.fromObject(data);

            this.logger.info("短信信息paramsJson" + paramsJson);
            //发送短信
            apiSmsService.sendSms(telephone, templateCode, data);
        }

    }

    private int checkSmsStatus(Map<String, Object> params) {

        ZLRpcResult zlRpcResult = new ZLRpcResult();

        Map<String, Object> result = new HashMap<>();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String smsparam = Contants.SMS_ORDERREFUNDSUCESS;

        result.put("shopcode",shopcode );
        result.put("branchcode",branchcode );
        result.put("smsparam", smsparam);

        zlRpcResult = apiBranchService.querySMSStatus(result);
        int flag = (int) zlRpcResult.getData();
        return flag;
    }


}