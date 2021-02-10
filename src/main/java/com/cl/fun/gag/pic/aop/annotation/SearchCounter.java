package com.cl.fun.gag.pic.aop.annotation;

import java.lang.annotation.*;

/**
 *
 *   搜索计数器
 *
 * */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SearchCounter {
    String value() default "";
}


