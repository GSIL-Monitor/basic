package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/25 0025.
 */

@Controller
@RequestMapping("/xcxsc/commodity")
public class XCXCommodityController extends MBXCXBaseController {

    @Autowired
    private ApitCommodityService commodityService;


    /**
     * 获取全部商品列表
     */

    @RequestMapping("/list")
    @ResponseBody
    public ZLResult list(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = commodityService.list(params);
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

    //商品详情查询
    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult detail(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = commodityService.detail(params);
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
