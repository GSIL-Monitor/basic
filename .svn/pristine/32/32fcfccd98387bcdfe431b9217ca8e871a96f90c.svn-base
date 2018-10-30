package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitXCXHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

@Controller
@RequestMapping("/xcxsc/home")
public class XCXHomeController extends MBXCXBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitXCXHomeService apitXcxHomeService;
    //首页
    @RequestMapping("/home")
    @ResponseBody
    public ZLResult homes(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();

        //获取入参数据
        Map<String, Object> params = getXcxParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitXcxHomeService.getHomeData(params);

        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        long endTime = System.currentTimeMillis();
        logger.info("getHomeData 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * 获取全部商品列表
     * @param request
     * @return
     */

    @RequestMapping("/hotGoods")
    @ResponseBody
    public ZLResult list(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getXcxParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitXcxHomeService.getHomeCommodityList(params);

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

}

