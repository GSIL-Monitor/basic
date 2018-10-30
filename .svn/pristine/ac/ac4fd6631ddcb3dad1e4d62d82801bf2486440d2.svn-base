package com.wgb.controller.mt.common;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lzy on 2017/2/6.
 */
@Controller
@RequestMapping("/common/file")
public class MTFileController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTFileController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping("/postObjectPolicy")
    @ResponseBody
    public ZLResult postObjectPolicy(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        Map<String, Object> postObjectPolicy = fileService.postObjectPolicy(params);
        return ZLResult.Success(postObjectPolicy);
    }

    /**
     * 供应链--上传文件
     * @param request
     * @return
     */
    @RequestMapping("/postObjectPolicyForSCP")
    @ResponseBody
    public ZLResult postObjectPolicyForSCP(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        Map<String, Object> postObjectPolicyForSCP = fileService.postObjectPolicyForSCP(params);
        return ZLResult.Success(postObjectPolicyForSCP);
    }
}
