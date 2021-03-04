package com.cl.fun.gag.pic.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MQProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(Object object, String topicName){
        SendResult sendResult = rocketMQTemplate.syncSend(topicName, JSON.toJSONString(object));
        log.info("id为{}的消息发送成功",sendResult.getMsgId());
    }
}
