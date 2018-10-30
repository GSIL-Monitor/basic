package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.salems.admin.ApiSelectCouponService;
import com.wgb.service.dubbo.salems.admin.ApiWeChatCouponService;
import com.wgb.service.dubbo.wxms.web.ApitCsCouponService;
import com.wgb.service.dubbo.wxms.web.ApitXCXCouponService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/xcxsc/coupon")
public class XCXCouponController extends MBXCXBaseController {

    @Autowired
    private ApiWeChatCouponService apiWeChatCouponService;

    @Autowired
    private ApitXCXCouponService apitXCXCouponService ;
    @Autowired
    private ApitCsCouponService apitCsCouponService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 优惠券--查询所有可见的优惠券
     *
     * @params shopcode、membermodel
     */

    @RequestMapping("/queryCoupon")
    @ResponseBody
    public ZLResult queryCoupon(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;

        try {

            //查询领取优惠券要传上客户号码，防止重复领取
            String mobile = MapUtils.getString(params, "mobile");
            params.put("telsign", mobile);
            zlRpcResult = apiWeChatCouponService.queryWeChatCoupon(params);

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 优惠券--领取优惠券
     *
     * @params
     */

    @RequestMapping("/getCoupon")
    @ResponseBody
    public ZLResult getCoupon(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitXCXCouponService.getCoupon(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("getCoupon 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 优惠券--可用优惠券的查询
     *
     * @params
     */

    @RequestMapping("/queryUsableCoupon")
    @ResponseBody
    public ZLResult queryUsableCoupon(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {

            params.put("telsign", MapUtils.getString(params, "mobile"));
            zlRpcResult = apiWeChatCouponService.queryWeChatCouponUsable(params);

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
     * 优惠券--优惠券使用记录
     *
     * @params
     */

    @RequestMapping("/queryCouponUseRecord")
    @ResponseBody
    public ZLResult queryCouponUseRecord(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            params.put("telsign", MapUtils.getString(params, "mobile"));
            zlRpcResult = apiWeChatCouponService.queryWeChatCouponLog(params);

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
     * 优惠券--查询优惠券详情
     *
     * @params
     */

    @RequestMapping("/queryCouponDetail")
    @ResponseBody
    public ZLResult queryCouponDetail(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = null;
        try {
            //前端传ID过来
            zlRpcResult = apiWeChatCouponService.queryWeChatCouponDetails(params);

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
     * 优惠券---- 下拉选的优惠券展示
     *
     * @params
     */

    @RequestMapping("/queryWeChatCouponDropDown")
    @ResponseBody
    public ZLResult queryWeChatCouponDropDown(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            //zlRpcResult = apitXCXCouponService.queryWeChatCouponDropDown(params);
            zlRpcResult = (ZLRpcResult) apitCsCouponService.queryWeChatCouponDropDown(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("queryWeChatCouponDropDown 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 优惠券--使用优惠券
     *
     * @params
     */

    @RequestMapping("/useCoupon")
    @ResponseBody
    public ZLResult useCoupon(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitXCXCouponService.useCoupon(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("useCoupon 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }

    private Map<String, Object> formatJsonCoupon(JSONObject jsoncoupon, Map<String, Object> params) {
        Map<String, Object> coupon = new HashMap<>();
        coupon.put("branchname", MapUtils.getString(params, "loginuserbranchname"));
        coupon.put("shopcode", MapUtils.getString(params, "shopcode"));
        coupon.put("branchcode", MapUtils.getString(params, "branchcode"));
        coupon.put("id", MapUtils.getString(jsoncoupon, "couponpid"));
        coupon.put("couponname", MapUtils.getString(jsoncoupon, "couponname"));
        coupon.put("coupontype", MapUtils.getString(jsoncoupon, "coupontype"));
        coupon.put("commoditycode", MapUtils.getString(jsoncoupon, "commoditycode"));
        coupon.put("coupontypename", MapUtils.getString(jsoncoupon, "coupontypename"));
        coupon.put("cpplicablescopename", MapUtils.getString(jsoncoupon, "cpplicablescopename"));
        coupon.put("memberdiscount", MapUtils.getString(jsoncoupon, "memberdiscount"));
        coupon.put("createcouponnum", MapUtils.getString(jsoncoupon, "createcouponnum"));
        coupon.put("alreadyused", MapUtils.getString(jsoncoupon, "alreadyused"));
        coupon.put("forcash", MapUtils.getString(jsoncoupon, "forcash"));
        coupon.put("buyprice", MapUtils.getString(jsoncoupon, "buyprice"));
        coupon.put("commodityname", MapUtils.getString(jsoncoupon, "commodityname"));
        coupon.put("categoryname", MapUtils.getString(jsoncoupon, "categoryname"));
        coupon.put("categorycode", MapUtils.getString(jsoncoupon, "categorycode"));
        coupon.put("discount", MapUtils.getString(jsoncoupon, "discount"));
        coupon.put("starttime", MapUtils.getString(jsoncoupon, "starttime"));
        coupon.put("endtime", MapUtils.getString(jsoncoupon, "endtime"));
        coupon.put("physicalstore", MapUtils.getString(jsoncoupon, "physicalstore"));
        coupon.put("onlinestore", MapUtils.getString(jsoncoupon, "onlinestore"));
        coupon.put("applyclient", MapUtils.getString(jsoncoupon, "applyclient"));
        coupon.put("couponpid", MapUtils.getString(jsoncoupon, "couponpid"));
        coupon.put("isused", "1");
        coupon.put("couponnum", MapUtils.getString(jsoncoupon, "couponnum"));
        coupon.put("conditions", MapUtils.getString(jsoncoupon, "conditions"));
        coupon.put("isreceived", MapUtils.getString(jsoncoupon, "isreceived"));
        coupon.put("telsign", MapUtils.getString(params, "mobile"));
        coupon.put("issuccess", MapUtils.getString(params, "issuccess"));


        return coupon;
    }
}
