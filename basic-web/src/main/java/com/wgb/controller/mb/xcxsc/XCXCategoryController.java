package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCategoryService;
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
 * Created by WuYoung on 2018/3/6.
 * 商品按分类查询
 */

@Controller
@RequestMapping("xcxsc/category")
public class XCXCategoryController extends MBXCXBaseController {

    @Autowired
    private ApitCategoryService categoryService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //查询所有的商品种类表
    @RequestMapping("/categorylist")
    @ResponseBody
    public ZLResult categorylist(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = categoryService.categorylist(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }
}
