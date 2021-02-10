package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.component.SnowflakeComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api("图片搜索接口")
@Slf4j
public class PicUploadController {

    @Autowired
    private SnowflakeComponent snowflakeComponent;

    @Value("${filePathPrefix}")
    private String filePathPrefix;

    @PostMapping(value = "/fileUpload")
    @ApiOperation("图片上传接口，返回图片的地址")
    public Object uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        int maxSize = 1024 * 1024 * 5;    //上传最大为1MB
        if (file.getSize()>maxSize) {
            return CommonResult.fail("文件长度不能大于5M");
        }
        File targetFile = null;

        // 添加服务器前缀
        StringBuffer serverPrefixSB = new StringBuffer();
        serverPrefixSB = serverPrefixSB.append(filePathPrefix);

        // 获取当前时间,添加时间前缀
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        Month month = now.getMonth();
        int dayOfMonth = now.getDayOfMonth();
        StringBuffer timePrefixStringBuffer = new StringBuffer();
        timePrefixStringBuffer = timePrefixStringBuffer.append("/").append(year).append("/").append(month.getValue()).append("/").append(dayOfMonth).append("/");

        // 先创建文件所在的文件夹
        File parentFile = new File(serverPrefixSB.append(timePrefixStringBuffer).toString());
        //如果文件夹不存在则创建
        if (!parentFile.exists() && !parentFile.isDirectory()) {
            parentFile.mkdirs();
        }


        // 获取文件的后缀名
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        StringBuffer fileNameSB = new StringBuffer();
        String fileName = fileNameSB.append(snowflakeComponent.snowflakeId()).append(fileSuffix).toString();

        //将图片存入文件夹
        targetFile = new File(parentFile, fileName);

        //todo 持久化这条数据

        try {
            //将上传的文件写到服务器上指定的文件。
            file.transferTo(targetFile);
            return CommonResult.success(timePrefixStringBuffer.append(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error("上传服务器报错：" + e.getMessage());
        }
    }
}
