package com.wgb.controller.mt.system;


import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.urms.web.ApitFeedBackService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/system/feedback")
public class MTFeedbackController extends MTBaseController {

    @Autowired
    private ApitFeedBackService apitFeedBackService;

    /**
     * 2.0问题反馈查询
     * @param request
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request){

        Map<String,Object> params=getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitFeedBackService.query(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 2.0问题反馈编辑
     * @param request
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        Map<String, Object> params = getParams();
        String tittle= MapUtils.getString(params,"title");
        String detail= MapUtils.getString(params,"detail");
        String type= MapUtils.getString(params,"type");
        if (StringUtils.isEmpty(tittle)) {
            return ZLResult.Error("标题不能为空！");
        }
        if (StringUtils.isEmpty(detail)) {
            return ZLResult.Error("详情不能为空！");
        }
        if (StringUtils.isEmpty(type)) {
            return ZLResult.Error("类型不能为空！");
        }
        try {
        apitFeedBackService.saveOrUpdate(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        return ZLResult.Success();
    }
}
