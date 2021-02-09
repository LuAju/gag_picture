package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.service.PicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/search")
public class PicSearchController {
    @Autowired
    private PicService picService;

    @ApiOperation("测试")
    @GetMapping("/")
    public Object get(){
        int a = 1 / 0;
        return "ss";
    }

    @ApiOperation("通过图片名称简单搜索" )
    @GetMapping("/getPicsByName")
    public Object getPicsByName(){
        return null;
    }
}
