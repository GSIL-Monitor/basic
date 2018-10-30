package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiSaleBillService;
import com.wgb.util.Contants;
import com.wgb.util.ZLConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wgb.service.mt.salebill.SaleBillService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/3/4.
 */
@Controller
@RequestMapping("/retail")
public class MTSaleBillController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiSaleBillService apiSaleBillService;

    @Autowired
    private SaleBillService saleBillService;

    /**
     * 零售 - 查询销售单
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySaleBill")
    @ResponseBody
    public ZLResult querySaleBill(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String ticketcode = MapUtils.getString(params, "ticketcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(ticketcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiSaleBillService.querySaleBill(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryReturnBill")
    @ResponseBody
    public ZLResult queryReturnBill(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String deviceuniquecode = MapUtils.getString(params, "deviceuniquecode");
        String starttime = MapUtils.getString(params, "starttime");
        String endtime = MapUtils.getString(params, "endtime");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(deviceuniquecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(starttime)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(endtime)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiSaleBillService.queryReturnBill(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * (MQ)零售 - 批量保存销售单据
     *
     * @param request
     * @return
     */
    @RequestMapping("/saveSaleBills")
    @ResponseBody
    public ZLResult saveSaleBills(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String salebills = MapUtils.getString(params, "salebills");

        if (StringUtils.isEmpty(salebills)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        List<Map<String, Object>> saleBills = getSaleBillList(salebills, params);
        //批量分页保存销售单
        saleBillService.batchPageSizeSaveSaleBills(saleBills);

        ZLRpcResult rpcResult = null;
        return ZLResult.Success();
    }


    /**
     * 解析返回字符串
     *
     * @param salebills
     * @param params
     */
    private List<Map<String, Object>> getSaleBillList(String salebills, Map<String, Object> params) {

        //返回的结果
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        try {

             //将字符串转成JSON数组对象
            JSONArray saleBillJsonArray = JSONArray.fromObject(salebills);

            Map<String, Object> saleBillMap =  new HashMap<String,Object>();

            for (int i = 0; i < saleBillJsonArray.size(); i++) {

                JSONObject saleBillJson = saleBillJsonArray.getJSONObject(i);

                saleBillMap = saleBillJson;

                //请求头
                saleBillMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                saleBillMap.put("createusername", MapUtils.getString(params, ZLConstant.LOGIN_USER_FULL_NAME, ""));

                //商品行数据
                JSONArray commodityJsonArray = saleBillJson.getJSONArray("commoditys");

                List<Map<String, Object>> commodityList = new ArrayList<Map<String, Object>>();

                for (int j = 0; j < commodityJsonArray.size(); j++) {
                    JSONObject commodityJson = commodityJsonArray.getJSONObject(j);
                    Map<String, Object> commodityMap = commodityJson;
                    commodityMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                    commodityMap.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ID, ""));

                    commodityList.add(commodityMap);
                }

                saleBillMap.put("commoditys", commodityList);

                //支付行数据
                JSONArray paydetails = saleBillJson.getJSONArray("paydetails");

                List<Map<String, Object>> paydetailList = new ArrayList<Map<String, Object>>();
                for (int index = 0; index < paydetails.size(); index++) {
                    JSONObject paydetailJson = paydetails.getJSONObject(index);
                    Map<String, Object> paydetail = paydetailJson;
                    paydetail.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                    paydetailList.add(paydetail);
                }
                saleBillMap.put("paydetails", paydetailList);


                //优惠行数据
                JSONArray discountdetails = saleBillJson.getJSONArray("discountdetails");

                List<Map<String, Object>> discountdetailsList = new ArrayList<Map<String, Object>>();
                for (int index = 0; index < discountdetails.size(); index++) {
                    JSONObject discountdetailJson = discountdetails.getJSONObject(index);
                    Map<String, Object> discountdetail = discountdetailJson;
                    discountdetail.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                    discountdetailsList.add(discountdetail);
                }
                saleBillMap.put("discountdetailsList", discountdetailsList);

                //返回结果
                resultList.add(saleBillMap);
            }

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.CODE_20302);
        }


        return resultList;
    }

    /**
     * 零售 - 上传单条销售单
     *
     * @param request
     * @return
     */
    @RequestMapping("/saveOneSaleBill")
    @ResponseBody
    public ZLResult saveOneSaleBill(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String saleBill = MapUtils.getString(params, "salebill");
        if (StringUtils.isEmpty(saleBill)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        try {
            apiSaleBillService.saveOneSaleBill(getSaleBillMap(saleBill, params));
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        return ZLResult.Success();
    }

    /**
     * 解析返回字符串
     *
     * @param saleBill
     */
    private Map<String, Object> getSaleBillMap(String saleBill, Map<String, Object> params) {

        Map<String, Object> saleBillMap;

        try {
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(saleBill);
            saleBillMap = jsonObject;
            saleBillMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));

            //商品行数据
            JSONArray commoditys = jsonObject.getJSONArray("commoditys");
            if (commoditys == null) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            List<Map<String, Object>> commodityList = new ArrayList<Map<String, Object>>();
            for (int index = 0; index < commoditys.size(); index++) {
                JSONObject commodityJson = commoditys.getJSONObject(index);
                Map<String, Object> commodity = commodityJson;
                commodity.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                commodityList.add(commodity);
            }
            saleBillMap.put("commoditys", commodityList);

            //支付行数据
            JSONArray paydetails = jsonObject.getJSONArray("paydetails");
            List<Map<String, Object>> paydetailList = new ArrayList<Map<String, Object>>();
            if (paydetails != null) {
                for (int index = 0; index < paydetails.size(); index++) {
                    JSONObject paydetailJson = paydetails.getJSONObject(index);
                    Map<String, Object> paydetail = paydetailJson;
                    paydetail.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                    paydetailList.add(paydetail);
                }
            }
            saleBillMap.put("paydetails", paydetailList);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.CODE_20302);
        }

        return saleBillMap;
    }
}
