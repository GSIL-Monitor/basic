package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/5/14.
 */
@Controller
@RequestMapping("/wxshop/category")
public class MTWxCategoryController extends MTBaseController {
    @Autowired
    private ApitWxCategoryService apitWxCategoryService;


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 类别列表
     * @Title
     */
    @RequestMapping("/CategoryList")
    @ResponseBody
    public ZLResult categoryList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCategoryService.categoryList(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 修改类别
     * @Title
     */
    @RequestMapping("/updatecategory")
    @ResponseBody
    public ZLResult updateCategory(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCategoryService.updateCategory(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();

    }
}
