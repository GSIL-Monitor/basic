package com.wgb.controller.mt.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.pays.web.ApiAliPayService;
import com.wgb.service.dubbo.pays.web.ApitAliPayService;
import com.wgb.util.CommonUtil;
import com.wgb.util.Contants;
import com.wgb.util.SystemConfig;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class MTAliPayController extends MTBaseController {

    @Autowired
    private ApitAliPayService apitAliPayService;

    public static final Logger logger = LoggerFactory.getLogger(MTAliPayController.class);

    @Autowired
    private ApiAliPayService apiAliPayService;

    /**
     *  收银端刷卡支付
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
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiAliPayService.microPay2Shop(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     *  收银端  生成 用户扫二维码
     * @param request
     * @return
     */
    @RequestMapping("/prePay")
    @ResponseBody
    public ZLResult prePay(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String payamount = MapUtils.getString(params, "payamount");//支付金额
        String isbranchpay = MapUtils.getString(params, "isbranchpay");//是否獨立支付

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payamount)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(isbranchpay)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        params.put("businessorigin", Contants.API_CLIENT_SALE_PAY);

        // 生成预订单
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiAliPayService.scanPay2Shop(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * c查询 支付订单状态
     * @param request
     * @return
     */
    @RequestMapping("/queryPayResultStatus")
    @ResponseBody
    public ZLResult queryPayResultStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String ordercode = MapUtils.getString(params, "ordercode");//销售单号
        String paytypecode = MapUtils.getString(params, "paytypecode");//销售单号
        String isbranchpay = MapUtils.getString(params, "isbranchpay");//是否獨立支付

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(ordercode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(paytypecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(isbranchpay)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        // 生成预订单
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiAliPayService.queryPayResultStatus(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端根据唯一订单号查询订单支付状态
     * @param request
     * @return
     */
    @RequestMapping("/queryAliPayResultOfApi")
    @ResponseBody
    public ZLResult queryAliPayResultOfApi(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String apiordercode = MapUtils.getString(params, "apiordercode");//收银唯一单号
        String paytypecode = MapUtils.getString(params, "paytypecode");// 支付类型
        String isbranchpay = MapUtils.getString(params, "isbranchpay");//是否独立支付

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(apiordercode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(paytypecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(isbranchpay)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        // 生成预订单
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiAliPayService.queryAliPayResultOfApi(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 查询订单状态----  调用支付宝接口查询
     * @param request
     * @return
     */
    @RequestMapping("/queryPayResult")
    @ResponseBody
    public ZLResult queryPayResult(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String ordercode = MapUtils.getString(params, "ordercode");//销售单号
        String paytypecode = MapUtils.getString(params, "paytypecode");//销售单号
        String isbranchpay = MapUtils.getString(params, "isbranchpay");//是否獨立支付

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(ordercode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(paytypecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(isbranchpay)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        // 生成预订单
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiAliPayService.queryPayResult(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 商户后台 返回二维码页面  支付宝支付
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/entry/scanPay/scanPay2Self")
    public ZLResult microPay2Shop(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();


        //商户编码
        String shopcode = MapUtils.getString(params, "shopcode");
        //业务单号
        String businesscode = MapUtils.getString(params, "businesscode");
        //支付预订单号
        String ordercode = MapUtils.getString(params, "ordercode");
        //支付方式
        String businessorigin = MapUtils.getString(params, "businessorigin");



        if (StringUtils.isEmpty(businesscode)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }

        if (StringUtils.isEmpty(businessorigin)) {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        try {
            pagePay2self(shopcode, "SCAN_PAY_2_SELF", businesscode, businessorigin, params, response);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        return ZLResult.Success();
    }

    /**
     * 服务商 运维后台支付宝支付   跳转二维码页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/entry/scanPay2SelfForServer")
    public ZLResult scanPay2SelfForServer(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcodecharge");
        params.put("shopcode", shopcode);

        //业务单号
        String businesscode = MapUtils.getString(params, "businesscode");
        //支付预订单号
        String ordercode = MapUtils.getString(params, "ordercode");
        //支付方式
        String businessorigin = MapUtils.getString(params, "businessorigin");

        if (StringUtils.isEmpty(ordercode)) {
            throw new ServiceException("支付预单号不能为空");
        }

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException("商户不能为空");
        }

        if (StringUtils.isEmpty(businesscode)) {
            throw new ServiceException("业务单号不能为空");
        }

        if (StringUtils.isEmpty(businessorigin)) {
            throw new ServiceException("业务类型不能为空");
        }

        try {
            pagePay2self(shopcode, "SCAN_PAY_2_SELF", businesscode, businessorigin, params, response);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        return ZLResult.Success();
    }

    /**
     * 收银端 刷卡支付 实时调用支付宝查询接口 查询订单支付状态
     * @param request
     * @return
     */
    @RequestMapping("/entry/queryOrderStatus")
    @ResponseBody
    public ZLResult queryOrderStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
              zlRpcResult=apitAliPayService.queryOrderStatus(params);
        } catch (ServiceException sx) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> commodityList = zlRpcResult.getList();
        return ZLResult.Success(commodityList);
    }

    /**
     * 商户后台支付宝支付 成功  回调接口
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    @RequestMapping("/entry/return_url")
    public void return_url(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {

        String CHARSET = "UTF-8";
        ZLRpcResult zlRpcResult = null;
        try {
            Map<String, String> params = CommonUtil.getAliParams(request);

            //判断验签是否正常
            boolean signVerified = AlipaySignature.rsaCheckV1(params, SystemConfig.PAY_ALI_ALIPAY_PUBLIC_KEY, CHARSET, SystemConfig.PAY_ALI_SIGN_TYPE); //调用SDK验证签名

            //——请在这里编写您的程序（以下代码仅作参考）——
            if (signVerified) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), CHARSET);

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), CHARSET);

                Map<String,Object> param=new HashMap<String, Object>();

                param = new HashMap<String, Object>();
                param.put("out_trade_no",out_trade_no);
                param.put("trade_no",trade_no);
                //更新状态
                zlRpcResult = apitAliPayService.return_url(out_trade_no, trade_no,param);

                //回调地址
                Map results = (Map) zlRpcResult.getData();
                String returnUrl=MapUtils.getString(results,"returnUrl");
                String businessorigin=MapUtils.getString(results,"businessorigin");

                //跳转页面    配置路径 测试环境不一样，正式环境路径值书一样的
                if(StringUtils.equals(businessorigin,"srvms-serverRecharge")||StringUtils.equals(businessorigin,"bugms-service")){
                    response.sendRedirect(SystemConfig.RECHARGE_SERVRETURN_URL_DOMAIN+returnUrl);
                }else{
                    response.sendRedirect(SystemConfig.RECHARGE_RETURN_URL_DOMAIN+returnUrl);
                }
            }

        } catch (ServiceException sx) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    public void pagePay2self(String shopcode, String businesstype, String businesscode, String businessorigin, Map<String, Object> params, HttpServletResponse response) throws ServletException, IOException {
        ZLRpcResult zlRpcResult = null;
        try {
            //   系统调用 ,传的金额 写死为零，  rpc 调用查出预支付金额与订单号
            zlRpcResult = apitAliPayService.microPay2Shop(0, shopcode, businesstype, businesscode, businessorigin, params);
        } catch (ServiceException sx) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> orderInfo = zlRpcResult.getMap();

        String ordercode = MapUtils.getString(orderInfo, "ordercode");
        String payamount = MapUtils.getString(orderInfo, "payamount");

        logger.info("ordercode=" + ordercode);
        logger.info("payamount=" + payamount);

        String FORMAT = "json";
        String CHARSET = "UTF-8";
        AlipayClient alipayClient = new DefaultAlipayClient(SystemConfig.PAY_ALI_GATEWAY_URL, SystemConfig.PAY_ALI_APP_ID, SystemConfig.PAY_ALI_APP_PRIVATE_KEY, FORMAT, CHARSET, SystemConfig.PAY_ALI_ALIPAY_PUBLIC_KEY, SystemConfig.PAY_ALI_SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(SystemConfig.PAY_ALI_RETURN_URL);
        alipayRequest.setNotifyUrl(SystemConfig.PAY_ALI_NOTIFY_URL);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + ordercode + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + payamount + "," +
                "    \"subject\":\"" + SystemConfig.PAY_ALI_SUBJECT + "\"," +
                "    \"body\":\"" + SystemConfig.PAY_ALI_SUBJECT + "\"," +
                "    \"passback_params\":\"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/entry/notify_url")
    public void notify_url(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException, AlipayApiException {

        String CHARSET = "UTF-8";
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            Map<String, String> params = CommonUtil.getAliParams(request);
            PrintWriter out = httpResponse.getWriter();

            boolean signVerified = AlipaySignature.rsaCheckV1(params, SystemConfig.PAY_ALI_ALIPAY_PUBLIC_KEY, CHARSET, SystemConfig.PAY_ALI_SIGN_TYPE); //调用SDK验证签名

            /* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
            */

            if (signVerified) {//验证成功
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), CHARSET);

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), CHARSET);

                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), CHARSET);

                //交易支付时间
                String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), CHARSET);

                Map<String,Object> param=new HashMap<String, Object>();
                param.put("finishtime",gmt_payment);
                param.put("out_trade_no",out_trade_no);
                param.put("trade_no",trade_no);

                if (trade_status.equals("TRADE_FINISHED")) {
                    try {
                        //   系统调用
                        apitAliPayService.notify_url(out_trade_no, trade_no,param);
                    } catch (ServiceException sx) {
                        //系统级别异常
                        throw new ServiceException(ServiceException.SYS_ERROR);
                    }

                    if (!zlRpcResult.success()) {
                        throw new ServiceException(zlRpcResult.getErrorMsg());
                    }

                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    try {
                        //   系统调用
                        apitAliPayService.notify_url(out_trade_no, trade_no,param);
                    } catch (ServiceException sx) {
                        //系统级别异常
                        throw new ServiceException(ServiceException.SYS_ERROR);
                    }

                    if (!zlRpcResult.success()) {
                        throw new ServiceException(zlRpcResult.getErrorMsg());
                    }
                }

                out.println("success");

            } else {//验证失败
                out.println("fail");

                //调试用，写文本函数记录程序运行情况是否正常
                //String sWord = AlipaySignature.getSignCheckContentV1(params);
                //AlipayConfig.logResult(sWord);
            }

        } catch (ServiceException sx) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
    }

    /**
     *   收银端 支付成功通知，更新订单状态
     */
    @RequestMapping("/entry/apinotify_url")
    public void apinotify_url(HttpServletRequest request, HttpServletResponse httpResponse) {
        String CHARSET = "UTF-8";
        try {
            Map<String, String> params = CommonUtil.getAliParams(request);
            PrintWriter out = httpResponse.getWriter();
            //商户订单号
            String order = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), CHARSET);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("out_trade_no",order);
            //查询订单信息与商户配置信息
            ZLRpcResult  zlRpcResult=apiAliPayService.getOrderInfo(param);
            Map<String,Object> orderinfo=new HashMap<String, Object>();
            if(zlRpcResult.success()){
                orderinfo= (Map<String, Object>) zlRpcResult.getData();
            }
            String alipaypublickey=MapUtils.getString(orderinfo,"alipaypublickey");
            String signtype=MapUtils.getString(orderinfo,"signtype");
            //判断商户的公钥是否配置，没有配置就结束方法
            if(StringUtils.isEmpty(alipaypublickey)){
                logger.info("单号:"+order+"支付通知失败，未获取到alipaypublickey");
                return;
            }
           logger.info("公钥：=-------------"+alipaypublickey);
            logger.info("signtype：=-------------"+signtype);
            //取出支付方式编码
            String paytypecode=MapUtils.getString(orderinfo,"paytypecode","");
            if(StringUtils.isEmpty(paytypecode)){
                paytypecode=Contants.PAYTYPE_ALI_SM;
            }

            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipaypublickey, "UTF-8", signtype); //调用SDK验证签名

            if (signVerified) {//验证成功
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), CHARSET);
                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), CHARSET);
                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), CHARSET);
                //买家在支付宝的用户id
                String buyer_user_id = new String(request.getParameter("buyer_logon_id").getBytes("ISO-8859-1"), CHARSET);
                //交易支付时间
                String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), CHARSET);

                param.put("paytypecode",paytypecode);
                param.put("out_trade_no",out_trade_no);
                param.put("trade_no",trade_no);
                param.put("alibuyerid",buyer_user_id);
                param.put("finishtime",gmt_payment);

                //TRADE_SUCCESS：交易支付成功，可退款；TRADE_FINISHED：交易结束，不可退款。
                if (trade_status.equals("TRADE_FINISHED")) {
                     //暂不做处理
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    logger.info("TRADE_SUCCESS-------------"+out_trade_no);
                    apiAliPayService.updateOrderStatus(param);
                }
                out.println("success");
            } else {//验证失败
                logger.info("收银端支付宝付款成功通知 验签失败！");
                out.println("fail");
            }

        } catch (IOException e) {
            System.out.print("错误信息"+e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
