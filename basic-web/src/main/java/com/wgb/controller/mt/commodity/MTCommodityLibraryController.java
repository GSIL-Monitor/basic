package com.wgb.controller.mt.commodity;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitCommodityLibraryService;
import com.wgb.util.CommonUtil;
import com.wgb.util.OssUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lzy on 2018/7/27.
 */
@Controller
@RequestMapping("/entry/commodity/commodityLibrary")
public class MTCommodityLibraryController extends MTBaseController {

    @Autowired
    private ApitCommodityLibraryService apitCommodityLibraryService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 查询商品
     * @param request
     * @return
     */
    @RequestMapping("/queryCommodityLibrary")
    @ResponseBody
    public ZLResult queryCommodityLibrary(HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        //校验参数
        int errorCode = validateParams(params);
        if (errorCode != 0) {
            return ZLResult.Error("参数丢失!");
        }

        try {
            zlRpcResult = apitCommodityLibraryService.queryCommodityLibrary(params);
        }catch (Exception ex){
            ZLResult result = ZLResult.Error(zlRpcResult.getErrorMsg());
            result.setData(new HashMap());
            return result;
            //throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if(!zlRpcResult.success()){
            ZLResult result = ZLResult.Error(zlRpcResult.getErrorMsg());
            result.setData(new HashMap());
            return result;
            //throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        if (zlRpcResult.getData() == null) {
            return ZLResult.Success(new HashMap());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 上传图片并保存到本地商品库
     * @param file
     *
     * @param request
     * @return
     */
    @RequestMapping("/saveUpLoadDataCommodityLibrary")
    @ResponseBody
    public ZLResult saveUpLoadDataCommodityLibrary(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> params =  getParams();
        ZLRpcResult zlRpcResult  = new ZLRpcResult();

        //校验参数
        int errorCode = validateParams(params);
        if (errorCode != 0) {
            return ZLResult.Error("参数丢失!");
        }

        try {
            //上传图片获得相对路径
            String img = saveFileOss(file);
            params.put("img",img);
            zlRpcResult = apitCommodityLibraryService.saveUpLoadDataCommodityLibrary(params);

        } catch (Exception e) {
            ZLResult result = ZLResult.Error(zlRpcResult.getErrorMsg());
            result.setData(new HashMap());
            return result;
            //throw new ServiceException(e.getMessage());
        }
        if(!zlRpcResult.success()){
            ZLResult result = ZLResult.Error(zlRpcResult.getErrorMsg());
            result.setData(new HashMap());
            return result;
            //throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return  ZLResult.Success(new HashMap());
    }


    private int validateParams(Map<String, Object> params) {
        String code = MapUtils.getString(params, "code");
        if (StringUtils.isEmpty(code)) {
            return ServiceException.PARAM_MISSING;
        }
        return 0;
    }

    private String saveFileOss(MultipartFile file) {

        //上传文件原名
        String fileName = file.getOriginalFilename();
        //新的文件名
        String newFileName = getNewFileName(fileName);
        //文件的相对路径
        String relativeFilePath = getRelativePath() + newFileName;

        try {
            OssUtil.uploadFile2OSS(file.getInputStream(), relativeFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("上传图片到云失败");
        }
        return relativeFilePath;
    }

    private String getRelativePath() {
        return "attachment" + "/" + CommonUtil.getCurrentYMDStr() + "/";
    }

    private String getNewFileName(String fileName) {
        //生成本地保存文件名前缀
        String fileNamePrefix = UUID.randomUUID().toString();
        //文件名后缀
        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        //新的文件名
        return fileNamePrefix + "." + fileNameSuffix;
    }
}
