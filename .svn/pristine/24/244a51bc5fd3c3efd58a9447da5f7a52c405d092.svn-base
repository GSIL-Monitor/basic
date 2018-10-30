package com.wgb.controller.mt.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitCommoditySpecService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by qiuxh on 2018/09/25.
 */
@Controller
@RequestMapping("/commodity/spec")
public class MTCommoditySpecController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitCommoditySpecService apitCommoditySpecService;

    /**
     * 查询颜色
     */
   @RequestMapping("/querySpecColors")
    @ResponseBody
    public ZLResult querySpecColors(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        checkIndustryParams(params);
        checkShopcodeSPUCodeParams(params);
        try {
            //系统调用
            zlRpcResult = apitCommoditySpecService.querySpecColorByShopcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getList());
    }

    /**
     * 查询尺码
     */
    @RequestMapping("/querySpecSizes")
    @ResponseBody
    public ZLResult querySpecSizes(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        checkIndustryParams(params);
        checkShopcodeSPUCodeParams(params);
        try {
            //系统调用
            zlRpcResult = apitCommoditySpecService.querySpecSizeByShopcode(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getList());
    }

    /**
     * 查询规格表校验参数
     * 字段备注  industryid:行业id
     */
    private void checkIndustryParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "industryid"))) {
            throw new ServiceException("参数缺失");
        }
    }

    /**
     * 查询规格表校验参数
     * 字段备注  shopcode:商户编码  spucode:spu商品编码
     */
    private void checkShopcodeSPUCodeParams(Map<String, Object> params) {
        if (StringUtils.isEmpty(MapUtils.getString(params, "shopcode")) || StringUtils.isEmpty(MapUtils.getString(params, "spucode"))) {
            throw new ServiceException("参数缺失");
        }
    }
}
