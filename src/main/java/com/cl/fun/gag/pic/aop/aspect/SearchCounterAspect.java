package com.cl.fun.gag.pic.aop.aspect;

import com.cl.fun.gag.pic.entity.PicDetailSearchDto;
import com.cl.fun.gag.pic.entity.PicNameSearchDto;
import com.cl.fun.gag.pic.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
@Component
public class SearchCounterAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.cl.fun.gag.pic.aop.annotation.SearchCounter)")
    public void searchCounter(){}

    @Around("searchCounter()")
    public Object around(ProceedingJoinPoint point) throws Throwable{

        Object[] args = point.getArgs();
        String param = "";
        if (args[0] instanceof PicNameSearchDto) {
            param = ((PicNameSearchDto) args[0]).getPicName();
        }
        if (args[0] instanceof PicDetailSearchDto){
            param = ((PicDetailSearchDto) args[0]).getPicDetail();
        }
        if (!StringUtils.isEmpty(param)){
            redisUtil.zsetIncre("heat", param, 1.0);
        }
        Object result = point.proceed();
        return result;
    }

}
