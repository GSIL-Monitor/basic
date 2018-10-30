package com.wgb.controller.mt.storage;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitStorageAssitanceService;
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
@RequestMapping("/storage/assistance")
public class MTStorageCheckAssitanceController extends MTBaseController {

    @Autowired
    private ApitStorageAssitanceService apitStorageAssitanceService;

    @RequestMapping("/storageCheckAddOrUpdate")
    @ResponseBody
    public ZLResult storageCheckAddOrUpdate(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitStorageAssitanceService.storageCheckAddOrUpdate(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        long endTime = System.currentTimeMillis();
        logger.info("商品助手盘点信息保存 用时:"+(endTime-startTime)+"ms");
        return ZLResult.Success(zlRpcResult.getData());
    }


}
