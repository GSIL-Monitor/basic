package com.wgb.controller.mt.system;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.sms.web.ApitSmsDetailService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/sms/record")
public class MTSmsDetailController extends MTBaseController {

    @Autowired
    private ApitSmsDetailService apitSmsDetailService;

    /**
     * 短信使用明细-查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult manage_detail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitSmsDetailService.getSmsDetail(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }


        return ZLResult.Success((Page<?>) zlRpcResult.getData());
    }

    /**
     * 查询商户剩余短信条数
     *
     * @param request
     * @return
     */
    @RequestMapping("/smsNumber")
    @ResponseBody
    public ZLResult smsNumber(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        //校验参数
        checkSmsNumberParams(params);

        try {

            //系统调用
            zlRpcResult = apitSmsDetailService.getSmsNumber(params);

        } catch (ServiceException sx) {

            //服务型异常
            throw sx;
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getMap());
    }

    /**
     * 查询商户剩余短信条数 校验参数
     * 字段备注  shopcode:商户编码
     */
    private void checkSmsNumberParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "shopcode"))) {
            throw new ServiceException("商户编码不能为空");
        }
    }

}
