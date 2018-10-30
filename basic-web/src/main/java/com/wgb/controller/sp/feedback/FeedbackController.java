package com.wgb.controller.sp.feedback;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends SPBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
    @Autowired
    private ApitFeedbackService apitFeedbackService;

    @RequestMapping("/saveFeedback")
    @ResponseBody
    public ZLResult saveFeedback(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitFeedbackService.saveFeedback(params);
            result = parseRpcResultForMsg(rpcResult);
        }catch (Exception ex){
            LOGGER.error("保存意见反馈信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员!");
        }
        return result;
    }
}
