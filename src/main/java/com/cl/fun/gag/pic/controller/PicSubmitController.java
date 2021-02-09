package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.entity.PictureDto;
import com.cl.fun.gag.pic.entity.PicturePo;
import com.cl.fun.gag.pic.service.PicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api("提交图片信息接口")
@RequestMapping("/submit")
public class PicSubmitController {

    @Autowired
    private PicService picService;

    @PostMapping("/picInfo")
    public Object submitPicInfo(@RequestBody PictureDto pictureDto){
//        PicturePo picturePo = picService.savePicturePo(pictureDto);

        return true;
    }
}
