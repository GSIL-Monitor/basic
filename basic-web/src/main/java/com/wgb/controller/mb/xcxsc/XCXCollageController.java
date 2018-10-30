package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCollageService;
import com.wgb.service.dubbo.wxms.web.ApitCsCollageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/6/19.
 */

@Controller
@RequestMapping("/xcxsc/collage")
public class XCXCollageController extends MBXCXBaseController {
    private Logger logger = LoggerFactory.getLogger(XCXCollageController.class);

    @Autowired
    private ApitCollageService collageService;

    @Autowired
    private ApitCsCollageService apitCsCollageService;


    /**
     * 获取拼团活动列表
     */

    @RequestMapping("/list")
    @ResponseBody
    public ZLResult list(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = collageService.queryCollageConfigPageList(params);
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


    /**
     * 拼团入团或者开团的check
     */
    @RequestMapping("/checkCanStartOrJoinCollage")
    @ResponseBody
    public ZLResult checkCanStartOrJoinCollage(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsCollageService.checkCanStartOrJoinCollage(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }
    /**
     * 获取拼团活动详情
     */

    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult detail(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = collageService.detail(params);
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


    /**
     * 发起拼团
     *
     * @param request
     * @return
     */

    @RequestMapping("/addcollage")
    @ResponseBody
    public ZLResult addcollage(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = collageService.addcollage(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }



    /**
     * 人员入团
     *
     * @param request
     * @return
     */

    @RequestMapping("/addcollagemember")
    @ResponseBody
    public ZLResult addcollagemember(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = collageService.addcollagemember(params);
        } catch (ServiceException sx) {
            throw sx;
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }


    /**
     * 拼团活动详情
     *
     * @param request
     * @return
     */

    @RequestMapping("/collagedetail")
    @ResponseBody
    public ZLResult collagedetail(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = collageService.collagedetail(params);
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

