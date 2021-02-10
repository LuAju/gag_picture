package com.cl.fun.gag.pic.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class PicDownloadController {
    @Value("${filePathPrefix}")
    private String filePathPrefix;

    @GetMapping("/fileDownload")
    public void fileDownload(@RequestParam("fileName") String fileName, HttpServletResponse response){
        try {
            Files.copy(Paths.get(filePathPrefix + fileName), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
