package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMemberTagsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/member/tags")
public class MTMemberTagsController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberTagsController.class);

    @Autowired
    ApitMemberTagsService apitMemberTagsService;

    @RequestMapping("/updateMemberTags")
    @ResponseBody
    public ZLResult updateMemberTags(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberTagsService.updateMemberTags(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    @RequestMapping("/queryMemberTags")
    @ResponseBody
    public ZLResult queryMemberBirth(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = apitMemberTagsService.queryMemberTags(params);
        return parseRpcResultForData(rpcResult);
    }
}
