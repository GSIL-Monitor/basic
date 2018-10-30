package com.wgb.controller.mt.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitCommodityPriceService;
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
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/commodity/commodityprice")
public class MTCommodityPriceController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitCommodityPriceService apitCommodityPriceService;

    /**
     *主页面查询page
     * @param request
     * @return
     */
    @RequestMapping("/commodityPriceListDetail")
    @ResponseBody
    public ZLResult commodityPriceListDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCommodityPriceService.queryPage(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

    /**
     *调价查询页面page
     * @param request
     * @return
     */
    @RequestMapping("/queryCommodityPrice")
    @ResponseBody
    public ZLResult queryCommodityPrice(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCommodityPriceService.queryCommodityPrice(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success((Page<?>)zlRpcResult.getData());
    }

    /**
     *删除调价单
     * @param request
     * @return
     */
    @RequestMapping("/commodityPriceDel")
    @ResponseBody
    public ZLResult commodityPriceDel(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCommodityPriceService.commodityPriceDel(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success();
    }

    /**
     *审核调价单
     * @param request
     * @return
     */
    @RequestMapping("/commodityPriceExamine")
    @ResponseBody
    public ZLResult commodityPriceExamine(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCommodityPriceService.commodityPriceExamine(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success();
    }


    /**
     *新增或修改调价单
     * @param request
     * @return
     */
    @RequestMapping("/commodityPriceAddOrUpdate")
    @ResponseBody
    public ZLResult commodityPriceAddOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        try {

            //系统调用
            zlRpcResult = apitCommodityPriceService.addOrUpdate(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success();
    }

    /**
     *查询调价单明细
     * @param request
     * @return
     */
    @RequestMapping("/commodityPriceUpdateDetail")
    @ResponseBody
    public ZLResult commodityPriceUpdateDetail(HttpServletRequest request) {
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        //校验参数
        checkCommodityPriceDetailParams(params);

        try {

            //系统调用
            zlRpcResult = apitCommodityPriceService.commodityPriceDetail(params);

        }catch (ServiceException sx){

            //服务型异常
            throw sx;
        }catch (Exception ex){

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return  ZLResult.Success(zlRpcResult.getMap());
    }

    /**
     *   查询调价单明细校验参数
     *   字段备注  id:调价单id
     */
    private  void checkCommodityPriceDetailParams(Map<String,Object> params){
        if(StringUtils.isEmpty(MapUtils.getString(params,"id"))){
            throw  new ServiceException("调价单id不能为空");
        }
    }
}
