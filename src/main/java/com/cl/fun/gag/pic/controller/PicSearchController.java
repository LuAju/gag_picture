package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.entity.PicturePo;
import com.cl.fun.gag.pic.service.PicService;
import com.cl.fun.gag.pic.utils.EntityConverter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
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

    @ApiOperation("通过图片的主键ID查询")
    @GetMapping("/getPicByPrimaryKey/{id}")
    public Object getPicByPrimaryKey(@PathVariable("id") String id){
        PicturePo picDetail = picService.getPicDetail(Long.valueOf(id));
        if (picDetail!=null) {
            return CommonResult.success(EntityConverter.picturePo2Dto(picDetail));
        } else {
            return CommonResult.fail("找不到对应数据");
        }
    }
}
