package com.wgb.controller.mt.member;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.mbms.web.ApitGiftService;
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
@RequestMapping("/member/gift")
public class MTGiftController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTGiftController.class);

    @Autowired
    private ApitGiftService giftService;

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        ZLRpcResult rpcResult = giftService.queryPageList(params);

        return parseRpcResultForData(rpcResult);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {


        //获取入参数据
        Map<String, Object> params =  getParams();
        String id = MapUtils.getString(params, "id");
        ZLRpcResult rpcResult;
        //新增
        if (StringUtils.isEmpty(id)) {
            rpcResult = giftService.addGift(params);
        } else {  //修改
            rpcResult = giftService.updateGift(params);
        }

        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();
        String id = MapUtils.getString(params, "id", "");
        ZLRpcResult rpcResult;
        if (StringUtils.isNotEmpty(id)) {
            rpcResult = giftService.delGift(params);
        } else {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }
        return parseRpcResultForMessage(rpcResult);
    }

    @RequestMapping("/getObject")
    @ResponseBody
    public ZLResult getObject(HttpServletRequest request) {
        //获取入参数据
        Map<String, Object> params =  getParams();
        ZLRpcResult rpcResult = giftService.getGiftInfo(params);

        return parseRpcResultForData(rpcResult);
    }


    @RequestMapping("/delBatch")
    @ResponseBody
    public ZLResult delBatch(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String ids = MapUtils.getString(params, "ids", "");
        ZLRpcResult rpcResult;
        if (StringUtils.isNotEmpty(ids)) {
            rpcResult = giftService.delBatch(params);
        } else {
            throw new ServiceException(ServiceException.PARAM_ERROR);
        }

        return parseRpcResultForMessage(rpcResult);
    }
}
