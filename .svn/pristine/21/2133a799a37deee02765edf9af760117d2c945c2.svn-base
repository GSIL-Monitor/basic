package com.wgb.controller.mb.xcxsc;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 10057 on 2018/3/2.
 */

@Controller
@RequestMapping("/xcxsc/address")
public class XCXAddressController extends MBXCXBaseController {

    @Autowired
    private ApitAddressService addressService;

    /**
     * 获取收货地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ZLResult list(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = addressService.list(params);
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
     * 添加收获地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ZLResult add(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = addressService.add(params);
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
     * 修改收获地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ZLResult update(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = addressService.update(params);
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
     * 删除收获地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public ZLResult del(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = addressService.del(params);
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
     * 修改默认地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateDefaultAddress")
    @ResponseBody
    public ZLResult updateDefaultAddress(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = addressService.updateDefaultAddress(params);
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
     * 获取默认收获地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryDefaultAddress")
    @ResponseBody
    public ZLResult queryDefaultAddress(HttpServletRequest request) {
        Map<String, Object> params = getXcxParams();
        ZLRpcResult zlRpcResult;
        try {
            zlRpcResult = addressService.queryDefaultAddress(params);
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
