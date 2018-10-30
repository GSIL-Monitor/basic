package com.wgb.mq.send;

import com.wgb.rocketmq.SendAware;
import com.wgb.rocketmq.annotation.MQMethod;
import com.wgb.rocketmq.annotation.MQService;

/**
 * Created by Administrator on 2018/9/19 0013.
 */
@MQService(topic = "topic_basic", serviceName = "SyncApiWxPaySendMQ")
public interface SyncApiWxPaySendMQ extends SendAware {

    @MQMethod(reqMbfBodyNode = true)
    void send(String message);
}
