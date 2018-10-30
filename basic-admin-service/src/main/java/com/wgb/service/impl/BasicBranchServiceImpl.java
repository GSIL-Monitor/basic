package com.wgb.service.impl;

import com.wgb.dubbo.ZLRpcResult;
import com.wgb.service.BranchService;
import com.wgb.service.dubbo.basic.BasicBranchService;
import com.wgb.exception.ServiceException;
import com.wgb.util.ZLApiExceptionConstant;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wgb on 2017/10/15 0015.
 */
@Service
public class BasicBranchServiceImpl implements BasicBranchService{

    public static final Logger logger = LoggerFactory.getLogger(BasicBranchServiceImpl.class);

    @Autowired
    private BranchService branchService;

    /**
     * 参数校验
     *
     * @param params
     * @return
     */
    private int queryBranchList_validateParams(Map<String, Object> params) {

        //门店编码
        String shopcode = MapUtils.getString(params, "shopcode");

        if (StringUtils.isEmpty(shopcode)) {
            return ServiceException.PARAM_MISSING;
        }
        return 0;
    }

    @Override
    public ZLRpcResult queryBranchList(Map<String, Object> params) {
        ZLRpcResult rpcResult = new ZLRpcResult();

        int errorCode = queryBranchList_validateParams(params);

        if (errorCode != 0) {
            rpcResult.setErrorCode(errorCode);
            return rpcResult;
        }

        try {
            //获取门店列表
            List<Map<String, Object>> dataList = branchService.queryBranchList(params);
            rpcResult.setData(dataList);

        } catch (ServiceException e) {
            logger.error("API获取商户门店数据处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(e.getCode());
        } catch (Exception e) {
            logger.error("API获取商户门店数据处理异常，输入参数 =" + params, e);
            rpcResult.setErrorCode(ServiceException.SYS_ERROR);
        }
        return rpcResult;
    }
}
