package com.wgb.controller.mt.sales;

import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.salems.admin.ApiPrepaidcardService;
import com.wgb.service.dubbo.salems.web.ApitPrepaidCardService;
import com.wgb.util.ExcelReader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/sales/prepaidcard")
public class MTPrepaidcardController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitPrepaidCardService apitPrepaidCardService;

    @Autowired
    private ApiPrepaidcardService apiPrepaidcardService;

    /**
     * 收银端查询预付卡
     * @param request
     * @return
     */
    @RequestMapping("/queryPrepaidcard")
    @ResponseBody
    public ZLResult queryPrepaidcard(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPrepaidcardService.queryPrepaidcard(params);
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
     * 查询预付卡实体卡
     * @param request
     * @return
     */
    @RequestMapping("/queryPrepaidcardDetail")
    @ResponseBody
    public ZLResult queryPrepaidcardDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String cardcode = MapUtils.getString(params, "cardcode");
        String chipcode = MapUtils.getString(params, "chipcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        //卡号或者芯片号必须存在一个
        if(StringUtils.isEmpty(cardcode) && StringUtils.isEmpty(chipcode)){
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPrepaidcardService.queryPrepaidcardDetail(params);
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
     * 购买预付卡-实体卡
     * @param request
     * @return
     */
    @RequestMapping("/buyPrepaidcard")
    @ResponseBody
    public ZLResult buyPrepaidcard(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String cardcode = MapUtils.getString(params, "cardcode");
        String chipcode = MapUtils.getString(params, "chipcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        //卡号或者芯片号必须存在一个
        if(StringUtils.isEmpty(cardcode) && StringUtils.isEmpty(chipcode)){
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPrepaidcardService.buyPrepaidcard(params);
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
     * 预付卡支付
     * @param request
     * @return
     */
    @RequestMapping("/prepaidcardpay")
    @ResponseBody
    public ZLResult prepaidcardpay(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String cardcode = MapUtils.getString(params, "cardcode");
        String chipcode = MapUtils.getString(params, "chipcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        //卡号或者芯片号必须存在一个
        if(StringUtils.isEmpty(cardcode) && StringUtils.isEmpty(chipcode)){
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPrepaidcardService.prepaidcardpay(params);
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
     * 预付卡退款
     * @param request
     * @return
     */
    @RequestMapping("/prepaidcardRefund")
    @ResponseBody
    public ZLResult prepaidcardRefund(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String businesscode = MapUtils.getString(params, "businesscode");
        String refundamount = MapUtils.getString(params, "refundamount");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        //卡号或者芯片号必须存在一个
        if(StringUtils.isEmpty(businesscode)){
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if(StringUtils.isEmpty(refundamount)){
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiPrepaidcardService.prepaidcardRefund(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryPagePrepaidCard")
    @ResponseBody
    public ZLResult queryPagePrepaidCard(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();


        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitPrepaidCardService.queryPagePrepaidCard(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
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

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();


        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            String starttime = MapUtils.getString(params, "starttime");
            String endtime = MapUtils.getString(params, "endtime");
            if (!checkDataStartTime(starttime)) {
                throw new ServiceException("开始时间不可选择之前的日期");
            }
            if (!checkDataEndTime(endtime, starttime)) {
                throw new ServiceException("开始时间不可大于结束时间");
            }
            //系统调用
            zlRpcResult = apitPrepaidCardService.saveOrUpdatePrepaidCard(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    public boolean checkDataStartTime(String starttime) {
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

    private boolean checkDataEndTime(String endtime, String starttime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startdate = null;
        Date enddate = null;

        try {
            startdate = format.parse(starttime);
            enddate = format.parse(endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (enddate.compareTo(startdate) != 0) {
            if (enddate.before(startdate)) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/delPrepaidCard")
    @ResponseBody
    public ZLResult delPrepaidCard(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();


        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkDelPrepaidCardParams(params);
            //系统调用
            zlRpcResult = apitPrepaidCardService.delPrepaidCard(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }


    @RequestMapping("/queryWebPrepaidCardDetail")
    @ResponseBody
    public ZLResult queryPrepaidCardDetail(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            checkPrepaidCardDetailParams(params);
            //系统调用
            zlRpcResult = apitPrepaidCardService.queryPrepaidCardDetail(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
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

    /**
     * 预付卡-实体卡导入
     *
     * @param file
     * @param request
     * @param response
     */
    @RequestMapping("/cardImport")
    public void prepaidCardImport(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {


        //获取入参数据
        Map<String, Object> params =  getParams();
        //返回结果
        Map<String, Object> result = null;

        try {

            //查询预付卡信息
            ZLRpcResult rpcResult = apitPrepaidCardService.getPrepaidCardDetail(params);
            Map<String, Object> prepaidcradMap = rpcResult.getMap();
            if (MapUtils.getString(prepaidcradMap, "required").equals("1")) {
                throw new ServiceException("系统内置数据无法导入！");
            }
            if (MapUtils.isEmpty(prepaidcradMap)) {
                throw new ServiceException("此预付卡不存在！");
            }

            //获取预付卡参数
            params = getParams(params, prepaidcradMap);

            // 字段名数组
            String[] columns = {"cardcode", "chipcode"};

            // 模板字段名称
            String[] columnNames = {"卡号", "芯片ID"};

            //文件数据
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

            try {
                //将流文件取出成List对象
                dataList = ExcelReader.read(file.getInputStream(), columns, columnNames, 2);
            } catch (Exception e) {
                throw new ServiceException("请按照正确模板格式导入!");
            }

            //校验导入参数
            checkImportData(dataList);
            params.put("fileDataList", dataList);
            //预付卡实体卡导入
            ZLRpcResult rpcResult1 = apitPrepaidCardService.prepaidCardImport(params);
            result = rpcResult1.getMap();
        } catch (ServiceException e) {

            //业务异常
            result = new HashMap<String, Object>();
            result.put("errorMsg", e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {

            //系统处理异常
            result = new HashMap<String, Object>();
            result.put("errorMsg", "导入异常");
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException var7) {
            this.logger.error("与客户端通讯异常：" + var7.getMessage(), var7);
            var7.printStackTrace();
        }

    }

    /**
     * 获取预付卡参数
     *
     * @param params
     * @param prepaidcradMap
     * @return
     */
    private Map<String, Object> getParams(Map<String, Object> params, Map<String, Object> prepaidcradMap) {
        params.put("prepaidcardname", MapUtils.getString(prepaidcradMap, "prepaidcardname", ""));
        params.put("branchname", MapUtils.getString(prepaidcradMap, "branchname", ""));
        params.put("prepaidcardname", MapUtils.getString(prepaidcradMap, "prepaidcardname", ""));
        params.put("prepaidcardamount", MapUtils.getString(prepaidcradMap, "prepaidcardamount", ""));
        params.put("cardbalance", MapUtils.getString(prepaidcradMap, "prepaidcardamount", ""));
        params.put("consumedamount", "0");
        return params;
    }

    /**
     * 格式化导入数据
     *
     * @param dataList
     */
    private void checkImportData(List<Map<String, Object>> dataList) {

        if (CollectionUtils.isNotEmpty(dataList)) {

            //遍历每行数据校验参数是否可以导入
            for (Map<String, Object> data : dataList) {
                String cardcode = MapUtils.getString(data, "cardcode", "");
                String chipcode = MapUtils.getString(data, "chipcode", "");
                if (StringUtils.isEmpty(cardcode)) {
                    throw new ServiceException("cardcode对应卡号必须填写！");
                }
                if (StringUtils.isEmpty(chipcode)) {
                    throw new ServiceException("chipcode对应卡号必须填写！");
                }
            }
        } else {
            throw new ServiceException("请正确填写预付卡数据再上传");
        }
    }

    /**
     * 添加预付卡校验参数
     * 字段备注预付卡编码
     */
    private void checkDelPrepaidCardParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "prepaidcardcode"))) {
            throw new ServiceException("操作异常");
        }
    }

    /**
     * 添加预付卡校验参数
     * 字段备注预付卡编码
     */
    private void checkPrepaidCardDetailParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "prepaidcardcode"))) {
            throw new ServiceException("操作异常");
        }
    }
}
