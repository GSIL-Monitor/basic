package com.wgb.controller.mt.act;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.acts.web.ApitPortalActPlayrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lzy on 2018/5/15.
 */
@Controller
@RequestMapping("/act/realtime")
public class MTActPlayRecordController extends MTBaseController {

    public static final Logger logger = LoggerFactory.getLogger(MTActPlayRecordController.class);

    @Autowired
    private ApitPortalActPlayrecordService apitPortalActPlayrecordService;

    /**
     * 2.0广告广告投放
     * @param request
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public ZLResult queryManageDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitPortalActPlayrecordService.queryManageDetail(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

}
