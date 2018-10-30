package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxShopMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/5/14.
 */
@Controller
@RequestMapping("/wxshop/member")
public class MTWxShopMemberController extends MTBaseController {
    @Autowired
    private ApitWxShopMemberService apitWxShopMemberService;

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 查询会员信息
     * @Title
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxShopMemberService.query(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }


}
