package com.wgb.service;

import com.wgb.dao.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface FileService {

    Map<String, Object> getFileInfo(String fileId);

    void deleteFile(String fileId);

    Map<String, Object> saveFile(MultipartFile file, Map<String, Object> params, HttpServletRequest request);

    Page<?> queryFilePage(Map<String, Object> params);

    Map<String,Object> postObjectPolicy(Map<String, Object> params);
}
