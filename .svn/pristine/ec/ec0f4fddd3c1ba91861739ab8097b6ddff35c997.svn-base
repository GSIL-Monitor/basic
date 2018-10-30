package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/27 0027.
 */

@Controller
@RequestMapping("/xcxsc/branch")
public class XCXBranchController extends MBXCXBaseController {


    @Autowired
    private ApitBranchService branchService;


    /**
     * 获取门店列表
     *
     * @param request
     * @return
     */

    @RequestMapping("/entry/getBranchList")
    @ResponseBody
    public ZLResult getBranchList(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = branchService.getBranchList(params);
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

    @RequestMapping("/getbranchInfo")
    @ResponseBody
    public ZLResult getbranchInfo(HttpServletRequest request) {
        Map<String, Object> params = getPubParams();
        //获取门店详情
        ZLRpcResult zlRpcResult;
        try {

            zlRpcResult = branchService.getbranchInfo(params);

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

