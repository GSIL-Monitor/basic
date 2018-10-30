package com.wgb.controller.mt.sales;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.salems.web.ApitRechargeRuleService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/sales/rechargerule")
public class MTRechargeRuleController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitRechargeRuleService apitRechargeRuleService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult queryPageList(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();


        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitRechargeRuleService.queryPageRechargeRule(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();


        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            String expiretime = MapUtils.getString(params, "expiretime");

            if (!checkDataTime(expiretime)) {
                zlRpcResult.setErrorMsg("截止日期不可选择之前的日期");
            }
            //系统调用
            zlRpcResult = apitRechargeRuleService.saveOrUpdateRechargeRule(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    public boolean checkDataTime(String starttime) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newdatetime = dateFormat.format(date);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startdate = null;
        Date newdate = null;

        try {
            startdate = format.parse(starttime);
            newdate = format.parse(newdatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startdate.compareTo(newdate) != 0) {
            if (startdate.before(newdate)) {
                return false;
            }
        }
        return true;

    }

    @RequestMapping("/delRechargeRule")
    @ResponseBody
    public ZLResult delRechargeRule(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();



        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkDelRechargeRuleParams(params);
            //系统调用
            zlRpcResult = apitRechargeRuleService.delRechargeRule(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    private void checkDelRechargeRuleParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "id"))) {
            throw new ServiceException("操作异常");
        }
    }


}
