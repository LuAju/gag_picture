package com.cl.fun.gag.pic.service.impl;

import com.cl.fun.gag.pic.rocketmq.producer.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RocketMQService {
    @Autowired
    private MQProducer mqProducer;

    @Value("${mq.syslog.topic}")
    private String sysLogTopic;

    public void sendSysLogMessage(Object object) {
        sendMessage(object, sysLogTopic);
    }

    public void sendMessage(Object object, String topicName) {
        mqProducer.send(object, topicName);
    }

}
