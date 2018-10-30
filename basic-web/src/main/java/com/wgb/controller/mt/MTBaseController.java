package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.pays.admin.ApiPayService;
import com.wgb.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.*;

/**
 * Controller通用父类
 */
@Controller
@Qualifier("/baseController")
public abstract class MTBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MTBaseController.class);

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map<String, Object> getParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return HttpRequestUtil.getParams(request);
    }

    public Map<String, Object> getPubParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return HttpRequestUtil.getParams(request);
    }

    /**
     * 设置门店参数
     *
     * @param params
     */
    public void setBranchParams(Map<String, Object> params) {
        params.put("branchcode", MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE));
        params.put("branchname", MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_NAME));
    }

    public Map<String, Object> Success(Object data) {
        ApiUtil.formatObjectForApi(data);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "1");
        result.put("data", data);
        return result;
    }

    public Map<String, Object> Success() {
        return Success(null);
    }

    public Map<String, Object> Error(String errorMsg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "0");
        result.put("errorMsg", errorMsg);
        return result;
    }

    /**
     * 返回结果为字符串类型，且字符串是一个提示语
     *
     * @param rpcResult
     * @return
     */
    public ZLResult parseRpcResultForMessage(ZLRpcResult rpcResult) {
        if (null == rpcResult) {
            LOGGER.info("调用RPC，未返回RPC调用结果！");
            return ZLResult.Error("系统异常，请联系管理员!");
        }
        ZLResult result;
        if (rpcResult.success()) {
            if (null != rpcResult.getData() && StringUtils.isNotBlank(rpcResult.getData().toString())) {
                result = ZLResult.Error(rpcResult.getData().toString());
            } else {
                result = ZLResult.Success();
            }
        } else {
            result = ZLResult.Error(rpcResult.getErrorMsg());
        }
        return result;
    }

    /**
     * 返回结果，为数据
     *
     * @param rpcResult
     * @return
     */
    public ZLResult parseRpcResultForData(ZLRpcResult rpcResult) {
        if (null == rpcResult) {
            LOGGER.info("调用RPC，未返回RPC调用结果！");
            return ZLResult.Error("系统异常，请联系管理员!");
        }
        ZLResult result;
        if (rpcResult.success()) {
            result = ZLResult.Success(rpcResult.getData());
        } else {
            result = ZLResult.Error(rpcResult.getErrorMsg());
        }
        return result;
    }

    /**
     * 校验是否有参数
     *
     * @param value
     * @param keys
     * @return
     */
    public boolean checkParam(Map<String, Object> value, List<String> keys) {
        for (String key : keys) {
            if (!value.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 处理门店
     */
    public void handleIshead(Map<String, Object> params) {
        String loginuserbranchishead = MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ISHEAD, "0");
        String loginuserbranchcode = MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE);

        //若是分店则branchcode取当前登录门店
        if (StringUtils.equals(loginuserbranchishead, "0")) {
            params.put("branchcode", loginuserbranchcode);

            //若是总店且branchcode为空,branchcode取当前登录门店
        } else if (StringUtils.equals(loginuserbranchishead, "1") && StringUtils.isEmpty(MapUtils.getString(params, "branchcode"))) {
            params.put("branchcode", loginuserbranchcode);
        }
    }

    /**
     * 处理门店
     */
    public void handleIsheadCope(Map<String, Object> params) {
        String loginuserbranchishead = MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ISHEAD, "0");
        String loginuserbranchcode = MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_CODE);
        String branchcode = MapUtils.getString(params, "branchcode", "");
        //若是分店则branchcode取当前登录门店
        if (StringUtils.equals(loginuserbranchishead, "0")) {
            params.put("branchcode", loginuserbranchcode);

            //若是branchcode为000则显示全部
        } else if ("000".equals(branchcode)) {
            params.put("branchcode", "");
        }
    }
}
