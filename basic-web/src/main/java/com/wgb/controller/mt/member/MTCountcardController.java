package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.admin.ApiMemberService;
import com.wgb.service.dubbo.mbms.web.ApitCountcardService;
import com.wgb.util.ExcelUtil;
import net.sf.json.JSONArray;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/member/countCard")
public class MTCountcardController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTCountcardController.class);

    @Autowired
    ApitCountcardService apitCountcardService;

    @Autowired
    private ApiMemberService apiMemberService;

    /**
     * 收银端接口-会员 - 查询次卡
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryCountCard")
    @ResponseBody
    public ZLResult queryCountCard(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.queryCountCard(params);
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
     * 收银端接口-会员 - 购买次卡
     *
     * @param request
     * @return
     */
    @RequestMapping("/buyCountCard")
    @ResponseBody
    public ZLResult buyCountCard(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String memberid = MapUtils.getString(params, "memberid");
        String countcardids = MapUtils.getString(params, "countcardids");
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(countcardids)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        List<Map<String, Object>> paydetails = getPayDetails(params);
        params.put("paydetails", paydetails);

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.buyCountCard(params);
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
     * @param params
     * @return
     */
    private List<Map<String, Object>> getPayDetails(Map<String, Object> params) {
        String paydetails = MapUtils.getString(params, "paydetails");

        if (StringUtils.isEmpty(paydetails)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        JSONArray paydetailjsonarr = JSONArray.fromObject(paydetails);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (int index = 0; index < paydetailjsonarr.size(); index++) {
            Map<String, Object> item = paydetailjsonarr.getJSONObject(index);
            result.add(item);
        }

        return result;
    }

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCountcardService.queryPageList(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();
        return ZLResult.Success(pageInfo);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCountcardService.saveOrUpdate(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCountcardService.delCountCard(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/queryCommissionDetailCountCard")
    @ResponseBody
    public ZLResult queryCommissionDetailCountCard(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCountcardService.queryCommissionDetailCountCard(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    /**
     * 计次卡-销售明细
     * @param request
     * @return
     */
    @RequestMapping("/queryCountcardSalesDetail")
    @ResponseBody
    public ZLResult queryCountcardSalesDetail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCountcardService.queryCountcardSalesDetail(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    /**
     * 计次卡-销售明细-导出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryCountcardSalesDetailExport")
    @ResponseBody
    public ZLResult queryCountcardSalesDetailExport(HttpServletRequest request, HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitCountcardService.queryCountcardSalesDetailList(params);
            String[] salesDetail = {"ticketcode", "cardname", "paymoney", "buynums", "buymoney",
                    "paytype", "membertelephone", "cashiername", "branchname", "whetheronline",
                    "buytime"
            };
            String[] salesDetailNames = {"订单编号", "卡名称", "卡单价", "数量", "销售金额",
                    "收款方式", "购买会员", "收银员", "销售门店", "销售渠道",
                    "销售时间"
            };
            String salesDetailName = "计次卡--销售明细_";
            ExcelUtil.exportExcel(zlRpcResult.getList(), salesDetail, salesDetailNames, salesDetailName, request, response);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

}
