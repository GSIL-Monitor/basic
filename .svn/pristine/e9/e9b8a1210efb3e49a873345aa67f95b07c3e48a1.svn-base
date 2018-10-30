package com.wgb.controller.mt.sales;

import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.salems.admin.ApiSelectCouponService;
import com.wgb.service.dubbo.salems.web.ApitCouponService;
import com.wgb.util.ExcelReader;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/sales/coupon")
public class MTCouponController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitCouponService apitCouponService;

    @Autowired
    private ApiSelectCouponService apiSelectCouponService;


    /**
     * 收银端-优惠券查询
     * @param request
     * @return
     */

    @RequestMapping("/queryCoupon")
    @ResponseBody
    public ZLResult queryCouponDetail(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();

        //获取商户号码
        String shopcode = MapUtils.getString(params, "shopcode");

        //获取门店号
        String branchcode = MapUtils.getString(params, "branchcode");

        String total =  MapUtils.getString(params, "total");

        String memberexclusive =  MapUtils.getString(params, "memberexclusive");

        String loginusermembermodel =  MapUtils.getString(params, "loginusermembermodel");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //获得商品json格式
        String commoditys = MapUtils.getString(params, "commoditys");

        //获得单行
        JSONArray commoditysJsonArray = JSONArray.fromObject(commoditys);


        //存放商品信息
        List<Map<String, Object>> commodityList = new ArrayList<Map<String, Object>>();

        for (int j = 0; j < commoditysJsonArray.size(); j++) {

            //获得每行的数据
            JSONObject commodityJson = commoditysJsonArray.getJSONObject(j);

            //添加商品信息
            commodityList.add(commodityJson);
        }

        //存放每个商品的信息
        Map<String, Object> couponMap = new HashMap<String,Object>();

        couponMap.put("commoditys", commodityList);

        couponMap.put("total", total);

        couponMap.put("shopcode", shopcode);

        couponMap.put("branchcode", branchcode);

        couponMap.put("memberexclusive", memberexclusive);

        couponMap.put("loginusermembermodel", loginusermembermodel);

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiSelectCouponService.queryCouponDetail(couponMap);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
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

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCouponService.queryPageCoupon(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }


    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCouponService.querySelectPageCouponList(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/addCoupon")
    @ResponseBody
    public ZLResult addCoupon(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCouponService.insertCoupon(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    @RequestMapping("/delCoupon")
    @ResponseBody
    public ZLResult delCoupon(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkDelCouponParams(params);
            //系统调用
            zlRpcResult = apitCouponService.delCoupon(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    @RequestMapping("/queryCouponDetail")
    @ResponseBody
    public Map<String, Object> queryCouponDetails(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象s
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkQueryCouponDetailParams(params);
            //系统调用
            zlRpcResult = apitCouponService.queryCouponDetail(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return zlRpcResult.getMap();
    }

    /**
     * 优惠券-实体劵导入
     *
     * @param file
     * @param request
     * @param response
     */
    @RequestMapping("/couponImport")
    @ResponseBody
    public ZLResult couponImport(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            // 字段名数组
            String[] columns = {"couponnum"};
            // 模板标题
            String[] columnNames = {"优惠劵编号"};

            List<Map<String, Object>> dataList = ExcelReader.read(file.getInputStream(), columns, columnNames, 2);
            params.put("fileDateList", dataList);
            //系统调用
            zlRpcResult = apitCouponService.couponImport(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    /**
     * 删除优惠劵校验参数 id
     *
     */
    private void checkDelCouponParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "id"))) {
            throw new ServiceException("操作异常");
        }
    }

    /**
     * 添加查询优惠劵校验参数 id
     *
     */
    private void checkQueryCouponDetailParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "id"))) {
            throw new ServiceException("操作异常");
        }
    }
}
