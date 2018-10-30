package com.wgb.service.httpclient;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface HttpClientService {
    String doGet(String url) throws URISyntaxException, ClientProtocolException, IOException;

    String doGet(String url, Map<String, Object> params) throws URISyntaxException, ClientProtocolException, IOException;

    String doPost(String url, Map<String, Object> params) throws URISyntaxException, ClientProtocolException, IOException;

    String doPost(String url) throws URISyntaxException, ClientProtocolException, IOException;
}
