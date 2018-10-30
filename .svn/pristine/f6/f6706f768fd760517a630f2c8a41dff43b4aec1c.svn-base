package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.admin.ApiOrderService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 10057 on 2018/4/16.
 */
@Controller
@RequestMapping("/wxOrder")
public class MTWxOrderController extends MTBaseController {
    @Autowired
    ApiOrderService apiOrderService;


    /**
     * 查询单据
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryWxOrder")
    @ResponseBody
    public ZLResult queryWxOrderr(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        Map<String, Object> resultmap = new HashMap<>();

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult rpcResult = null;
        try {
            rpcResult = apiOrderService.getOrderList(params);
            Map<String,Object> rpcdata = rpcResult.getMap();
            resultmap.put("orderlist",MapUtils.getObject(rpcdata,"orderlist"));
            resultmap.put("branchinfo",MapUtils.getObject(rpcdata,"branchinfo"));
            rpcResult = apiOrderService.getAllStatusCountsForMachine(params);
            resultmap.put("orderstatus",rpcResult.getData());
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        if (!rpcResult.success()) {
            return ZLResult.Error(rpcResult.getErrorCode());
        }
        return ZLResult.Success(resultmap);
    }

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 提货
     * @Title
     */
    @RequestMapping("/confirmPick")
    @ResponseBody
    public ZLResult confirmPick(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = apiOrderService.confirmPick(params);
        if (zlRpcResult.success()) {
            ZLResult result = (ZLResult) zlRpcResult.getData();
            return result;
        }
        return ZLResult.Error(zlRpcResult.getErrorCode());
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 确认订单
     * @Title
     */
    @RequestMapping("/confirmOrder")
    @ResponseBody
    public ZLResult confirmOrder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = apiOrderService.confirmOrder(params);
        if (zlRpcResult.success()) {
            return ZLResult.Success();
        }
        return ZLResult.Error("订单异常");
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 取消订单
     * @Title
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public ZLResult cancelOrder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = apiOrderService.cancelOrder(params);
        if (zlRpcResult.success()) {
            return ZLResult.Success();
        }
        return ZLResult.Error(ServiceException.getMsg(zlRpcResult.getErrorCode()));
    }
}
