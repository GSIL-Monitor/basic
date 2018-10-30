package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitMemberLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/member/memberLevel")
public class MTMemberLevelController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberLevelController.class);
    @Autowired
    ApitMemberLevelService apitMemberLevelService;

    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.queryMemberLevelList(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> memberlevelList = zlRpcResult.getList();

        return ZLResult.Success(memberlevelList);
    }

    /**
     * 促销充值规则-接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/salems/select")
    @ResponseBody
    public ZLResult queryMemberLevelListForSalems(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.queryMemberLevelListForSalems(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> memberlevelList = zlRpcResult.getList();

        return ZLResult.Success(memberlevelList);
    }

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 封装返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.queryPageList(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page) zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.saveOrUpdate(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.deleteObject(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/add")
    @ResponseBody
    public ZLResult add(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.insertMemberLevel(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.updateObject(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }


    @RequestMapping("/toUpdate")
    @ResponseBody
    public ZLResult updateObject(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.getObject(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Map<String, Object> object = zlRpcResult.getMap();

        return ZLResult.Success(object);
    }

    @RequestMapping("/delObject")
    @ResponseBody
    public ZLResult delObject(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.deleteObjectNoMember(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public ZLResult getAll(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberLevelService.getAllObject(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> levels = zlRpcResult.getList();

        return ZLResult.Success(levels);
    }

}
