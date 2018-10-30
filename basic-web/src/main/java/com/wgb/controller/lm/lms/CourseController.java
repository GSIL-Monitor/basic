package com.wgb.controller.lm.lms;

import com.wgb.bean.ZLResult;
import com.wgb.controller.lm.LMXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.lms.web.ApitCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * By chmin
 */
@Controller
@RequestMapping("course")
public class CourseController extends LMXCXBaseController {

    @Autowired
    private ApitCourseService apitCourseService;

    @RequestMapping("selectCourse")
    @ResponseBody
    public ZLResult selectCourse(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.selectCourse(params);
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

    @RequestMapping("selectCourseFull")
    @ResponseBody
    public ZLResult selectCourseFull(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.selectCourseFull(params);
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

    @RequestMapping("buyCourse")
    @ResponseBody
    public ZLResult buyCourse(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.buyCourse(params);
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

    @RequestMapping("owned")
    @ResponseBody
    public ZLResult owned(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.availableRead(params);
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

    @RequestMapping("availableRead")
    @ResponseBody
    public ZLResult availableRead(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.availableRead(params);
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

    /*首页课程推荐*/
    @RequestMapping("/entry/getRecommend")
    @ResponseBody
    public ZLResult getRecommend(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.selectCourseRecommend(params);
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

    /*精品课程分类查询*/
    @RequestMapping("entry/getExcellentCourse")
    @ResponseBody
    public ZLResult getExcellentCourse(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.selectExcellentCourse(params);
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

    /*课程分页*/
    @RequestMapping("entry/getCoursePage")
    @ResponseBody
    public ZLResult getCoursePage(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.getCoursePage(params);
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

    /*收藏*/
    @RequestMapping("addCourseCollection")
    @ResponseBody
    public ZLResult addCourseCollection(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.addCollectionStatus(params);
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

    /*取消收藏*/
    @RequestMapping("delCourseCollection")
    @ResponseBody
    public ZLResult delCourseCollection(){
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = apitCourseService.delCollectionStatus(params);
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
