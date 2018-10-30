package com.wgb.controller.mt.sales;

import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMemberAnalyzeService;
import com.wgb.util.Contants;
import com.wgb.util.DateUtils;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/sales/marketmember")
public class MTMarketMemberController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitMemberAnalyzeService apitMemberAnalyzeService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult queryMemberAnalyzPageList(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitMemberAnalyzeService.queryMemberAnalyzPageList(params);
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

    @RequestMapping("/sendAct")
    @ResponseBody
    public ZLResult sendAct(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "loginuserbranchcode");
        String membermodel = MapUtils.getString(params, "loginusermembermodel");
        //获取当前系统时间
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        SimpleDateFormat minute = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String thisday = day.format(new Date());
        String key = Contants.SEND_COUPON_TEMPLATE + shopcode + "_" + branchcode + "_" + membermodel + "_" + thisday;

        String listkey = RedisFactory.getDefaultClient().get(key);
        if (StringUtils.isNotEmpty(listkey)) {
            JSONArray dataJsonList = JSONArray.fromObject(listkey);
            List<String> dataList = dataJsonList;
                int listsize = dataJsonList.size();
                String thisminute = dataList.get(listsize - 1);
                Date enddate = null;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    enddate = format.parse(thisminute);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar endC = Calendar.getInstance();
                endC.setTime(enddate);
                endC.add(Calendar.MINUTE, +10);
                String endDate = DateUtils.formatDate2Str(endC.getTime(), DateUtils.C_TIME_PATTON_DEFAULT);
                String thatminute = minute.format(new Date());
                Date ed = null;
                Date tm = null;
                try {
                    ed = minute.parse(endDate);
                    tm = minute.parse(thatminute);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (ed.before(tm)) {
                    dataJsonList.add(thatminute);
                    RedisFactory.getDefaultClient().set(key, JSONArray.fromObject(dataJsonList).toString(), 86400);
                    //表示bt小于et
                } else {
                    return ZLResult.Error("发送活动成功后需要间隔10分钟才能发送第二次活动!");
                }

        } else {
            List<String> dataList = new ArrayList<>();
            String thatminute = minute.format(new Date());
            dataList.add(thatminute);
            RedisFactory.getDefaultClient().set(key, JSONArray.fromObject(dataList).toString(), 86400);
        }

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitMemberAnalyzeService.queryMemberAnalyzList(params);

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

     /*   List<Map<String, Object>> memberList = zlRpcResult.getList();
        params.put("memberList", memberList);
        apitCouponService.sendCouponTemplate(params);*/
        return ZLResult.Success();
    }

}
