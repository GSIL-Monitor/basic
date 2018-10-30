package com.wgb.controller.mt.purchase;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.admin.ApiPurchaseService;
import com.wgb.util.Contants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
 * Created by wgb on 2017/3/12.
 */
@Controller
@RequestMapping("/purchase")
public class MTPurchaseController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTPurchaseController.class);

    @Autowired
    private ApiPurchaseService apiPurchaseService;

    /**
     * 查询采购订单
     * @param request
     * @return
     */
    @RequestMapping("/queryPurchaseOrder")
    @ResponseBody
    public ZLResult queryPurchaseOrder(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.queryPurchaseOrder(params);
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
     * 审核采购订单
     * @param request
     * @return
     */
    @RequestMapping("/examinePurchaseOrder")
    @ResponseBody
    public ZLResult examinePurchaseOrder(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.examinePurchaseOrder(params);
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
     * 删除采购订单
     * @param request
     * @return
     */
    @RequestMapping("/deletePurchaseOrder")
    @ResponseBody
    public ZLResult deletePurchaseOrder(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.deletePurchaseOrder(params);
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
     * 采购 - 保存采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/savePurchaseOrder")
    @ResponseBody
    public ZLResult savePurchaseOrder(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        String purchaseOrder = MapUtils.getString(params, "purchaseOrder");
        if (StringUtils.isEmpty(purchaseOrder)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.savePurchaseOrder(getPurchaseOrderMap(purchaseOrder, params));
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
     * 解析返回字符串
     *
     * @param purchaseOrder
     */
    private Map<String, Object> getPurchaseOrderMap(String purchaseOrder, Map<String, Object> params) {

        Map<String, Object> purchaseOrderMap;

        try {
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(purchaseOrder);
            purchaseOrderMap = jsonObject;
            purchaseOrderMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
            purchaseOrderMap.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
            purchaseOrderMap.put(Contants.LOGIN_USER_BRANCH_CODE, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE, ""));
            purchaseOrderMap.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ID, ""));

            //商品行数据
            JSONArray purchaseOrderGoods = jsonObject.getJSONArray("purchaseOrderGoods");
            if (purchaseOrderGoods == null) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();
            for (int index = 0; index < purchaseOrderGoods.size(); index++) {
                JSONObject purchaseOrderGoodJson = purchaseOrderGoods.getJSONObject(index);
                Map<String, Object> purchaseOrderGood = purchaseOrderGoodJson;
                purchaseOrderGood.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                purchaseOrderGood.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
                goods.add(purchaseOrderGood);
            }
            purchaseOrderMap.put("purchaseOrderGoods", goods);

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.CODE_20302);
        }

        return purchaseOrderMap;
    }

    /**
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPurchaseReceipt")
    @ResponseBody
    public ZLResult queryPurchaseReceipt(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.queryPurchaseReceipt(params);
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
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/examinePurchaseReceipt")
    @ResponseBody
    public ZLResult examinePurchaseReceipt(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.examinePurchaseReceipt(params);
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
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/deletePurchaseReceipt")
    @ResponseBody
    public ZLResult deletePurchaseReceipt(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.deletePurchaseReceipt(params);
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
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/savePurchaseReceipt")
    @ResponseBody
    public ZLResult savePurchaseReceipt(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        String purchaseReceipt = MapUtils.getString(params, "purchaseReceipt");
        if (StringUtils.isEmpty(purchaseReceipt)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.savePurchaseReceipt(getPurchaseReceiptMap(purchaseReceipt, params));
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
     * 解析返回字符串
     *
     * @param purchaseReceipt
     */
    private Map<String, Object> getPurchaseReceiptMap(String purchaseReceipt, Map<String, Object> params) {

        Map<String, Object> purchaseReceiptMap;

        try {
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(purchaseReceipt);
            purchaseReceiptMap = jsonObject;
            purchaseReceiptMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
            purchaseReceiptMap.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
            purchaseReceiptMap.put(Contants.LOGIN_USER_BRANCH_CODE, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE, ""));
            purchaseReceiptMap.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ID, ""));

            //商品行数据
            JSONArray purchaseReceiptGoods = jsonObject.getJSONArray("purchaseReceiptGoods");
            if (purchaseReceiptGoods == null) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();
            for (int index = 0; index < purchaseReceiptGoods.size(); index++) {
                JSONObject purchaseReceiptGoodJson = purchaseReceiptGoods.getJSONObject(index);
                Map<String, Object> purchaseReceiptGood = purchaseReceiptGoodJson;
                purchaseReceiptGood.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                purchaseReceiptGood.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
                goods.add(purchaseReceiptGood);
            }
            purchaseReceiptMap.put("purchaseReceiptGoods", goods);

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.CODE_20302);
        }

        return purchaseReceiptMap;
    }

    /**
     * 采购 - 查询采购退货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPurchaseReturn")
    @ResponseBody
    public ZLResult queryPurchaseReturn(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.queryPurchaseReturn(params);
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
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/examinePurchaseReturn")
    @ResponseBody
    public ZLResult examinePurchaseReturn(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.examinePurchaseReturn(params);
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
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/deletePurchaseReturn")
    @ResponseBody
    public ZLResult deletePurchaseReturn(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.deletePurchaseReturn(params);
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
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/savePurchaseReturn")
    @ResponseBody
    public ZLResult savePurchaseReturn(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        String purchaseReturn = MapUtils.getString(params, "purchaseReturn");
        if (StringUtils.isEmpty(purchaseReturn)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPurchaseService.savePurchaseReturn(getPurchaseReturnMap(purchaseReturn, params));
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
     * 解析返回字符串
     *
     * @param purchaseReturn
     */
    private Map<String, Object> getPurchaseReturnMap(String purchaseReturn, Map<String, Object> params) {

        Map<String, Object> purchaseReturnMap = new HashMap<String, Object>();

        try {
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(purchaseReturn);
            purchaseReturnMap = jsonObject;
            purchaseReturnMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
            purchaseReturnMap.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
            purchaseReturnMap.put(Contants.LOGIN_USER_BRANCH_CODE, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE, ""));
            purchaseReturnMap.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ID, ""));

            //商品行数据
            JSONArray purchaseReturnGoods = jsonObject.getJSONArray("purchaseReturnGoods");
            if (purchaseReturnGoods == null) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();
            for (int index = 0; index < purchaseReturnGoods.size(); index++) {
                JSONObject purchaseReturnGoodJson = purchaseReturnGoods.getJSONObject(index);
                Map<String, Object> purchaseReturnGood = purchaseReturnGoodJson;
                purchaseReturnGood.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                purchaseReturnGood.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
                goods.add(purchaseReturnGood);
            }
            purchaseReturnMap.put("purchaseReturnGoods", goods);

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.CODE_20302);
        }

        return purchaseReturnMap;
    }
}
