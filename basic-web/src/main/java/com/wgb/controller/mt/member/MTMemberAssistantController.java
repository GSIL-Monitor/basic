package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMemberAssistantService;
import com.wgb.util.ExcelUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 曾经沧海 on 2018/6/1.
 */
@Controller
@RequestMapping("/member/assistant")
public class MTMemberAssistantController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberAssistantController.class);

    @Autowired
    private ApitMemberAssistantService apitMemberAssistantService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult =apitMemberAssistantService.queryPageList(params);
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


    @RequestMapping("/getAssistantBindRecord")
    @ResponseBody
    public ZLResult getAssistantBindRecord(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            //系统调用
            zlRpcResult =apitMemberAssistantService.getAssistantBindRecord(params);
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
            //系统调用
            zlRpcResult =apitMemberAssistantService.saveOrUpdate(params);
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

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberAssistantService.delAssistant(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/manageExport")
    public ZLResult manageExport(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberAssistantService.queryAssistantListExport(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> manageExportList = checkRpcResult(zlRpcResult,params);
        // 字段名数组
        String[] assistants = {"assistantcode", "name", "account", "saleassistantcode",
                "branchname", "status", "isonwork", "membernums", "leavedate", "createtime"};
        // 字段名数组
        String[] assistantNames = {"导购员编号", "姓名", "账号", "营销助手码",
                "所属门店", "状态", "是否在职", "会员数", "离职时间", "创建时间"};
        String assistantName = "导购员资料表";
        ExcelUtil.exportExcel(manageExportList, assistants, assistantNames, assistantName, request, response);

        return ZLResult.Success();
    }

    @RequestMapping("/queryCommissionManagementAssistant")
    @ResponseBody
    public ZLResult queryCommissionManagementAssistant(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitMemberAssistantService.queryCommissionManagementAssistant(params);
        } catch (Exception ex){
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>)zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    private List<Map<String, Object>> checkRpcResult(ZLRpcResult rpcResult,Map<String, Object>params) {

        List<Map<String, Object>> assistantList = (List<Map<String, Object>>) rpcResult.getData();
        for (Map<String, Object> assistant : assistantList) {
            //购买明细
            if ("0".equals(MapUtils.getString(assistant, "status"))) {
                assistant.put("status", "停用");
            } else {
                assistant.put("status", "正常");
            }

            String isonwork =MapUtils.getString(assistant,"isonwork");
            if ("1".equals(isonwork)) {
                assistant.put("isonwork","在职");
            }else {
                assistant.put("isonwork","离职");
                assistant.put("saleassistantcode","");
            }

        }
        return assistantList;
    }

}
