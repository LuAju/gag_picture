package com.cl.fun.gag.pic.config;

import com.cl.fun.gag.pic.config.properties.ESProperties;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ESConfig {
    @Autowired
    private ESProperties esProperties;

//    @Bean
//    public TransportClient getTransportClient(){
//        // 创建对象
//        TransportClient transportClient = null;
//        try {
//            // 配置集群信息
//            Settings settings = Settings.builder().put("cluster.name", esProperties.getClusterName())
//                    .put("nodeName", esProperties.getNodeName())
//                    // 设置自动修改
//                    .put("client.transport.sniff", true)
//                    .put("thread_pool.search.size", esProperties.getPool()).build();
//            // 初始化
//            transportClient = new PreBuiltTransportClient(settings);
//            //  配置连接信息
//            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(esProperties.getIp()),Integer.parseInt(esProperties.getPort()));
//            // 将连接信息放到对象中
//            transportClient.addTransportAddress(transportAddress);
//        }catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        // 返回对象
//        return transportClient;
//    }

}
