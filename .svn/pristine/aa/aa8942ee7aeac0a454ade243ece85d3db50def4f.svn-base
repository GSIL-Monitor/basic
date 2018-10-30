package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiCardRechargeOrderService;
import com.wgb.service.dubbo.dcms.admin.ApiRetailRecordService;
import com.wgb.service.dubbo.mbms.admin.ApiMemberService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2017/3/13.
 */
@Controller
@RequestMapping("/retail")
public class MTRetailRecordController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiRetailRecordService apiRetailRecordService;

    @Autowired
    private ApiMemberService apiMemberService;

    @Autowired
    private ApiCardRechargeOrderService apiCardRechargeOrderService;


    /**
     * 零售 - 查询商品流水
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySaleRecord")
    @ResponseBody
    public ZLResult querySaleRecord(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiRetailRecordService.querySaleRecord(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 零售 - 查询收银流水
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPayRecord")
    @ResponseBody
    public ZLResult queryPayRecord(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult= null;
        try {
            zlRpcResult = apiRetailRecordService.queryPayRecord(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 零售 - 查询会员充值日账记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryDayRecharge")
    @ResponseBody
    public ZLResult queryDayRecharge(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String deviceuniquecode = MapUtils.getString(params, "deviceuniquecode");
        String starttime = MapUtils.getString(params, "starttime");
        String endtime = MapUtils.getString(params, "endtime");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(deviceuniquecode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(starttime)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(endtime)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

       ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.queryDayRecharge(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 零售 - 收银端日结统计
     * 支付方式明细统计
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryDaySaleTotal")
    @ResponseBody
    public ZLResult queryDaySaleTotal(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult0= null;
        ZLRpcResult zlRpcResult1= null;
        try {
            zlRpcResult0 = apiRetailRecordService.queryDaySaleTotal(params);
            zlRpcResult1 = apiRetailRecordService.queryTotalByPayType(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult0.success()){
            throw  new ServiceException(zlRpcResult0.getErrorMsg());
        }
        if(!zlRpcResult1.success()){
            throw  new ServiceException(zlRpcResult1.getErrorMsg());
        }
        Map<String, Object> item =  new HashMap<>();
        item.put("dayTotal",zlRpcResult0.getData());
        item.put("payTotal",zlRpcResult1.getData());
        return ZLResult.Success(item);
    }

    /**
     * 收银端 - 交易查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryBusinessRecord")
    @ResponseBody
    public ZLResult queryBusinessRecord(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult= null;
        try {
            zlRpcResult = apiRetailRecordService.queryBusinessRecord(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     *  收银端--退货查询销售流水
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySaleRecordForReturn")
    @ResponseBody
    public ZLResult querySaleRecordForReturn(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult= null;
        try {
            zlRpcResult = apiRetailRecordService.querySaleRecordForReturn(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
