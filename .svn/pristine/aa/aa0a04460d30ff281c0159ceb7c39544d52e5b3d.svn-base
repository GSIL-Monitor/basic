package com.wgb.controller.mt.meituan;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.osrms.admin.ApiMeiTuanService;
import com.wgb.service.dubbo.osrms.web.ApitMeiTuanService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meituan")
public class MTMeiTuanController extends MTBaseController {
    @Autowired
    private ApitMeiTuanService apitMeiTuanService;

    @Autowired
    private ApiMeiTuanService apiMeiTuanService;

    private static final Logger logger = LoggerFactory.getLogger(MTMeiTuanController.class);

    /**
     * 收银端接口-查询单据
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryMeiTuanOrder")
    @ResponseBody
    public ZLResult queryMeiTuanOrder(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.queryMeiTuanOrder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-查询订单统计数量
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryMeiTuanOrderCount")
    @ResponseBody
    public ZLResult queryMeiTuanOrderCount(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.queryMeiTuanOrderCount(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-接单
     *
     * @param request
     * @return
     */
    @RequestMapping("/confirmOrder")
    @ResponseBody
    public ZLResult confirmOrder(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");
        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.confirmOrder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-取消单
     *
     * @param request
     * @return
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public ZLResult cancelOrder(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.cancelOrder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-订单已配送
     *
     * @param request
     * @return
     */
    @RequestMapping("/deliveringOrder")
    @ResponseBody
    public ZLResult deliveringOrder(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.deliveringOrder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-订单已完成配送
     *
     * @param request
     * @return
     */

    @RequestMapping("/deliveredOrder")
    @ResponseBody
    public ZLResult deliveredOrder(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.deliveredOrder(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-发美团专配
     *
     * @param request
     * @return
     */

    @RequestMapping("/dispatchShip")
    @ResponseBody
    public ZLResult dispatchShip(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.dispatchShip(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-发众包配送
     *
     * @param request
     * @return
     */

    @RequestMapping("/ZbDispatch")
    @ResponseBody
    public ZLResult ZbDispatch(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.ZbDispatch(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-查询美团门店配置信息
     *
     * @param request
     * @return
     */

    @RequestMapping("/getMeiTuanBranchConfig")
    @ResponseBody
    public ZLResult getMeiTuanBranchConfig(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.getMeiTuanBranchConfig(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-众包配送加小费
     *
     * @param request
     * @return
     */

    @RequestMapping("/updateZbDispatchTip")
    @ResponseBody
    public ZLResult updateZbDispatchTip(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.updateZbDispatchTip(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-众包配送取消配送
     *
     * @param request
     * @return
     */

    @RequestMapping("/cancelDispatch")
    @ResponseBody
    public ZLResult cancelDispatch(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.cancelDispatch(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-拒绝退款
     *
     * @param request
     * @return
     */

    @RequestMapping("/rejectRefund")
    @ResponseBody
    public ZLResult rejectRefund(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.rejectRefund(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-同意退款
     *
     * @param request
     * @return
     */

    @RequestMapping("/agreeRefund")
    @ResponseBody
    public ZLResult agreeRefund(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.agreeRefund(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-更新美团门店配置
     *
     * @param request
     * @return
     */

    @RequestMapping("/updateMeiTuanBranchConfig")
    @ResponseBody
    public ZLResult updateMeiTuanBranchConfig(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.updateMeiTuanBranchConfig(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 收银端接口-获取退款信息
     *
     * @param request
     * @return
     */

    @RequestMapping("/queryMeiTuanOrderRefund")
    @ResponseBody
    public ZLResult queryMeiTuanOrderRefund(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String branchcode = MapUtils.getString(params, "branchcode");

        if (StringUtils.isEmpty(branchcode) && StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMeiTuanService.queryMeiTuanOrderRefund(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }
    /**
     * 获取美团映射门店url
     *
     * @param request
     * @return
     */
    @RequestMapping("/meituanUrl/getMeiTuanMappingBranchUrl")
    @ResponseBody
    public ZLResult getMeiTuanMappingBranchUrl(HttpServletRequest request){

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult=null;
     //   checkAddBrandParams(params);
//系统调用
        try {
            //   系统调用
             zlRpcResult = apitMeiTuanService.getMeiTuanMappingBranchUrl(params);
        }catch (ServiceException sx){
            //系统级别异常
            sx.getStackTrace();
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

             if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
              }
        String meiTuanUrl=(String)zlRpcResult.getData();

         return  ZLResult.Success(meiTuanUrl);


    }
    /**
     * 获取美团解绑门店url
     *
     * @param request
     * @return
     */
    @RequestMapping("/meituanUrl/getMeiTuanUntieBindingBranchUrl")
    @ResponseBody
    public ZLResult getMeiTuanUntieBindingBranchUrl(HttpServletRequest request){

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult=new ZLRpcResult();

//系统调用
        try {
            //系统调用
           zlRpcResult = apitMeiTuanService.getMeiTuanUntieBindingBranchUrl(params);
        }catch (ServiceException sx){
            //服务型异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        String meiTuanUrl=(String)zlRpcResult.getData();

        return  ZLResult.Success(meiTuanUrl);
    }
    @RequestMapping("/commodity/query")
    @ResponseBody
    public ZLResult commodityDetail(HttpServletRequest request){

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult =new ZLRpcResult();
        try {
            // 系统调用
             zlRpcResult =apitMeiTuanService.commodityDetail(params);
        }
        catch (ServiceException sx){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
         if(!zlRpcResult.success()){
             throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
//系统调用
         List<Map<String,Object>> commodityList=zlRpcResult.getList();

        return  ZLResult.Success(commodityList);


    }
    @RequestMapping("/meituanCommodity/mappingCommodity")
    @ResponseBody
    public ZLResult mappingCommodity(HttpServletRequest request){

        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult =new ZLRpcResult();
//系统调用
        try {
            //系统调用
            zlRpcResult =apitMeiTuanService.mappingCommodity(params);
        }catch (ServiceException sx){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }catch (Exception ex){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            return  ZLResult.Error(zlRpcResult.getErrorMsg());
    }
         String message=(String)zlRpcResult.getData();

        return  ZLResult.Success(message);


    }

    private  void checkAddBrandParams(Map<String,Object> params){
        if(StringUtils.isEmpty(MapUtils.getString(params,"brandname"))){
            throw  new ServiceException("品牌名称不能为空");
        }
        if(StringUtils.isEmpty(MapUtils.getString(params,"branchcode"))){
            throw  new ServiceException("品牌编码不能为空");
        }
    }
}
