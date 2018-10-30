package com.wgb.controller.mt.common;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStmsCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/common/commodity")
public class MTSelectCommodityController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTSelectCommodityController.class);
    @Autowired
    private ApitStmsCommonService apitStmsCommonService;

    /*
   選擇商品
    */
    @RequestMapping("/toChoose")
    @ResponseBody
    public ZLResult toChoose(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitStmsCommonService.toChooseCommodity(params);
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


    @RequestMapping("/queryCommodityBatchInfo")
    @ResponseBody
    public ZLResult queryCommodityBatchInfo(HttpServletRequest request){
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitStmsCommonService.queryCommodityBatchInfo(params);
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
}
