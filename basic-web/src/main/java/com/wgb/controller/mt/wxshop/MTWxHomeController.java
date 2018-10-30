package com.wgb.controller.mt.wxshop;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.admin.ApiBranchService;
import com.wgb.service.dubbo.wxms.web.ApitWxHomeCategoryService;
import com.wgb.service.dubbo.wxms.web.ApitWxHomeNoticeService;
import com.wgb.service.dubbo.wxms.web.ApitWxHomePlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 10057 on 2018/5/14.
 */
@Controller
@RequestMapping("/wxshop/home")
public class MTWxHomeController extends MTBaseController {
    @Autowired
    private ApitWxHomePlayService apitWxHomePlayService;

    @Autowired
    private ApitWxHomeNoticeService apitWxHomeNoticeService;

    @Autowired
    private ApitWxHomeCategoryService apitWxHomeCategoryService;

    @Autowired
    private ApiBranchService apiBranchService;

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 查询公告
     * @Title queryNotice
     */
    @RequestMapping("/queryNotice")
    @ResponseBody
    public ZLResult queryNotice(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomeNoticeService.queryNotice(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("查询公告 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 添加或者修改公告
     * @Title
     */
    @RequestMapping("/updateOrAddNotice")
    @ResponseBody
    public ZLResult updateNotice(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomeNoticeService.updateNotice(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("添加或者修改公告 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success();
    }

    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 删除轮播
     * @Title
     */
    @RequestMapping("/deleteNotice")
    @ResponseBody
    public ZLResult deleteNotice(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomeNoticeService.deleteNotice(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("删除轮播 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success();
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 查询轮播
     * @Title queryPlay
     */
    @RequestMapping("/queryPlay")
    @ResponseBody
    public ZLResult queryPlay(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomePlayService.queryPlay(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("查询轮播 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 新增或修改轮播
     * @Title
     */
    @RequestMapping("/addOrUpdate")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomePlayService.addOrUpdate(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("新增或修改轮播 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success();
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 删除轮播
     * @Title
     */
    @RequestMapping("/delHome")
    @ResponseBody
    public ZLResult delHome(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomePlayService.delHome(params);
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


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 查询类别
     * @Title querycategory
     */
    @RequestMapping("/querycategory")
    @ResponseBody
    public ZLResult querycategory(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomeCategoryService.querycategory(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("查询类别 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 添加或修改商品类别
     * @Title
     */
    @RequestMapping("/addorupdateHomeCategory")
    @ResponseBody
    public ZLResult addCategory(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomeCategoryService.addorupdateHomeCategory(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("添加或修改商品类别 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success();
    }


    /**
     * @param @param  request
     * @param @return
     * @param
     * @return
     * @throws
     * @Description 删除商品类别
     * @Title
     */
    @RequestMapping("/delHomeCategory")
    @ResponseBody
    public ZLResult delHomeCategory(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxHomeCategoryService.delHomeCategory(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("删除商品类别 用时:" + (endTime - startTime) + "ms");
        return ZLResult.Success();
    }

    /**
     * @param @param  request
     * @param @return
     * @Description rpc查询门店
     * @Title querybranch
     */
    @RequestMapping("/querybranch")
    @ResponseBody
    public ZLResult querybranch(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apiBranchService.queryBranchDetail(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> branchlist = zlRpcResult.getList();
        return ZLResult.Success(branchlist);

    }
}
