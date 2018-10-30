package com.wgb.controller.mt.requisition;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.admin.ApiCrossBranchRequisitionService;
import com.wgb.util.Contants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/requisition/requisition")
public class MTRequisitionController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiCrossBranchRequisitionService apiCrossBranchRequisitionService;

    /*
     * 获取本门店库存不足的商品列表
     * */
    @RequestMapping("/getCommoditysInBranch")
    @ResponseBody
    public ZLResult getCommoditysInBranch(HttpServletRequest request) {

        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.getCommoditysInBranch(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /*
     * 获取跨店的商品数据
     * */
    @RequestMapping("/getCommodityList")
    @ResponseBody
    public ZLResult getCommodityList(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String barcode = MapUtils.getString(params, "barcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(barcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.getCommodityList(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }


    /*
     * 跨店的销售申请
     * */
    @RequestMapping("/saleApply")
    @ResponseBody
    public ZLResult saleApply(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String billcode = MapUtils.getString(params, "billcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;
        try {
            //解析requisitionbill到params
            // formatRequisitionBill(params);
            zlRpcResult = apiCrossBranchRequisitionService.saleApply(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /*
        * 跨店申请商品信息的状态查询
        * */
    @RequestMapping("/queryRequisitionList")
    @ResponseBody
    public ZLResult queryRequisitionList(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.queryRequisitionList(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /*
    * 更新调拨单状态
    * */
    @RequestMapping("/updateRequisitionBill")
    @ResponseBody
    public ZLResult updateRequisitionBill(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String billcode = MapUtils.getString(params, "billcode");
        String ticketcode = MapUtils.getString(params, "ticketcode");
        String status = MapUtils.getString(params, "status");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(status)) {
            if (StringUtils.isEmpty(ticketcode)) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }
        }
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.updateRequisitionBill(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/queryRequisitionCount")
    @ResponseBody
    public ZLResult queryRequisitionCount(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.queryRequisitionCount(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /*
   * 门店联系方式查询
   * */
    @RequestMapping("/queryBranchInfo")
    @ResponseBody
    public ZLResult queryBranchInfo(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.queryBranchInfo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /*
* 门店联系方式查询
* */
    @RequestMapping("/cancelRequisitionBill")
    @ResponseBody
    public ZLResult cancelRequisitionBill(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        String billcode = MapUtils.getString(params, "billcode");
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(branchcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(billcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiCrossBranchRequisitionService.cancelRequisitionBill(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 解析requisitionbill
     *
     * @param params
     */
    private void formatRequisitionBill(Map<String, Object> params) {

        String requisitionbill = MapUtils.getString(params, "requisitionbill");
        if (StringUtils.isEmpty(requisitionbill)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        try {
            //调拨单行数据
            JSONArray requisitionbilljsonarr = JSONArray.fromObject(requisitionbill);

            List<Map<String, Object>> requisitionBillList = new ArrayList<Map<String, Object>>();
            for (int index = 0; index < requisitionbilljsonarr.size(); index++) {
                JSONObject requisitionbilljson = requisitionbilljsonarr.getJSONObject(index);
                Map<String, Object> requisitionbillMap = requisitionbilljson;
                requisitionbillMap.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID, ""));
                requisitionBillList.add(requisitionbillMap);
            }
            params.put("requisitionbill", requisitionBillList);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.CODE_23101);
        }
    }
}
