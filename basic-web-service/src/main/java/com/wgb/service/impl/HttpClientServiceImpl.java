package com.wgb.service.impl;

import com.wgb.service.httpclient.HttpClientService;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * httpClient
 *
 * @author fxs
 * @create 2018-08-01 15:59
 **/
@Service
public class HttpClientServiceImpl implements HttpClientService {

    @Autowired(required = false)
    private RequestConfig requestConfig;

    @Autowired(required = false)
    private CloseableHttpClient httpClient;

    /**
     * 执行Get请求
     *
     * @param url
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url) throws URISyntaxException,
            ClientProtocolException, IOException {
        return doGet(url, null);
    }

    /**
     * 执行Get请求
     *
     * @param url
     * @param params
     *            请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url, Map<String, Object> params)
            throws URISyntaxException, ClientProtocolException, IOException {
        // 定义请求的参数
        URI uri = null;
        if (params != null) {
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addParameter(entry.getKey(),
                        String.valueOf(entry.getValue()));
            }
            uri = builder.build();
        }

        // 创建http GET请求
        HttpGet httpGet = null;
        if (uri != null) {
            httpGet = new HttpGet(uri);
        } else {
            httpGet = new HttpGet(url);
        }
        // 设置请求参数
        httpGet.setConfig(this.requestConfig);

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     *
     * @param url
     * @param params
     *            请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url, Map<String, Object> params )
            throws URISyntaxException, ClientProtocolException, IOException {
        // 设置post参数
        List<NameValuePair> parameters = null;
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;

        // 定义请求的参数
        if (params != null) {
            // 设置post参数
            parameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                // 添加参数
                parameters.add(new BasicNameValuePair(entry.getKey(), String
                        .valueOf(entry.getValue())));
            }
            // 构造一个form表单式的实体
            formEntity = new UrlEncodedFormEntity(parameters);
        }
        // 创建http post 请求
        HttpPost httpPost = new HttpPost(url);
        // 设置请求参数
        httpPost.setConfig(this.requestConfig);
        if (formEntity != null) {
            httpPost.setEntity(formEntity);
        }

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     *
     * @param url
     *            请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url) throws URISyntaxException,
            ClientProtocolException, IOException {
        return doPost(url, null);
    }

}
