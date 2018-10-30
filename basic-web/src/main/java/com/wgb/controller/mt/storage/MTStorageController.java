package com.wgb.controller.mt.storage;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.admin.ApiStorageService;
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
 * Created by wgb on 2017/3/13.
 */
@Controller
@RequestMapping("/storage")
public class MTStorageController extends MTBaseController {

    @Autowired
    private ApiStorageService apiStorageService;

    /**
     * 采购 - 查询采购收货单
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryStorageCheck")
    @ResponseBody
    public ZLResult queryStorageCheck(HttpServletRequest request) {
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
            zlRpcResult = apiStorageService.queryStorageCheck(params);
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
    @RequestMapping("/examineStorageCheck")
    @ResponseBody
    public ZLResult examineStorageCheck(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiStorageService.examineStorageCheck(params);
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
     * 库存 - 查询库存明细
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryStorageDetail")
    @ResponseBody
    public ZLResult queryStorageDetail(HttpServletRequest request) {

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
            zlRpcResult = apiStorageService.queryStorageDetail(params);
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
    @RequestMapping("/deleteStorageCheck")
    @ResponseBody
    public ZLResult deleteStorageCheck(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String id = MapUtils.getString(params, "id");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }


        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiStorageService.deleteStorageCheck(params);
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
    @RequestMapping("/saveStorageCheck")
    @ResponseBody
    public ZLResult saveStorageCheck(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        String storageCheck = MapUtils.getString(params, "storageCheck");
        if (StringUtils.isEmpty(storageCheck)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiStorageService.saveStorageCheck(getStorageCheckMap(storageCheck, params));
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
     * @param storageCheck
     */
    private Map<String, Object> getStorageCheckMap(String storageCheck, Map<String, Object> params) {

        Map<String, Object> storageCheckMap = new HashMap<String, Object>();

        try {
            JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(storageCheck);
            storageCheckMap = jsonObject;
            storageCheckMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
            storageCheckMap.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
            storageCheckMap.put(Contants.LOGIN_USER_BRANCH_ID, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ID, ""));
            storageCheckMap.put(Contants.LOGIN_USER_BRANCH_CODE, MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE, ""));

            //商品行数据
            JSONArray storageCheckGoods = jsonObject.getJSONArray("storageCheckGoods");
            if (storageCheckGoods == null) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>();
            for (int index = 0; index < storageCheckGoods.size(); index++) {
                JSONObject storageCheckGoodJson = storageCheckGoods.getJSONObject(index);
                Map<String, Object> storageCheckGood = storageCheckGoodJson;
                storageCheckGood.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                storageCheckGood.put(Contants.LOGIN_USER_FULL_NAME, MapUtils.getString(params, Contants.LOGIN_USER_FULL_NAME, ""));
                goods.add(storageCheckGood);
            }
            storageCheckMap.put("storageCheckGoods", goods);

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        return storageCheckMap;
    }
}
