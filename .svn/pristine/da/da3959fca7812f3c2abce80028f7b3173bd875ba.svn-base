package com.wgb.service.impl.send;

/**
 * Created by 11609 on 2017/10/17.
 */

import com.wgb.rocketmq.SendAware;
import com.wgb.rocketmq.annotation.MQMethod;
import com.wgb.rocketmq.annotation.MQService;

@MQService(topic = "topic_basic", serviceName = "saveSaleBills2DCMS")
public interface SaveSaleBills2DCMS extends SendAware {

    @MQMethod(reqMbfBodyNode = true)
    void send(String message);
}
