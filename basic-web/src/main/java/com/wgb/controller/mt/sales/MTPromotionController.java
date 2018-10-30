package com.wgb.controller.mt.sales;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.salems.admin.ApiPromotionService;
import com.wgb.service.dubbo.salems.web.ApitPromotionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/sales/promotion")
public class MTPromotionController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitPromotionService apitPromotionService;

    @Autowired
    private ApiPromotionService apiPromotionService;


    /**
     * 促销查询
     * @param request
     * @return
     */

    @RequestMapping("/queryPromotion")
    @ResponseBody
    public ZLResult queryPromotionMessage(HttpServletRequest request) {

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
        Map<String, Object> promotionMap = new HashMap<String,Object>();

        promotionMap.put("commoditys", commodityList);

        promotionMap.put("paytotal", total);

        promotionMap.put("shopcode", shopcode);

        promotionMap.put("branchcode", branchcode);

        promotionMap.put("memberexclusive", memberexclusive);

        promotionMap.put("loginusermembermodel", loginusermembermodel);

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPromotionService.queryPromotionDetail(promotionMap);
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
    public ZLResult queryPageList(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPromotionService.queryPagePromotion(params);

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

    @RequestMapping("/insertPromotion")
    @ResponseBody
    public ZLResult insertPromotion(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            String starttime = MapUtils.getString(params, "starttime");

            if (!checkDataTime(starttime)) {
                throw new ServiceException("开始时间不可选择之前的日期");
            }
            //系统调用
            zlRpcResult = apitPromotionService.insertPromotion(params);

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
        return ZLResult.Success();
    }

    public boolean checkDataTime(String starttime) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newdatetime = dateFormat.format(date);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startdate = null;
        Date newdate = null;

        try {
            startdate = format.parse(starttime);
            newdate = format.parse(newdatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startdate.compareTo(newdate) != 0) {
            if (startdate.before(newdate)) {
                return false;
            }
        }
        return true;

    }

    @RequestMapping("/delPromotion")
    @ResponseBody
    public ZLResult delPromotion(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkDelPromotionParams(params);
            //系统调用
            zlRpcResult = apitPromotionService.delPromotion(params);

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
        return ZLResult.Success();
    }

    @RequestMapping("/queryPromotiondetail")
    @ResponseBody
    public ZLResult queryPromotiondetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkPromotiondetailParams(params);
            //系统调用
            zlRpcResult = apitPromotionService.queryPromotiondetail(params);

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
     * 添加预付卡校验参数
     * 字段备注预付卡编码
     */
    private void checkDelPromotionParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "id"))) {
            throw new ServiceException("操作异常");
        }
    }

    /**
     * 添加预付卡校验参数
     * 字段备注预付卡编码
     */
    private void checkPromotiondetailParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "id"))) {
            throw new ServiceException("操作异常");
        }
    }
}
