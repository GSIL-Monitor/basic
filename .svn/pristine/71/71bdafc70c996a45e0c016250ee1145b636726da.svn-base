package com.wgb.controller.mt.passanger;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.controller.mt.pay.MTAliPayController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitBillCountForPassengerFlowService;
import com.wgb.service.dubbo.osrms.web.ApitPassengerService;
import org.apache.commons.collections.MapUtils;
import org.omg.CORBA.OBJ_ADAPTER;
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

@Controller
@RequestMapping("/boss/passanger")
public class MTPassangerController  extends MTBaseController {
    public static final Logger logger = LoggerFactory.getLogger(MTPassangerController.class);
    @Autowired
    private ApitPassengerService apitPassengerService;
    @Autowired
    private ApitBillCountForPassengerFlowService apitBillCountForPassengerFlowService;
    @RequestMapping("/query/passangerFordays")
    @ResponseBody
    public ZLResult passangerForhours(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        List<Map<String,Object>>  list=new ArrayList<>();
        List<Map<String,Object>>  listSum=new ArrayList<>();
        List<Map<String,Object>>  listBillCountForDays=new ArrayList<>();
        List<Map<String,Object>>  listBillCountForHours=new ArrayList<>();
        ZLRpcResult zlRpcResult = null;
        try {
                zlRpcResult=apitPassengerService.passangerForhours(params);
        }
        catch (Exception e){
            e.getStackTrace();
            ZLResult.Error("系统异常");
          logger.error("调用rpc查询日客流量错误",e);
        }

        return ZLResult.Success(zlRpcResult.getData());

    }
    @RequestMapping("/query/passangerFormonths")
    @ResponseBody
    public ZLResult passangerFormonths(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        String saletime=MapUtils.getString(params,"saletime").replace("-","");
        String month=null;
       if(saletime!=null){
           if(saletime.length()==6){
               month=saletime.substring(4,6).replace("0","");
           }
           else if(saletime.length()==5)
           {
               month=saletime.substring(4,5).replace("0","");
           }
           String year=saletime.substring(0,4);
           String newsaletime=MapUtils.getString(params,"saletime")+"-01";
           params.put("month",month);
           params.put("year",year);
           params.put("saletime",newsaletime);
       }
        List<Map<String,Object>>  list=new ArrayList<>();
        List<Map<String,Object>>  listSum=new ArrayList<>();
        Map<String ,Object>  listBillCountForDays=new HashMap<>();
        List<Map<String,Object>>  listBillCountFormonth=new ArrayList<>();
        ZLRpcResult zlRpcResult = null;

        try {
            zlRpcResult=apitPassengerService.passangerForMonths(params);
           // zlRpcResultForSum=apitPassengerService.passangerSumForMonth(params);
            //BillCountForDays=apitBillCountForPassengerFlowService.queryBillCountForDays(params);
            //BillCountFormonth=apitBillCountForPassengerFlowService.queryBillCountForMonths(params);
        }
        catch (Exception e){
            ZLResult.Error("系统异常");
            logger.error("调用rpc查询日月客流量错误",e);
        }
        //list=(List<Map<String,Object>>) zlRpcResult.getData();
        //listSum=(List<Map<String,Object>>) zlRpcResultForSum.getData();
       // listBillCountForDays=(Map<String ,Object>) BillCountForDays.getData();
       // listBillCountFormonth=(List<Map<String,Object>>) BillCountFormonth.getData();
       /* if(listSum!=null){
            list.addAll(listSum);
        }
        if(listBillCountForDays!=null){
            list.add(listBillCountForDays);
        }*/
    /*    if(listBillCountFormonth!=null){
            list.addAll(listBillCountFormonth);
        }*/

        return ZLResult.Success(zlRpcResult.getData());
    }
    @RequestMapping("/query/passangerForQuater")
    @ResponseBody
    public ZLResult passangerForQuater(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult = null;
        ZLRpcResult zlRpcResultForSum = null;
        List<Map<String,Object>>  list=new ArrayList<>();
        List<Map<String,Object>>  listSum=new ArrayList<>();

        Map<String,Object> monthMap=new HashMap<>();
        String quarter=MapUtils.getString(params,"quarter");
        String shopcode=MapUtils.getString(params,"shopcode");
        String branchcode=MapUtils.getString(params,"branchcode");
        monthMap.put("shopcode",shopcode);
        monthMap.put("branchcode",branchcode);
        monthMap.put("year",MapUtils.getString(params,"year"));
        List<String> monthList=new ArrayList<>();
        if("1".equals(quarter)){
            monthList.add("1");
            monthList.add("2");
            monthList.add("3");
            try {
                monthMap.put("monthlist",monthList);
                zlRpcResult=apitPassengerService.passangerSumForQuarter(params);
                zlRpcResultForSum=apitPassengerService.passangerForMonthList(monthMap);
            }
            catch (Exception e){
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            list=(List<Map<String,Object>>) zlRpcResult.getData();
            listSum=(List<Map<String,Object>>) zlRpcResultForSum.getData();
            if(listSum!=null){
                list.addAll(listSum);
            }
        }
        if("2".equals(quarter)){
            monthList.add("4");
            monthList.add("5");
            monthList.add("6");
            try {
                monthMap.put("monthlist",monthList);
                zlRpcResult=apitPassengerService.passangerSumForQuarter(params);
                zlRpcResultForSum=apitPassengerService.passangerForMonthList(monthMap);
            }
            catch (Exception e){
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            list=(List<Map<String,Object>>) zlRpcResult.getData();
            listSum=(List<Map<String,Object>>) zlRpcResultForSum.getData();
            if(listSum!=null){
                list.addAll(listSum);
            }
        }
        if("3".equals(quarter)){
            monthList.add("7");
            monthList.add("8");
            monthList.add("9");
            try {
                monthMap.put("monthlist",monthList);
                zlRpcResult=apitPassengerService.passangerSumForQuarter(params);
                zlRpcResultForSum=apitPassengerService.passangerForMonthList(monthMap);
            }
            catch (Exception e){
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            list=(List<Map<String,Object>>) zlRpcResult.getData();
            listSum=(List<Map<String,Object>>) zlRpcResultForSum.getData();
            if(listSum!=null){
                list.addAll(listSum);
            }
        }
        if("4".equals(quarter)){
            monthList.add("10");
            monthList.add("11");
            monthList.add("12");
            try {
                monthMap.put("monthlist",monthList);
                zlRpcResult=apitPassengerService.passangerSumForQuarter(params);
                zlRpcResultForSum=apitPassengerService.passangerForMonthList(monthMap);
            }
            catch (Exception e){
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
            list=(List<Map<String,Object>>) zlRpcResult.getData();
            listSum=(List<Map<String,Object>>) zlRpcResultForSum.getData();
            if(listSum!=null){
                list.addAll(listSum);
            }
        }
        return ZLResult.Success(list);

    }
    @RequestMapping("/query/passangerForYear")
    @ResponseBody
    public ZLResult passangerForYear(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        List<Map<String,Object>>  list=new ArrayList<>();
        List<Map<String,Object>>  listSum=new ArrayList<>();
        Map<String,Object>  listBillCountForyears=new HashMap<>();
        ZLRpcResult zlRpcResult = null;
     /*   ZLRpcResult zlRpcResultForSum = null;
        ZLRpcResult BillCountForYear = null;*/
        try {
            zlRpcResult=apitPassengerService.passangerForYear(params);
           // zlRpcResultForSum=apitPassengerService.passangerForQuarterList(params);
          //  BillCountForYear=apitBillCountForPassengerFlowService.queryBillCountForQuarterAndYear(params);
        }
        catch (Exception e){
            ZLResult.Error("系统异常");
            logger.error("调用rpc查询年客流量错误",e);
        }
        Map<String,Object> map=zlRpcResult.getMap();
       // listSum=(List<Map<String,Object>>) zlRpcResultForSum.getData();
       // listBillCountForyears=(Map<String,Object>) BillCountForYear.getData();
        //if(listSum!=null){
        //    list.addAll(listSum);
      //  }
       // if(listBillCountForyears!=null){
         //   list.add(listBillCountForyears);
       // }

        return ZLResult.Success(map);

    }

}
