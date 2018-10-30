package com.wgb.service;

import com.wgb.dao.Page;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/13.
 */
public interface MQMessageService {
    long insertMQMessage(String beanId, String message, String topic, String serviceName);

    int updateMQMessageById(long id, int checkstatus, String errorreason);

    Map<String, Object> selectMQMessageById(long id);

    void chooseBusinessService(String beanId, Map<String, Object> messageMap);

    int deleteMQMessageById(long id);

    int updateMQMessageById(long id, int checkstatus, int isrepeat, String errorreason);

    Page<?> selectXmlMessage(Map<String, Object> params);

    String deleteXmlQuery(Map<String, Object> params);

    String optXmlMessage(Map<String, Object> params);
}
