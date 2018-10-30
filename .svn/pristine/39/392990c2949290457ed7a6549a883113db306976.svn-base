package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * Created by Administrator on 2017/5/25 0025.
 */

@Controller
@RequestMapping("/xcxsc/template")
public class XCXTemplateController extends MBXCXBaseController {

    @Autowired
    private ApitTemplateService templateService;


    /**
     * 获取xcxopenid
     */

    @RequestMapping("/sendTemplate")
    @ResponseBody
    public ZLResult sendTemplate() {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = templateService.sendTemplate(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
