package com.cl.fun.gag.pic.config;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.customizeexception.SizeZeroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(value =Exception.class)
//    public String exceptionHandler(Exception e){
//        System.out.println("未知异常！原因是:"+e);
//        return e.getMessage();
//    }

    @ExceptionHandler(value = SizeZeroException.class)
    public Object exceptionHandler(Exception e) {
        System.out.println("未知异常！原因是:" + e);
        return CommonResult.error(e.getMessage());
    }


}
