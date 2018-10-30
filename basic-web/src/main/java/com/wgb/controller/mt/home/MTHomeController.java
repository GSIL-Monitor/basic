package com.wgb.controller.mt.home;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitHomeService;
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
@RequestMapping("/dcms/home")
public class MTHomeController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTHomeController.class);

    @Autowired
    private ApitHomeService apitHomeService;

    /**
     * 零售2.0首页查询
     * @param request
     * @return
     */
    @RequestMapping("queryForIndex")
    @ResponseBody
    public ZLResult queryForIndex(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitHomeService.queryForIndex(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success(zlRpcResult.getMap());
    }
}
