package com.wgb.controller.mt.purchase;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitPurchaseSupplierService;
import com.wgb.util.ExcelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */

@Controller
@RequestMapping("/settle/supplier")
public class MTSupplierController extends MTBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApitPurchaseSupplierService apitPurchaseSupplierService;

    @RequestMapping("/detail")
    @ResponseBody
    public ZLResult manage_detail(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitPurchaseSupplierService.supplierDetail(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 查询所有供应商
     *
     * @param request
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public ZLResult select(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.select(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 查询购销和代销
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryUnionProxySupplier")
    @ResponseBody
    public ZLResult queryUnionProxySupplier(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.queryUnionProxySupplier(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    /**
     * 查询代销供应商
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryProxySupplier")
    @ResponseBody
    public ZLResult queryProxySupplier(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.queryProxySupplier(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    /**
     * 查询联营供应商
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryUnionSupplier")
    @ResponseBody
    public ZLResult queryUnionSupplier(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.queryUnionSupplier(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    /**
     * 为商品页面供应商
     *
     * @param request
     * @return
     */
    @RequestMapping("/querySupplierForCommodity")
    @ResponseBody
    public ZLResult querySupplierForCommodity(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.querySupplierForCommodity(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    @RequestMapping("/export")
    @ResponseBody
    public ZLResult export(HttpServletRequest request, HttpServletResponse response) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.export(params);

            List<Map<String,Object>> supplierList=zlRpcResult.getList();

            formatCommodityListForExport(supplierList);
            // 字段名数组
            String[] columns = {"suppliercode", "name","regionname", "mnemonic", "contact", "contactnum", "contactaddress",
                    "flag","businessmodelname","commissionrate","createtime"};

            // 字段名数组
            String[] columnNames = {"编码", "名称","区域名称", "助记码", "联系人", "联系电话", "联系地址",
                    "状态","经营方式","提成率","创建时间"};

            String fileName = "供应商资料";

            ExcelUtil.exportExcel(supplierList, columns, columnNames, fileName, request, response);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    @RequestMapping("/delSupplier")
    @ResponseBody
    public ZLResult delSupplier(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.delSupplier(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        //校验参数
        try {

            //系统调用
            zlRpcResult = apitPurchaseSupplierService.saveOrUpdate(params);
        } catch (Exception ex) {

            //系统级别异常
            throw new ServiceException(ex.getMessage());
        }

        //判断返回结果
        if (!zlRpcResult.success()) {

            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

    private void formatCommodityListForExport(List<Map<String, Object>> supplierList) {
        if (CollectionUtils.isNotEmpty(supplierList)) {
            for (Map<String, Object> supplier : supplierList) {
                String flag = MapUtils.getString(supplier, "flag", "");

                if (flag.equals("1")) {
                    supplier.put("flag", "正常");
                } else {
                    supplier.put("flag", "锁定");
                }

            }
        }
    }
}
