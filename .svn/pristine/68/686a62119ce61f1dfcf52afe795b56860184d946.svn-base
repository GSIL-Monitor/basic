package com.wgb.controller.mt.act;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.acts.admin.ApiActService;
import com.wgb.service.dubbo.acts.web.ApitPortalActService;
import com.wgb.service.mt.act.ActService;
import com.wgb.util.ZLConstant;
import net.sf.json.JSONArray;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzy on 2018/5/15.
 */
@Controller
@RequestMapping("/acts/act")
public class MTActController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTActController.class);

    @Autowired
    private ApitPortalActService apitPortalActService;

    @Autowired
    private ApiActService apiActService;

    @Autowired
    private ActService actService;

    /**
     * 收银端接口-获取最新广告数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/getNewestAct")
    @ResponseBody
    public ZLResult getNewestAct(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        String deviceuniquecode = MapUtils.getString(params, "deviceuniquecode");
        String devicetype = MapUtils.getString(params, "devicetype");

        if (StringUtils.isEmpty(deviceuniquecode) || StringUtils.isEmpty(devicetype)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiActService.getNewestAct(params);

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
     * 收银端接口-同步广告播放时长到acts
     *
     * @param request
     * @return
     */
    @RequestMapping("/syncActPlayDetail")
    @ResponseBody
    public ZLResult syncActPlayDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        ZLRpcResult rpcResult = null;

        //格式化数据
        List<Map<String, Object>> playList = getActPlayList(params);

        try {
            params.put("playList", playList);
            //同步广告播放时长到acts
            actService.syncActPlayDetail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        return ZLResult.Success();
    }

    /**
     * 收银端接口-更新设备状态
     *
     * @param request
     * @return
     */
    @RequestMapping("/syncDeviceStatus")
    @ResponseBody
    public ZLResult syncDeviceStatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = null;

        try {
            actService.syncDeviceStatus(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        return ZLResult.Success();
    }

    /**
     * 获取播放记录清单
     *
     * @param params
     * @return
     */
    private List<Map<String, Object>> getActPlayList(Map<String, Object> params) {

        String plays = MapUtils.getString(params, "plays");

        if (StringUtils.isEmpty(plays)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        JSONArray jsonArray = JSONArray.fromObject(plays);

        if (jsonArray == null || jsonArray.size() == 0) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (int index = 0; index < jsonArray.size(); index++) {
            Map<String, Object> playMap = jsonArray.getJSONObject(index);
            playMap.put("loginuserid", MapUtils.getString(params, "loginuserid"));
            playMap.put("deviceuniquecode", MapUtils.getString(params, "deviceuniquecode"));
            playMap.put("branchcode", MapUtils.getString(params, ZLConstant.LOGIN_USER_BRANCH_CODE));
            result.add(playMap);
        }
        return result;
    }

    /**
     * 2.0广告资源展示页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult queryManageDetail(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPortalActService.queryManageDetail(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 2.0广告资源删除
     *
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPortalActService.delete(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        return isSuccess(zlRpcResult);
    }

    /**
     * 2.0广告资源修改
     *
     * @param request
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPortalActService.saveOrUpdate(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        return isSuccess(zlRpcResult);
    }

    private ZLResult isSuccess(ZLRpcResult rpcResult) {
        boolean success = rpcResult.success();
        if (success) {
            return ZLResult.Success();
        } else {
            return ZLResult.Error(rpcResult.getErrorMsg());
        }
    }
}
