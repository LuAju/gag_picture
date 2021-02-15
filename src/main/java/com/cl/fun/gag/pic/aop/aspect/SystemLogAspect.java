package com.cl.fun.gag.pic.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.fun.gag.pic.entity.ESLog;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class SystemLogAspect {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("")
    private String txProduceGroup;

    @Value("pic_log")
    private String txDestination;

    @Pointcut("@annotation(com.cl.fun.gag.pic.aop.annotation.SysLog)")
    public void syslogPointcut() {

    }

    @Around("syslogPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Date start = new Date();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        try {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // ip地址
            String ipAddr = getRemoteHost(request);
            // 请求路径
            String requestUrl = request.getRequestURL().toString();

            // 获取请求参数进行打印
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;

            // 参数名数组
            String[] parameterNames = ((MethodSignature) signature).getParameterNames();
            // 构造参数组集合
            List<Object> argList = new ArrayList<>();
            for (Object arg : joinPoint.getArgs()) {
                // request/response无法使用toJSON
                if (arg instanceof HttpServletRequest) {
                    argList.add("request");
                } else if (arg instanceof HttpServletResponse) {
                    argList.add("response");
                } else {
                    argList.add(JSON.toJSON(arg));
                }
            }

            stopwatch.stop();
            long timeConsuming = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            ESLog esLog = ESLog.builder()
                    .costTime(timeConsuming)
                    .createBy("admin")
                    .createTime(start)
                    .ip(ipAddr)
                    .requestParam(argList.toString())
                    .requestUrl(requestUrl)
                    .username("guest").build();
            sendMsg(esLog);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("参数获取失败: {}", throwable.getMessage());
        }
        return result;
    }

    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.contains("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    public void sendMsg(ESLog esLog) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("log", esLog);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        rocketMQTemplate.convertAndSend(txDestination,message);
//        TransactionSendResult sendResult =
//                rocketMQTemplate.sendMessageInTransaction(txProduceGroup, "topic_txmsg", message, null);
//        log.debug("send msg body = {},result={}", message.getPayload(), sendResult.getSendStatus());
    }
}
