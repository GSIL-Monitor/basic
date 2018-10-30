package com.wgb.controller.sp.home;

import com.wgb.bean.ZLResult;
import com.wgb.controller.sp.SPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.dubbo.srvms.web.ApitHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("home")
public class SrvHomeController extends SPBaseController {

    @Autowired
    private ApitHomeService apitSrvHomeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SrvHomeController.class);

    @RequestMapping("index")
    @ResponseBody
    public ZLResult home(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        Map<String, Object> params = getParams();
        try {
            ZLRpcResult rpcResult = apitSrvHomeService.index(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询首页信息系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("getExpireBranchCount")
    @ResponseBody
    public ZLResult getExpireBranchCount(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvHomeService.getExpireBranchCount(params);
            result = parseRpcResult(rpcResult);
        } catch (Exception ex){
            LOGGER.error("查询到期门店系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("getStoreCount")
    @ResponseBody
    public ZLResult getStoreCount(HttpServletRequest request) {
        ZLResult result = ZLResult.Success();
        try {
            Map<String, Object> params = getParams();
            ZLRpcResult rpcResult = apitSrvHomeService.getStoreCount(params);
            result = parseRpcResult(rpcResult);
        }catch (Exception ex){
            LOGGER.error("查询新增门店总数/流失门店数系统异常!",ex);
            result.setErrorMsg("操作失败，请联系管理员！");
        }
        return result;
    }


//    /**
//     * 获取环比增长率
//     *
//     * @param request
//     * @return
//     */
//
//    @RequestMapping("/getChainGrowthRate")
//    @ResponseBody
//    public Object getChainGrowthRate(HttpServletRequest request) {
//        Map<String, Object> params = getParams();
//        String newtime = MapUtils.getString(params, "newtime");
//        if (StringUtils.isEmpty(newtime)) {
//            throw new ServiceException(ServiceException.OPER_ERROR);
//        }
//        Map<String, Object> shopCountData = srvShopService.getChainGrowthRate(params);
//        if (shopCountData == null) {
//            throw new ServiceException(ServiceException.OPER_ERROR);
//        }
//
//        return shopCountData;
//    }

}
