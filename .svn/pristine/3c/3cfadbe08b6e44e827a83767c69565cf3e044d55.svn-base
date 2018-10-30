
package com.wgb.controller.mt.settle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.stms.web.ApitCheckBillService;
import com.wgb.service.dubbo.stms.web.ApitSupplierBillsService;
import com.wgb.service.dubbo.stms.web.ApitSupplierService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 供应商对账单
 *
 * @author fxs
 * @create 2018-05-14 16:24
 **/

@Controller
    @RequestMapping("/settle/supplierBill")
public class MTCheckBillController extends MTBaseController {

    @Autowired
    private ApitCheckBillService checkBillService;

    @Autowired
    private ApitSupplierService supplierService;

    @Autowired
    private ApitSupplierBillsService supplierBillsService;

/***
     * 待对账 订单 查询
     * 收货/退货、 联营、 代销
     *
     * @param request
     * @return
     */
    @RequestMapping("/billquery")
    @ResponseBody
    public ZLResult billquery(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //控制参数
        String businessmodel = MapUtils.getString(params, "businessmodel");
        if (StringUtils.isEmpty(businessmodel)) {
            return ZLResult.Error("门店与供应商合作模式未获取到！");
        }
        //查询 未对账订单
        ZLRpcResult rpcResult = checkBillService.queryPageBillList(params);
        return parseRpcResultForData(rpcResult);
    }


/***
     * 对账单详细信息查询
     *
     * @param request
     * @return
     */

    @RequestMapping("/billQueryDetails")
    @ResponseBody
    public ZLResult billQueryDetails(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //控制参数
        String billcode = MapUtils.getString(params, "billcode");
        String mainid = MapUtils.getString(params, "id");
        if (StringUtils.isEmpty(mainid) || StringUtils.isEmpty(billcode)) {
            return  ZLResult.Error("订单号参数未获取到！");
        }
        //查询 未对账订单
        ZLRpcResult rpcResult = checkBillService.queryPageBillListDetail(params);
        Map<String, Object> bills = new HashMap<String, Object>();
        bills.put("dataList", rpcResult.getData());
        return ZLResult.Success(bills) ;
    }


/***
     *  供应商与门店 合作模式
     * @param request
     * @return
     */

    @RequestMapping("/businessMode")
    @ResponseBody
    public ZLResult BusinessMode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //控制参数
        String suppliercode = MapUtils.getString(params, "suppliercode");
        if (StringUtils.isEmpty(suppliercode)) {
            return ZLResult.Error("供应商参数未获取到！");
        }
        //查询供应商经营模式
        ZLRpcResult rpcResult = supplierService.getSupplierType(params);
        Map<String, Object> model = (Map<String, Object>) rpcResult.getData();
        if(MapUtils.isEmpty(model)){
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        String businessmodel = MapUtils.getString(model, "businessmodel");

        if(StringUtils.isEmpty(businessmodel)){
            return ZLResult.Error("合作模式未获取到！");
        }
        return ZLResult.Success(businessmodel);
    }

}

