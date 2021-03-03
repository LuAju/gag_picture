package com.cl.fun.gag.pic.config;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.customizeexception.NameDuplicateException;
import com.cl.fun.gag.pic.customizeexception.SizeZeroException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

//    @ExceptionHandler(value =Exception.class)
//    public String exceptionHandler(Exception e){
//        System.out.println("未知异常！原因是:"+e);
//        return e.getMessage();
//    }

    @ExceptionHandler(value = SizeZeroException.class)
    public Object exceptionHandler(Exception e) {
        return CommonResult.error(e.getMessage());
    }

    @ExceptionHandler(value = NameDuplicateException.class)
    public Object NameDuplicateExceptionHandler(Exception e) {
        return CommonResult.error("注册失败，用户名重复");
    }
}
