package com.wgb.util;

/**
 * Created by wgb on 2017/12/27 0027.
 */
public class SystemConfig {

    public static String SERVICE_INDEX_URL;
    public static String SERVICE_LOGIN_URL;

    public static String SYSTEM_START_ENV;

    public static String ZL_WEB_PAYS_DOMAIN;
    public static String SCP_SERVICE_LOGIN_URL;    public static int SESSION_TIMEOUT;
    public static String PAY_WX_NOTIFY_URL;
    public static String PAY_WX_REFUND_NOTIFY_URL;
    public static String PAY_WX_SPBILL_CREATE_IP;
    public static String PAY_WX_BODY;
    public static String PAY_WX_UNIFIEDORDER_URL;
    public static String PAY_WX_REFUND_URL;
    public static String PAY_WX_MICROPAY_URL;
    public static String PAY_WX_QUERY_ORDER;
    public static String PAY_WX_APP_ID;
    public static String PAY_WX_MCH_ID;
    public static String PAY_WX_APP_SECRET;
    public static String PAY_WX_REFUND_P12_PATH;

    public static String PAY_ALI_NOTIFY_URL;
    public static String PAY_ALI_RETURN_URL;
    public static String PAY_ALI_GATEWAY_URL;
    public static String PAY_ALI_APP_ID;
    public static String PAY_ALI_APP_PRIVATE_KEY;
    public static String PAY_ALI_ALIPAY_PUBLIC_KEY;
    public static String PAY_ALI_SIGN_TYPE;
    public static String PAY_ALI_SUBJECT;
    public static String DCMS_DOMAIN;
    public static String URMS_DOMAIN;
    public static String RECHARGE_SMS_DOMAIN;
    public static String RECHARGE_CHARGE_DOMAIN;
    public static String RECHARGE_SRVMS_DOMAIN;
    public static String RECHARGE_ACTS_DOMAIN;
    public static String RECHARGE_ADDVALUE_DOMAIN;
    public static String RECHARGE_YXZS_DOMAIN;
    public static String RECHARGE_MINIAPP_DOMAIN;
    public static String RECHARGE_PLUG_DOMAIN;
    public static String RECHARGE_SRVICE_DOMAIN;
    public static String RECHARGE_RETURN_URL_DOMAIN;
    public static String RECHARGE_SERVRETURN_URL_DOMAIN;

    public static String SMS_MEMBER_PAY_AUTH_TEMPLATE;
    public static String SMS_MEMBER_ACCOUNT_PAY_TEMPLATE;
    public static String SMS_MEMBER_ACCOUNT_RECHARGE_TEMPLATE;

    public static String WX_THIRDPLATFORM_CALLBCK;


    private static PropConfig propConfig = PropConfig.loadConfig("setting-web.properties");

    public SystemConfig() {
    }

    public static String getConfigString(String key) {
        return propConfig.getConfigString(key);
    }

    public static int getConfigInt(String key) {
        return propConfig.getConfigInt(key);
    }

    static {
        if(propConfig != null) {
            SERVICE_INDEX_URL = propConfig.getConfigString("service.index.url");
            SERVICE_LOGIN_URL = propConfig.getConfigString("service.login.url");
            SCP_SERVICE_LOGIN_URL = propConfig.getConfigString("scp.service.login.url");
            SYSTEM_START_ENV = propConfig.getConfigString("system.start.env");
            PAY_WX_NOTIFY_URL = propConfig.getConfigString("pay.wx.notifyUrl");
            PAY_WX_REFUND_NOTIFY_URL = propConfig.getConfigString("pay.wx.refundNotifyUrl");
            PAY_WX_SPBILL_CREATE_IP = propConfig.getConfigString("pay.wx.spbillCreateIp");
            PAY_WX_BODY = propConfig.getConfigString("pay.wx.body");
            PAY_WX_UNIFIEDORDER_URL = propConfig.getConfigString("pay.wx.unifiedorderUrl");
            PAY_WX_REFUND_URL = propConfig.getConfigString("pay.wx.refundUrl");
            PAY_WX_MICROPAY_URL = propConfig.getConfigString("pay.wx.micropayUrl");
            PAY_WX_QUERY_ORDER = propConfig.getConfigString("pay.wx.queryorder");
            PAY_WX_APP_ID = propConfig.getConfigString("pay.wx.appId");
            PAY_WX_MCH_ID = propConfig.getConfigString("pay.wx.mchId");
            PAY_WX_APP_SECRET = propConfig.getConfigString("pay.wx.appSecret");
            PAY_WX_REFUND_P12_PATH = propConfig.getConfigString("pay.wx.refund.p12path");
            PAY_ALI_NOTIFY_URL = propConfig.getConfigString("pay.ali.notifyUrl");
            PAY_ALI_RETURN_URL = propConfig.getConfigString("pay.ali.returnUrl");
            PAY_ALI_GATEWAY_URL = propConfig.getConfigString("pay.ali.gateWayUrl");
            PAY_ALI_APP_ID = propConfig.getConfigString("pay.ali.appId");
            PAY_ALI_APP_PRIVATE_KEY = propConfig.getConfigString("pay.ali.appPrivateKey");
            PAY_ALI_ALIPAY_PUBLIC_KEY = propConfig.getConfigString("pay.ali.alipayPublicKey");
            PAY_ALI_SIGN_TYPE = propConfig.getConfigString("pay.ali.signType");
            PAY_ALI_SUBJECT = propConfig.getConfigString("pay.ali.subject");
            ZL_WEB_PAYS_DOMAIN = propConfig.getConfigString("pay.wx.domain");

            RECHARGE_RETURN_URL_DOMAIN = propConfig.getConfigString("zl.web.return.domain");
            RECHARGE_SERVRETURN_URL_DOMAIN = propConfig.getConfigString("zl.web.servreturn.domain");

            WX_THIRDPLATFORM_CALLBCK = propConfig.getConfigString("wx.thirdplatform.callbck");
        }
    }
}
