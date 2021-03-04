package com.cl.fun.gag.pic.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.cl.fun.gag.pic.entity.ESLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.syslog.topic}", consumerGroup = "${mq.syslog.consumer-group}")
public class SysLogConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        // todo 这样处理不行，感觉还是要有消息的id才行
        ESLog esLog = JSON.parseObject(message, ESLog.class);
        System.out.println(esLog);
        log.debug("Receive message：" + esLog);
        // todo 这里加上进入es的处理
    }
}