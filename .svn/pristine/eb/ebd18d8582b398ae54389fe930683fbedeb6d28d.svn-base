package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitDeviceGrantService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by whq on 2018/4/19.
 */
@Controller
@RequestMapping("/member/devicegrant")
public class MTDevicegrantController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTDevicegrantController.class);
    @Autowired
    private ApitDeviceGrantService apitDeviceGrantService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitDeviceGrantService.query(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitDeviceGrantService.saveOrUpdate(params);

        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){

            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

    /**
     *   添加品牌校验参数
     *   字段备注  brandname:品牌名称
     */
    private  void checkAddBrandParams(Map<String,Object> params){
        if(StringUtils.isEmpty(MapUtils.getString(params,"brandname"))){
            throw  new ServiceException("品牌名称不能为空");
        }
    }
}
