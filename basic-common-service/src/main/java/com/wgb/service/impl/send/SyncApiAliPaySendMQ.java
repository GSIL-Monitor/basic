package com.wgb.service.impl.send;

import com.wgb.rocketmq.SendAware;
import com.wgb.rocketmq.annotation.MQMethod;
import com.wgb.rocketmq.annotation.MQService;

/**
 * Created by 98721 on 2018/9/20.
 */
@MQService(topic = "topic_basic", serviceName = "syncApiAliPaySendMQ")
public interface SyncApiAliPaySendMQ  extends SendAware {
    @MQMethod(reqMbfBodyNode = true)
    void send(String message);
}
