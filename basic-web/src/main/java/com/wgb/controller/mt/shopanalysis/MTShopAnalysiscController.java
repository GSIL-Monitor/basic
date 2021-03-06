package com.wgb.controller.mt.shopanalysis;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxShopAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 98721 on 2018/8/13.
 */
@Controller
@RequestMapping("/shopAnalysis")
public class MTShopAnalysiscController extends MBBaseController {

    @Autowired
    private ApitWxShopAnalysisService apitWxShopAnalysisService;
    //查询分析数据
    @RequestMapping("/queryAnalysis")
    @ResponseBody
        public ZLResult queryBargainPageList(HttpServletRequest request, HttpServletResponse response) {
            Map<String, Object> params = getPubParams();
            ZLRpcResult zlRpcResult=new ZLRpcResult();
            try {
                    zlRpcResult = apitWxShopAnalysisService.queryAnalysis(params);
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
