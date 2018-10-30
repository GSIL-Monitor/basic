package com.wgb.controller.scp.xcx;

import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.scpms.web.ApitXcxCustomerService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/association")
public class ScpXcxCustomerController extends SCPXCXBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(ScpXcxCustomerController.class);

    @Autowired
    private ApitXcxCustomerService apitXcxCustomerService;

    @RequestMapping("/save")
    @ResponseBody
    public ZLResult saveCooperation(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitXcxCustomerService.saveCooperation(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

}
