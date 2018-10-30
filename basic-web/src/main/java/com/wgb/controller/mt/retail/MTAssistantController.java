package com.wgb.controller.mt.retail;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitAssistantService;
import com.wgb.util.ExcelUtil;
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
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/retail/assistant")
public class MTAssistantController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitAssistantService apitAssistantService;

    /**
     * 查询导购员page
     * @param request
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitAssistantService.queryAssistant(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

    /**
     * 增加导购员
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ZLResult add(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        //校验参数
        checkAddAssistantParams(params);

        try {

            //系统调用
            zlRpcResult = apitAssistantService.addAssistant(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return  ZLResult.Success();
    }

    /**
     *   增加导购员校验参数
     *   字段备注  name:导购员名称
     */
    private  void checkAddAssistantParams(Map<String,Object> params){
        if(StringUtils.isEmpty(MapUtils.getString(params,"name"))){
            throw  new ServiceException("导购员名称不能为空");
        }
    }

    /**
     * 修改导购员
     * @param request
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        //校验参数
        checkUpdateAssistantParams(params);

        try {

            //系统调用
            zlRpcResult = apitAssistantService.updateAssistant(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success();
    }

    /**
     *   修改导购员校验参数
     *   字段备注  id:导购员id
     */
    private  void checkUpdateAssistantParams(Map<String,Object> params){
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            throw  new ServiceException("导购员id不能为空");
        }
    }

    /**
     * 删除导购员
     * @param request
     * @return
     */
    @RequestMapping("/delObject")
    @ResponseBody
    public ZLResult delObject(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        //校验参数
        checkDeleteAssistantParams(params);

        try {

            //系统调用
            zlRpcResult = apitAssistantService.deleteAssistant(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success();
    }

    /**
     *   删除导购员校验参数
     *   字段备注  id:导购员id
     */
    private  void checkDeleteAssistantParams(Map<String,Object> params){
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            throw  new ServiceException("导购员id不能为空");
        }
    }



    /**
     * 导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportOut")
    public void exportOut(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitAssistantService.getExportAssistants(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        List<Map<String, Object>> assistantList = zlRpcResult.getList();

        //处理商品列表 - 导出到excel
        // 字段名数组
        String[] columns = {"assistantcode", "branchname", "name", "status", "remark"};

        // 字段名数组
        String[] columnNames = {"导购员编号", "门店名称", "导购员名称", "状态", "备注"};

        String fileName = "导购员资料";

        ExcelUtil.exportExcel(assistantList, columns, columnNames, fileName, request, response);
    }


}
