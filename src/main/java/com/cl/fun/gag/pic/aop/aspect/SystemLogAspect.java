package com.cl.fun.gag.pic.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.fun.gag.pic.component.SnowflakeComponent;
import com.cl.fun.gag.pic.entity.ESLog;
import com.cl.fun.gag.pic.entity.auth.UserManageDetails;
import com.cl.fun.gag.pic.service.impl.RocketMQService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private RocketMQService rocketMQService;

    @Autowired
    private SnowflakeComponent snowflakeComponent;

//    @Pointcut("@annotation(com.cl.fun.gag.pic.aop.annotation.SysLog)")
    @Pointcut("execution(* com.cl.fun.gag.pic.controller.*.*(..))")
    public void syslogPointcut() {

    }

    @Around("syslogPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 使用JDK的API记录接口的处理时间
        Stopwatch stopwatch = Stopwatch.createStarted();
        Date start = new Date();
        Object result = null;
        try {
            // 运行方法
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        try {
            // 获取请求的相关信息
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
                }
                if (arg instanceof HttpServletResponse) {
                    argList.add("response");
                }
//                else {
//                    argList.add(JSON.toJSON(arg));
//                }
            }

            stopwatch.stop();
            // 获取请求的处理时间
            long timeConsuming = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            // 拼接日志对象
            ESLog esLog = ESLog.builder()
                    // 插入es时需要添加主键，否则都为null会被覆盖
                    .id(snowflakeComponent.snowflakeId())
                    .costTime(timeConsuming)
                    .createBy("admin")
//                    .createTime(start)
                    .ip(ipAddr)
                    .requestParam(argList.toString())
                    .requestUrl(requestUrl)
                    .username("guest").build();
            // 从上下文中获取用户名，如果为空，则使用 guest填充
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null) {
                UserManageDetails details = (UserManageDetails) authentication.getPrincipal();
                esLog.setUsername(details.getUsername());
            }
            // 发送到消息服务器中
            sendMsg(esLog);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("参数获取失败: {}", throwable.getMessage());
        }
        return result;
    }

    /**
     *
     *  获取主机的地址信息
     *
     * */
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

    /**
     *
     *
     *  发送消息到服务器
     *
     * */
    public void sendMsg(ESLog esLog) {
        rocketMQService.sendSysLogMessage(esLog);
//        JSONObject jsonObject = new JSONObject();
//        // 消息序列化。
//        jsonObject.put("log", esLog);
//        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
//        rocketMQTemplate.convertAndSend(txDestination, message);
//        TransactionSendResult sendResult =
//                rocketMQTemplate.sendMessageInTransaction(txProduceGroup, "topic_txmsg", message, null);
//        log.debug("send msg body = {},result={}", message.getPayload(), sendResult.getSendStatus());
    }
}
