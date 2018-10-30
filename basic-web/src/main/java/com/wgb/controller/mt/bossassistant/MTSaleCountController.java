package com.wgb.controller.mt.bossassistant;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitBossAssistantService;
import com.wgb.service.dubbo.urms.web.ApitBranchService;
import com.wgb.service.dubbo.urms.web.ApitPortalUserService;
import com.wgb.util.Contants;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 老板助手-销售统计服务
 * Created by lzy on 2018/8/6.
 */
@Controller
@RequestMapping("/boss/bossAssist")
public class MTSaleCountController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTSaleCountController.class);

    @Autowired
    private ApitBossAssistantService apitBossAssistantService;

    @Autowired
    private ApitBranchService apitBranchService;

    @Autowired
    private ApitPortalUserService apitPortalUserService;

    /**
     * 老板助手-查询日交易数据汇总,日交易流水
     * 按照所选日的销售时间倒序排序
     * @param request
     * @return
     */
    @RequestMapping("/queryDaySale")
    @ResponseBody
    public ZLResult queryDaySale(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult0 = new ZLRpcResult();
        ZLRpcResult zlRpcResult1 = new ZLRpcResult();

        try {
            //查询日交易统计
            zlRpcResult0 = apitBossAssistantService.queryDaySaleCount(params);
            //查询日交易流水
            zlRpcResult1 = apitBossAssistantService.queryDaySaleWater(params);

        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult0.success()) {
            throw new ServiceException(zlRpcResult0.getErrorMsg());
        }
        if (!zlRpcResult1.success()) {
            throw new ServiceException(zlRpcResult1.getErrorMsg());
        }

        //合并返回
        Map<String,Object> map = zlRpcResult0.getMap();
        if (MapUtils.isEmpty(map)) {
            map = new HashMap<>();
        }
        map.put("page",zlRpcResult1.getData());

        return ZLResult.Success(map);
    }

    /**
     * 老板助手-查询订单详情
     * @param request
     * @return
     */
    @RequestMapping("/queryBillDetail")
    @ResponseBody
    public ZLResult queryBillDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //查询订单详情
            zlRpcResult = apitBossAssistantService.queryBillDetail(params);

        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }


    /**
     * 老板助手-首页获取近6个月各月的交易情况
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySaleTotalForSixMonths")
    @ResponseBody
    public ZLResult querySaleTotalForSixMonths(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBossAssistantService.querySaleTotalForSixMonths(params);
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
     * 老板助手-查询月交易数据汇总
     * 按照所选月的日期倒序排序
     * @param request
     * @return
     */

    @RequestMapping("/querySaleTotalForMonth")
    @ResponseBody
    public ZLResult querySaleTotalForMonth(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBossAssistantService.querySaleTotalForMonth(params);
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
     * 老板助手-月交易日结对账查询
     *
     * @param request
     * @return
     */

    @RequestMapping("/querySaleTotalForDay")
    @ResponseBody
    public ZLResult querySaleTotalForDay(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBossAssistantService.querySaleTotalForDay(params);
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
     * 老板助手-月交易根据商品类别进行汇总
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySaleTotalByCategory")
    @ResponseBody
    public ZLResult querySaleTotalByCategory(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBossAssistantService.querySaleTotalByCategory(params);
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
     * 老板助手-根据商品品牌进行汇总
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySaleTotalByBrand")
    @ResponseBody
    public ZLResult querySaleTotalByBrand(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitBossAssistantService.querySaleTotalByBrand(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryAllBranchList")
    @ResponseBody
    public ZLResult queryAllBranchList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        List<Map<String, Object>> branchlist = new ArrayList<>();
        try {
            zlRpcResult = apitBranchService.queryBranchList(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        int ishead = MapUtils.getIntValue(params, Contants.LOGIN_USER_BRANCH_ISHEAD);
        if(ishead == 1){
            branchlist = zlRpcResult.getList();
            Map<String, Object> allbranch = new HashMap<>();
            allbranch.put("value",Contants.ALL_BRANCH_CODE);
            allbranch.put("text",Contants.ALL_BRANCH_NAME);
            allbranch.put("branchcode",Contants.ALL_BRANCH_CODE);
            allbranch.put("branchname",Contants.ALL_BRANCH_NAME);
            branchlist.add(0,allbranch);
            return ZLResult.Success(branchlist);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/querySaleBillListByToday")
    @ResponseBody
    public ZLResult querySaleBillListByToday(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //今日收款金额和笔数
            zlRpcResult = apitBossAssistantService.querySaleBillListByToday(params);

        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }

    @RequestMapping("/querySaleBillListByWeek")
    @ResponseBody
    public ZLResult querySaleBillListByWeek(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //查询近七天收款金额
            zlRpcResult = apitBossAssistantService.querySaleBillListByWeek(params);

        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }

    @RequestMapping("/queryCashierList")
    @ResponseBody
    public ZLResult queryCashierList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //查询收银员
            zlRpcResult = apitPortalUserService.getBranchUserList(params);

        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());

    }

}
