package com.wgb.service.impl.impl;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wgb.dao.CommonDalClient;
import com.wgb.dao.Page;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.FileService;
/*
import com.wgb.service.dubbo.fms.web.ApiFileService;
*/
import com.wgb.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final String NAMESPACE = "shardName.com.wgb.service.impl.FileServiceImpl.";

    @Autowired
    private CommonDalClient dal;

    @Autowired
    private CacheService cacheService;

/*    //@Autowired
    private ApiFileService apiFileService;*/

    @Override
    public Map<String, Object> getFileInfo(String id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        return dal.getDalClient().queryForMap(NAMESPACE + "getFileInfo", params);
    }

    @Override
    public void deleteFile(String fileId) {

        Map<String, Object> fileInfo = getFileInfo(fileId);
        String filePath = MapUtils.getString(fileInfo, "file_path", "");
        if (StringUtils.isNotEmpty(filePath)) {

            File file = new File(filePath);

            if (file.exists()) {
                file.delete();
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("fileId", fileId);

            dal.getDalClient().execute(NAMESPACE + "deleteFile", params);
        }
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

    private String saveFileLocal(MultipartFile file, HttpServletRequest request) {
        //系统根路径
        String sysBasePath = request.getSession().getServletContext().getRealPath("");
        //系统相对路径
        String relativePath = getRelativePath();
        //文件保存路径
        String savePath = sysBasePath + "/" + relativePath;
        //如果路径不存在，需要重新创建
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            savePathFile.mkdirs();
        }
        //上传文件原名
        String fileName = file.getOriginalFilename();
        //新的文件名
        String newFileName = getNewFileName(fileName);
        //文件完整路径，含文件名
        String fileFullPath = savePath + newFileName;

        try {
            FileOutputStream outputStream = new FileOutputStream(fileFullPath);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePath + newFileName;
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
        }

        return relativeFilePath;
    }

    @Override
    public Map<String, Object> saveFile(MultipartFile file, Map<String, Object> params, HttpServletRequest request) {

        //上传文件原名
        String fileName = file.getOriginalFilename();
        //上传文件大小
        String fileSize = String.valueOf(file.getSize());

        /*String filePath = "";
        if (CommonUtil.isUseOss(CommonConfig.OSS_FLAG)) {
            filePath = saveFileOss(file);
        } else {
            filePath = saveFileLocal(file, request);
        }*/

        Map<String, Object> uploadParam = new HashMap<String, Object>();

        //新的文件名
        String newFileName = getNewFileName(fileName);
        //文件的相对路径
        String filePath = getRelativePath() + newFileName;

        uploadParam.put("name", fileName);
        uploadParam.put("size", fileSize);
        uploadParam.put(Contants.LOGIN_USER_ID, MapUtils.getString(params, Contants.LOGIN_USER_ID));
        uploadParam.put(Contants.LOGIN_USER_SHOP_CODE, MapUtils.getString(params, Contants.LOGIN_USER_SHOP_CODE));

        Map<String, Object> result = null;
//        try {
//            result = apiFileService.putObject(filePath, uploadParam,file.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return MapUtils.getMap(result, "result");
    }

    private Map<String, Object> postObjectPolicyForSCP(String scpcode) {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConnections(200);
        conf.setSocketTimeout(10000);
        conf.setMaxErrorRetry(5);
        OSSClient client = new OSSClient(CommonConfig.OSS_ENDPOINT, CommonConfig.OSS_ACCESS_KEY_ID, CommonConfig.OSS_ACCESS_KEY_SECRET, conf);

        String dir = "attachment/" + DateUtils.formatDate2Str(new Date(), "yyyyMMdd") + "/";

        try {
            long ex = 30L;
            long expireEndTime = System.currentTimeMillis() + ex * 1000L;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem("content-length-range", 0L, 1048576000L);
            policyConds.addConditionItem(MatchMode.StartWith, "key", dir);
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            String selfParam = "";
            if(StringUtils.isNotEmpty(scpcode)) {
                selfParam = "&scpcode=" + scpcode;
            }

            HashMap callBackMap = new HashMap();
            callBackMap.put("callbackUrl", CommonConfig.OSS_CALLBACK);
            callBackMap.put("callbackBody", "url=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}" + selfParam);
            callBackMap.put("callbackBodyType", "application/x-www-form-urlencoded");
            String callBackStr = JSONObject.fromObject(callBackMap).toString();
            byte[] callBackByte = callBackStr.getBytes("utf-8");
            String callback = BinaryUtil.toBase64String(callBackByte);
            HashMap resultMap = new HashMap();
            resultMap.put("accessid", CommonConfig.OSS_ACCESS_KEY_ID);
            resultMap.put("policy", encodedPolicy);
            resultMap.put("signature", postSignature);
            resultMap.put("dir", dir);
            resultMap.put("host", CommonConfig.OSS_URL);
            resultMap.put("expire", String.valueOf(expireEndTime / 1000L));
            resultMap.put("callback", callback);
            return resultMap;
        } catch (Exception var19) {
            throw new ServiceException(100003);
        }
    }

    @Override
    public Page<?> queryFilePage(Map<String, Object> params) {
        return dal.getDalClient().queryForListPage(NAMESPACE + "getFileList", params);
    }

    @Override
    public Page<?> queryPageList(Map<String, Object> params) {
        return dal.getDalClient().queryForListPage(NAMESPACE + "queryPageList", params);
    }

    @Override
    public Map<String, Object> postObjectPolicy(Map<String, Object> params) {
        return OssUtil.postObjectPolicy(MapUtils.getString(params, "shopcode"));
    }

    @Override
    public Map<String, Object> postObjectPolicyForSCP(Map<String, Object> params) {
        return postObjectPolicyForSCP(MapUtils.getString(params, "scpcode"));
    }
}
