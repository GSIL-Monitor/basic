
package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitBargainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by 686 on 2018/7/6.
 * 砍价
 */

@Controller
@RequestMapping("/xcxsc/bargain")
public class XCXBargainController extends MBXCXBaseController {

    @Autowired
    private ApitBargainService bargainService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //砍价列表
    @RequestMapping("/queryBargainPageList")
    @ResponseBody
    public ZLResult queryBargainPageList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = bargainService.queryBargainPageList(params);
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

    //发起砍价
    @RequestMapping("/startBargain")
    @ResponseBody
    public ZLResult startBargain(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = bargainService.startBargain(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    //砍价详情
    @RequestMapping("/getBargainRegistrationDetail")
    @ResponseBody
    public ZLResult getBargainRegistrationDetail(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = bargainService.getBargainRegistrationDetail(params);
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

    //帮忙砍价
    @RequestMapping("/helpBargain")
    @ResponseBody
    public ZLResult helpBargain(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = bargainService.helpBargain(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }
}


