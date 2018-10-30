package com.wgb.util;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.pays.admin.ApiPayService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
@Service
public class WeixinCommon {

    protected final static Logger logger = LoggerFactory.getLogger(WeixinCommon.class);

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 获取网页授权凭证
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */


    public static Map<String, Object> getOauth2AccessToken(String appId, String appSecret, String code) {
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭

        String httpResult = HttpClientUtil.httpGetRequest(requestUrl);

        if (StringUtils.isNotEmpty(httpResult)) {
            try {
                JSONObject jsonObject = JSONObject.fromObject(httpResult);

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("access_token", jsonObject.getString("access_token"));
                resultMap.put("expires_in", jsonObject.getString("expires_in"));
                resultMap.put("refresh_token", jsonObject.getString("refresh_token"));
                resultMap.put("openid", jsonObject.getString("openid"));
                resultMap.put("scope", jsonObject.getString("scope"));

                return resultMap;
            } catch (Exception e) {
                System.out.println("调用获取网页授权接口异常了:" + e.getMessage());
            }
        }

        return null;
    }


    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static Map<String, Object> getUserInfo(String accessToken, String openId) {
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        String httpResult = HttpClientUtil.httpGetRequest(requestUrl);

        System.out.println(httpResult);

        if (StringUtils.isNotEmpty(httpResult)) {
            try {

                String filterString = EmojiFilterUtils.filterEmoji(httpResult);
                JSONObject jsonObject = JSONObject.fromObject(filterString);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("openid", jsonObject.getString("openid"));
                resultMap.put("nickname", jsonObject.getString("nickname"));
                resultMap.put("sex", jsonObject.getString("sex"));
                resultMap.put("country", jsonObject.getString("country"));
                resultMap.put("province", jsonObject.getString("province"));
                resultMap.put("city", jsonObject.getString("city"));
                resultMap.put("headimgurl", jsonObject.getString("headimgurl"));
                //通过第三方开放平台多获取了一个unionid
               // resultMap.put("unionid", jsonObject.getString("unionid"));
                //resultMap.put("zb", jsonObject.getString("zb"));
                return resultMap;
            } catch (Exception e) {
                System.out.println("调用获取微信用户信息接口异常了:" + e.getMessage());
            }
        }
        return null;
    }

    public static String getPrepayId(SortedMap<String, Object> parameterMap) {

        String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        String requestXML = PayCommonUtil.getRequestXml(parameterMap);
        String payResult = PayCommonUtil.httpsRequest(
                requestUrl, "POST",
                requestXML);

        logger.info("统一下单接口调用返回结果：" + payResult);

        Map<String, String> payResultMap = null;
        try {
            payResultMap = PayCommonUtil.doXMLParse(payResult);
        } catch (Exception e) {
            throw new ServiceException("调用微信接口异常");
        }

        logger.info("统一下单接口调用返回结果map：" + payResultMap);

        //用于说明请求是否成功
        String returnCode = MapUtils.getString(payResultMap, "return_code", "");
        if (!returnCode.equals("SUCCESS")) {
            throw new ServiceException("调用微信接口失败");
        }

        String prepayId = MapUtils.getString(payResultMap, "prepay_id", "");
        if (StringUtils.isEmpty(prepayId)) {
            throw new ServiceException("调用微信接口失败");
        }

        return prepayId;
    }

    /**
     * 判断微信商户是否营业
     *
     * @param params 商户信息
     * @return
     */
    public static Boolean checkShopIsOpen(Map<String, Object> params) {
        String businessstate = MapUtils.getString(params, "businessstate", "");
        String openstate = MapUtils.getString(params, "openstate", "");
        String strstarttime = MapUtils.getString(params, "starttime", "");
        String strendtime = MapUtils.getString(params, "endtime", "");
        SimpleDateFormat dataform = new SimpleDateFormat("HH:mm:ss");
        String strnow = dataform.format(new Date());
        //营业状态为休息的时候直接返回
        if (StringUtils.equals(businessstate, "0")) {
            return false;
        }

        if (StringUtils.equals(openstate, "0")) {
            return true;
        } else {
            try {
                Date now = dataform.parse(strnow);
                Date startime = dataform.parse(strstarttime);
                Date endtime = dataform.parse(strendtime);
                if (now.getTime() >= startime.getTime() && now.getTime() <= endtime.getTime()) {
                    return true;
                } else {
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new ServiceException("时间转换错误");
            }
        }
    }

     /*
    * 获取调用微信接口的access_token
    *
    * */

    public String getGZHAcess_token(Map<String, Object> params) {
        String shopcode = MapUtils.getString(params, "shopcode");
        String wxPubAppId = MapUtils.getString(params, "wxpubappid");
        String wxPubAppSecret = MapUtils.getString(params, "wxpubappsecret");
        String acess_token = null;
        acess_token = RedisFactory.getDefaultClient().get("shopcode_" + shopcode + "acess_token");
        if (StringUtils.isEmpty(acess_token)) {
            // 拼接请求地址
            // https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfa05743eef319afd&secret=APP724c7f5d7691a6cc7372870b08127a39
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

            requestUrl = requestUrl.replace("APPID", wxPubAppId);
            requestUrl = requestUrl.replace("APPSECRET", wxPubAppSecret);
            String httpResult = HttpClientUtil.httpGetRequest(requestUrl);
            if (StringUtils.isNotEmpty(httpResult)) {
                JSONObject jsonObject = JSONObject.fromObject(httpResult);
                //获取到的acecss_token和expires_in保存到redis当中
                acess_token = jsonObject.getString("access_token");
                RedisFactory.getDefaultClient().set("shopcode_" + shopcode + "acess_token", acess_token, 7200);
            }
        }
        return acess_token;
    }

/*    *//*
    * 获取调用微信接口的access_token
    *
    * *//*

    public String getAcess_token(Map<String, Object> params) {
        String code = MapUtils.getString(params, "code");
        String shopcode = MapUtils.getString(params, "shopcode");

        ZLRpcResult rpcResult = apiPayService.getPayConfig(params);
        Map<String, Object> payConfig = rpcResult.getMap();
        String wxPubAppId = MapUtils.getString(payConfig, "wxpubappid");
        String wxPubAppSecret = MapUtils.getString(payConfig, "wxpubappsecret");
        String acess_token = null;
        acess_token = RedisFactory.getDefaultClient().get("shopcode_" + shopcode + "acess_token");
        if (StringUtils.isEmpty(acess_token)) {
            // 拼接请求地址
            // https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfa05743eef319afd&secret=APP724c7f5d7691a6cc7372870b08127a39
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

            requestUrl = requestUrl.replace("APPID", wxPubAppId);
            requestUrl = requestUrl.replace("APPSECRET", wxPubAppSecret);
            String httpResult = HttpClientUtil.httpGetRequest(requestUrl);
            if (StringUtils.isNotEmpty(httpResult)) {
                JSONObject jsonObject = JSONObject.fromObject(httpResult);
                //获取到的acecss_token和expires_in保存到redis当中
                acess_token = jsonObject.getString("access_token");
                RedisFactory.getDefaultClient().set("shopcode_" + shopcode + "acess_token", acess_token, 7200);
            }
        }
        return acess_token;
    }*/

    /*
  *  @param string url 调用微信发送模板消息的接口的URL
  *  @param string body post提交的数据
  * */
    public String sendHttpPost(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        // httpPost.setEntity(new StringEntity(body));
        httpPost.setEntity(new StringEntity(body, "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseContent);
        response.close();
        httpClient.close();
        return responseContent;
    }

    /**
     * 获取网页授权凭证
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */

    public static Map<String, Object> getOauth2AccessTokenAgent(String appId, String appSecret, String code) {
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=APPID&code=CODE&grant_type=authorization_code&component_appid=COMPONENT_APPID&component_access_token=COMPONENT_ACCESS_TOKEN";
        requestUrl = requestUrl.replace("COMPONENT_APPID", "wx039158dd1b0e5f81");
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("CODE", code);
        requestUrl = requestUrl.replace("COMPONENT_ACCESS_TOKEN", "10_fUv5FgPiFt5ayVb2VUpS3pTX0M3Nn2NubDEraFlxVrX7qPLquk5Y7Ho7MHcwUearUWOUpZFNMzDv8CqgxJsezNuxVhDywGU7Dh-5H5LSYRR9JwnHGcKQo8q_poVj5DNFu73aw4VD2PmdJ51PLPBhAFAQUG");


        // 获取网页授权凭

        String httpResult = HttpClientUtil.httpGetRequest(requestUrl);

        if (StringUtils.isNotEmpty(httpResult)) {
            try {
                JSONObject jsonObject = JSONObject.fromObject(httpResult);

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("access_token", jsonObject.getString("access_token"));
                resultMap.put("expires_in", jsonObject.getString("expires_in"));
                resultMap.put("refresh_token", jsonObject.getString("refresh_token"));
                resultMap.put("openid", jsonObject.getString("openid"));
                resultMap.put("scope", jsonObject.getString("scope"));

                return resultMap;
            } catch (Exception e) {
                System.out.println("调用获取网页授权接口异常了:" + e.getMessage());
            }
        }

        return null;
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取微信票据
     */
    public static String getJsApiTicket(String acess_token) throws Exception {
        String jsapi_ticket = null;
        String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" + "access_token=" + acess_token + "&type=jsapi";
        try {
            String apiticketResult = HttpClientUtil.httpGetRequest(urlStr);
            if (StringUtils.isNotEmpty(apiticketResult)) {
                JSONObject jasonObject = JSONObject.fromObject(apiticketResult);
                jsapi_ticket = jasonObject.getString("ticket");//调用微信JS接口的临时票据
            }
        } catch (Exception e) {
            logger.error("获取微信票据失败 参数：" + acess_token, e);
        }
        return jsapi_ticket;
    }
}
