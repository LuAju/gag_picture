package com.cl.fun.gag.pic.config;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.customizeexception.ESPictureIdNotFoundException;
import com.cl.fun.gag.pic.customizeexception.NameDuplicateException;
import com.cl.fun.gag.pic.customizeexception.NoSuchLocationFileException;
import com.cl.fun.gag.pic.customizeexception.SizeZeroException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.NoSuchFileException;

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
    public Object nameDuplicateExceptionHandler(Exception e) {
        return CommonResult.error("注册失败，用户名重复");
    }

    @ExceptionHandler(value = ESPictureIdNotFoundException.class)
    public Object eSPictureIdNotFoundExceptionHandler(Exception e) {
        return CommonResult.error("无法从es中获取对应主键的图片");
    }


    @ExceptionHandler(value = NoSuchLocationFileException.class)
    public Object noSuchFileExceptionHandler(Exception e) {
        return CommonResult.error("无法根据文件地址获取指定图片");
    }
}
