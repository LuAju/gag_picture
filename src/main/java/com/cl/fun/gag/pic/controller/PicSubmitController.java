package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.component.SnowflakeComponent;
import com.cl.fun.gag.pic.entity.PictureDto;
import com.cl.fun.gag.pic.entity.PicturePo;
import com.cl.fun.gag.pic.service.PicService;
import com.cl.fun.gag.pic.utils.EntityConverter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api("提交图片信息接口")
@RequestMapping("/submit")
public class PicSubmitController {

    @Autowired
    private PicService picService;

    @Autowired
    private SnowflakeComponent snowflakeComponent;

    @PostMapping("/picInfo")
    public Object submitPicInfo(@RequestBody PictureDto pictureDto){
        PicturePo picturePo = EntityConverter.pictureDto2Po(pictureDto);
        picturePo.setId(snowflakeComponent.snowflakeId());
        picturePo.setCreateTime(new Date());
        picturePo.setHasAudited(false);
        picturePo.setIsDisplaying(false);
        // 从登录信息中获取图片
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        picturePo.setSubmitter(username);
        PictureDto save = EntityConverter.picturePo2Dto(picService.savePicturePo(picturePo));
        return CommonResult.success(save);
    }
}
