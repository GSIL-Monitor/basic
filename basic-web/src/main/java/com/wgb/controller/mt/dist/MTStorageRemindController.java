package com.wgb.controller.mt.dist;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageRemindService;
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
@RequestMapping("/dist/storageRemind")
public class MTStorageRemindController extends MTBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(MTStorageRemindController.class);

    @Autowired
    private ApitStorageRemindService apitStorageRemindService;

    @RequestMapping("/queryStorageRequire")
    @ResponseBody
    public ZLResult queryStorageRequire(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult  = apitStorageRemindService.query(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> storageRemindPage  = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(storageRemindPage);
    }

}
