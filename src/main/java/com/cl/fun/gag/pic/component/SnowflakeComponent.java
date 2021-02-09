package com.cl.fun.gag.pic.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class SnowflakeComponent {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Value("${snowflake.workId}")
    private long workerId ;//为终端ID

    @Value("${snowflake.workId}")
    private long datacenterId ;//数据中心ID
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);
    @PostConstruct
    public void init(){
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        log.info("当前机器的workId:{}",workerId);
    }
    public long snowflakeId(){
        return snowflake.nextId();
    }
    public long snowflakeId(long workerId,long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }
}
