package com.wgb.service.impl.camera;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wgb.cache.RedisFactory;
import com.wgb.service.camera.CameraService;
import com.wgb.service.httpclient.HttpClientService;
import com.wgb.util.Contants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取摄像头第三方连接信息
 *
 * @author fxs
 * @create 2018-08-01 14:49
 **/
@Service
public class CameraServiceImpl implements CameraService {

    @Value("${camera.clientId}")
    private String clientId;

    @Value("${camera.clientSecret}")
    private String clientSecret;

    @Value("${camera.grantType}")
    private String grantType;

    @Value("${camera.scope}")
    private String scope;

    @Value("${camera.url}")
    private String url;

    @Value("${camera.tokenUrl}")
    private String tokenUrl;

    @Autowired
    private HttpClientService httpClientService;


    public String queryCameraAccessToken() {

        // Redis中获取
        String accessToken = RedisFactory.getDefaultClient().get(Contants.MONITOR_ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)){
            Map<String ,Object> params = new HashMap<>();
            params.put("client_id" ,clientId);
            params.put("client_secret" ,clientSecret);
            params.put("grant_type" ,grantType);
            params.put("scope" ,scope);
            try {
                String response = httpClientService.doPost(url + tokenUrl, params);
                JSONObject jsonObject = JSON.parseObject(response);
                accessToken = jsonObject.get("token_type").toString() + " " + jsonObject.get("access_token").toString();
                Integer expires = Integer.parseInt(jsonObject.get("expires_in").toString());
                RedisFactory.getDefaultClient().set(Contants.MONITOR_ACCESS_TOKEN ,accessToken ,expires);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accessToken;
    }
}
