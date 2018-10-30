package com.wgb.controller.mt.bug;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.bugms.admin.ApiDeviceBugService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
 * Created by yjw on 2017/9/23 0023.
 */
@Controller
@RequestMapping("/device/bug")
public class MTDeviceBugController extends MTBaseController {

    @Autowired
    private ApiDeviceBugService apiDeviceBugService;

    /**
     * bug-批量上传设备异常信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/entry/saveDeviceBugsInfo")
    @ResponseBody
    public Object saveSaleBills(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String devicebugs = MapUtils.getString(params, "devicebugs");

        if (StringUtils.isEmpty(devicebugs)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {

            //json数据初始化
            List<Map<String,Object>> result =new ArrayList<Map<String, Object>>();
            JSONArray jsonArray = JSONArray.fromObject(devicebugs);
            for (int index = 0; index < jsonArray.size(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("shopcode", getJsonString(jsonObject, "shopcode"));
                param.put("branchcode", getJsonString(jsonObject, "branchcode"));
                param.put("createtime", getJsonString(jsonObject, "createtime"));
                param.put("deviceserialnumber", MapUtils.getString(jsonObject, "deviceserialnumber"));
                param.put("deviceuniquecode", MapUtils.getString(jsonObject, "deviceuniquecode"));
                param.put("devicetype", MapUtils.getString(jsonObject, "devicetype"));
                param.put("errortype", MapUtils.getString(jsonObject, "errortype"));
                param.put("errorsource", MapUtils.getString(jsonObject, "errorsource"));
                param.put("errordevice", MapUtils.getString(jsonObject, "errordevice"));
                param.put("errorevent", MapUtils.getString(jsonObject, "errorevent"));
                param.put("errorcode", MapUtils.getString(jsonObject, "errorcode"));
                param.put("errordesc", MapUtils.getString(jsonObject, "errordesc"));
                param.put("errorremark", MapUtils.getString(jsonObject, "errorremark"));
                param.put("errorclass", MapUtils.getString(jsonObject, "errorclass"));
                param.put("errormethod", MapUtils.getString(jsonObject, "errormethod"));
                param.put("systemmodel", MapUtils.getString(jsonObject, "systemmodel"));
                param.put("systemversion", MapUtils.getString(jsonObject, "systemversion"));
                param.put("username", MapUtils.getString(jsonObject, "username"));
                param.put("softwareversion", MapUtils.getString(jsonObject, "softwareversion"));
                zlRpcResult = apiDeviceBugService.saveOneDeviceBugInfo(param);
            }

        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success();
    }

    private String getJsonString(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 获取设备监测部件
     * @param request
     * @return
     */
    @RequestMapping("/entry/getDeviceParts")
    @ResponseBody
    public Object getDeviceMissingParts(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String devicemodel = MapUtils.getString(params, "devicemodel");

        if (StringUtils.isEmpty(devicemodel)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiDeviceBugService.getDeviceMissingParts(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }
}
