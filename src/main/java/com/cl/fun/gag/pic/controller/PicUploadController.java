package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.component.SnowflakeComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Api("图片搜索接口")
@Slf4j
public class PicUploadController {

    @Autowired
    private SnowflakeComponent snowflakeComponent;

    @Value("${filePathPrefix}")
    private String filePathPrefix;

    @PostMapping(value = "/fileUpload")
    @ApiOperation("图片上传接口，返回图片的地址")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = snowflakeComponent.snowflakeId()+""; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        return fileName;
    }
}
