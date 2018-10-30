package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.admin.ApiMemberService;
import com.wgb.service.dubbo.mbms.web.ApitShopConfigService;
import com.wgb.service.dubbo.salems.admin.ApiRechargeRuleService;
import com.wgb.service.dubbo.sms.web.ApiSmsService;
import com.wgb.util.ArithUtil;
import com.wgb.util.CommonConfig;
import com.wgb.util.Contants;
import com.wgb.util.ZLConstant;
import net.sf.json.JSONArray;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值设置
 *
 * @author fxs
 * @create 2018-05-15 14:19
 **/
@Controller
@RequestMapping("/member/recharge")
public class MTRechargeGrantController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTRechargeGrantController.class);

    @Autowired
    private ApitShopConfigService apitShopConfigService;


    @Autowired
    private ApiMemberService apiMemberService;

    @Autowired
    private ApiSmsService apiSmsService;

    @Autowired
    private ApiRechargeRuleService apiRechargeRuleService;

    /**
     * 收银接口-会员充值接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/memberRecharge")
    @ResponseBody
    public ZLResult memberRecharge(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = null;
        //校验参数
        int errorCode = memberRecharge_validateParams(params);
        if (errorCode != 0) {
            rpcResult.setErrorCode(errorCode);
            return ZLResult.Error(errorCode);
        }
        List<Map<String, Object>> paydetails = getPayDetails(params);
        params.put("paydetails", paydetails);
        //修改充值记录
        try {
            rpcResult = apiMemberService.updateRecharge(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!rpcResult.success()) {
            return ZLResult.Error(rpcResult.getErrorCode());
        }
        Map<String, Object> payresult = rpcResult.getMap();
        //发送充值提醒短信
        try {
            sendMessage(payresult,params);
        }catch (Exception e1){
            logger.error("充值短信提醒发送时候失败!");
        }
        return ZLResult.Success(rpcResult.getData());
    }

    /**
     *  收银端接口-校验充值参数
     * @param params
     * @return
     */
    private int memberRecharge_validateParams(Map<String, Object> params){
        //商户编码
        String shopcode = MapUtils.getString(params, "shopcode");
        //门店编码
        String branchcode = MapUtils.getString(params, "branchcode");
        //会员ID
        String memberid = MapUtils.getString(params, "memberid");
        //充值金额
        String rechargeamount = MapUtils.getString(params, "rechargeamount");

        if (StringUtils.isEmpty(shopcode)) {
            return ServiceException.PARAM_MISSING;
        }
        if (StringUtils.isEmpty(branchcode)) {
            return ServiceException.PARAM_MISSING;
        }
        if (StringUtils.isEmpty(memberid)) {
            return ServiceException.PARAM_MISSING;
        }
        if (StringUtils.isEmpty(rechargeamount)) {
            return ServiceException.PARAM_MISSING;
        }
        return 0;
    }

    /**
     *  收银端接口-发送充值提醒短信
     * @param payresult
     * @param params
     */
    private void sendMessage( Map<String, Object> payresult,Map<String, Object> params){
        if (MapUtils.isNotEmpty(payresult)) {
            Map<String, Object> data = new HashMap<String, Object>();
            double rechargeamount = MapUtils.getDouble(payresult, "rechargeamount",0.00);
            double giftamount = MapUtils.getDouble(payresult, "giftamount",0.00);
            String amount = ArithUtil.add(rechargeamount,giftamount) + "";

            String templateCode = "SMS_112550018";
            String smsnotifiyflag = MapUtils.getString(payresult, "smsnotifiyflag", "");
            String account = MapUtils.getString(payresult, "account", "0");
            String telephone = MapUtils.getString(payresult, "telephone", "");
            data.put("account", account);
            data.put("shopcode", MapUtils.getString(params, "shopcode"));
            data.put("amount", amount);
            data.put("product", CommonConfig.SMS_SYSNAME);
            data.put("type", Contants.SMS_MEMBER_ACCOUNT_PAY_TYPE);
            data.put("giveamount", giftamount+"");
            data.put("loginuserid", MapUtils.getString(params, "loginuserid"));
            data.put("branchcode", MapUtils.getString(params, "branchcode"));
            data.put("branchname", MapUtils.getString(params, ZLConstant.LOGIN_USER_BRANCH_NAME));
            if (smsnotifiyflag.equals("1") && rechargeamount > 0) {
                apiSmsService.sendSms(telephone, templateCode, data);
            }
        }
    }

    /**
     * 收银端接口-获取充值信息
     * @param params
     * @return
     */
    private List<Map<String, Object>> getPayDetails(Map<String, Object> params) {
        String paydetails = MapUtils.getString(params, "paydetails");
        if (StringUtils.isEmpty(paydetails)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        JSONArray paydetailjsonarr = JSONArray.fromObject(paydetails);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (int index = 0; index < paydetailjsonarr.size(); index++) {
            Map<String, Object> item = paydetailjsonarr.getJSONObject(index);
            result.add(item);
        }
        return result;
    }

    /**
     * 收银端接口-查询会员充值优惠
     *
     * @param request
     * @return
     */
    @RequestMapping("/memberRechargeRule")
    @ResponseBody
    public ZLResult queryMemberRechargeRule(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //校验参数
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
//        String memberMode = MapUtils.getString(params, "membermode");
        String levelid = MapUtils.getString(params, "levelid");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
//        if (StringUtils.isEmpty(memberMode)) {
//            throw new ServiceException(ServiceException.PARAM_MISSING);
//        }
        if (StringUtils.isEmpty(levelid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        //查询
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiRechargeRuleService.queryRechargeRule(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //检查远程调用是否成功
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult rpcResult = apitShopConfigService.getConfig(params);
        return parseRpcResultForData(rpcResult);
    }
}
