package com.wgb.controller.mt.pay;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.mq.send.SyncApiAliPaySendMQ;
import com.wgb.rocketmq.annotation.MQWired;
import com.wgb.service.dubbo.pays.web.ApiMQPayService;
import com.wgb.util.Contants;
import com.wgb.util.RscUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/alipaymq")
public class MTAliPayMQController extends MTBaseController {

    @Autowired
    private ApiMQPayService apiMQPayService;

    public static final Logger logger = LoggerFactory.getLogger(MTAliPayMQController.class);

    private SyncApiAliPaySendMQ syncApiAliPaySendMQ;

    @MQWired
    public void setSyncApiAliPaySendMQ(SyncApiAliPaySendMQ syncApiAliPaySendMQ) {
        this.syncApiAliPaySendMQ = syncApiAliPaySendMQ;
    }
    /**
     *  收银端刷卡支付  走 MQ
     * @param request
     * @return
     */
    @RequestMapping("/microPay")
    @ResponseBody
    public ZLResult microPay(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String payamount = MapUtils.getString(params, "payamount");//支付金额
        String auth_code = MapUtils.getString(params, "auth_code");//卡号
        String isbranchpay = MapUtils.getString(params, "isbranchpay");//是否獨立支付

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payamount)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(auth_code)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(isbranchpay)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        params.put("businessorigin", Contants.API_CLIENT_SALE_PAY);


        // 生成预订单
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apiMQPayService.microPayAli(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        //  调用MQ
        syncSmsPayResult2SMS(params);

        return ZLResult.Success(zlRpcResult);
    }

    /**
     * 同步 刷卡支付调用 到支付系统
     */
    private void syncSmsPayResult2SMS(Map<String, Object> params) {

        Map<String, Object> syncParams = getSyncApiPayResult(params);

        try {
            String message = RscUtil.objectToMessage(syncParams);
            // 同步商品信息到微信平台
            syncApiAliPaySendMQ.send(message);
        } catch (Exception e) {
            logger.error("发送收银机支付宝刷卡刷剧到pays系统", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 封装参数同步报文
     */
    private Map<String, Object> getSyncApiPayResult(Map<String, Object> params) {
        String shopcode = MapUtils.getString(params, "shopcode");
        String payamount = MapUtils.getString(params, "payamount");//支付金额
        String auth_code = MapUtils.getString(params, "auth_code");//卡号
        String isbranchpay = MapUtils.getString(params, "isbranchpay");//是否独立支付
        String apiordercode = MapUtils.getString(params, "apiordercode");//收银端生成唯一订单号
        String businesscode = MapUtils.getString(params, "businesscode");//业务单号
        Map<String, Object> sendParams = new LinkedHashMap<String, Object>();
        Map<String, Object> items = new LinkedHashMap<String, Object>();

        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("ordercode", apiordercode);
        data.put("businesscode", businesscode);
        data.put("shopcode", shopcode);
        data.put("payamount", payamount);
        data.put("auth_code", auth_code);
        data.put("isbranchpay", isbranchpay);
        item.add(data);

        items.put("item", item);
        sendParams.put("items", items);
        return sendParams;
    }
}
