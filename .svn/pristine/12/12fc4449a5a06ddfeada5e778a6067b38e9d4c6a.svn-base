package com.wgb.controller.mt;

import com.wgb.bean.ZLResult;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiBranchService;
import com.wgb.service.dubbo.urms.admin.ApiUserService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgb on 2017/3/28.
 */
@Controller
@RequestMapping("/expire")
public class MTExpireController extends MTBaseController {

    @Autowired
    private ApiBranchService apiBranchService;

    @RequestMapping("/getExpirestmeAlert")
    @ResponseBody
    public Object getExpirestmeAlert(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;

        //检验门店是否到期
        try {
            zlRpcResult = apiBranchService.getBranchExpirestimeAlert(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorCode());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
