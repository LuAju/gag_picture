package com.cl.fun.gag.pic.aop.aspect;

import com.cl.fun.gag.pic.entity.PicDetailSearchDto;
import com.cl.fun.gag.pic.entity.PicNameSearchDto;
import com.cl.fun.gag.pic.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Aspect
@Component
public class SearchCounterAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.cl.fun.gag.pic.aop.annotation.SearchCounter)")
    public void searchCounter(){}

    @Around("searchCounter()")
    public Object around(ProceedingJoinPoint point) throws Throwable{

        // 获取参数
        Object[] args = point.getArgs();
        String param = "";
        // 当参数类型是图片名对象时
        if (args[0] instanceof PicNameSearchDto) {
            param = ((PicNameSearchDto) args[0]).getPicName();
        }
        // 当参数类型是图片细节对象时
        if (args[0] instanceof PicDetailSearchDto){
            param = ((PicDetailSearchDto) args[0]).getPicDetail();
        }
        // 使用redis有序集合，存储热度值
        if (!StringUtils.isEmpty(param)){
            redisUtil.zsetIncre("heat", param, 1.0);
        }
        // 继续执行方法
        Object result = point.proceed();
        return result;
    }

}
