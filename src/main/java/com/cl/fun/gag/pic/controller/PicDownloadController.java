package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.customizeexception.NoSuchLocationFileException;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

@RestController
public class PicDownloadController {
    @Value("${filePathPrefix}")
    private String filePathPrefix;

    @GetMapping("/fileDownload")
        public void fileDownload(@RequestParam("fileName") String fileName, HttpServletResponse response){
        try {
            // 根据全路径，获取文件
            Files.copy(Paths.get(filePathPrefix + fileName), response.getOutputStream());
        } catch (NoSuchFileException e) {
            throw new NoSuchLocationFileException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
