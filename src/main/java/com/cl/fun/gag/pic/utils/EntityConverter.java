package com.cl.fun.gag.pic.utils;

import com.cl.fun.gag.pic.entity.PictureDto;
import com.cl.fun.gag.pic.entity.PicturePo;

public class EntityConverter {
    public static PicturePo pictureDto2Po(PictureDto pictureDto){
        PicturePo build = PicturePo.builder()
                .picName(pictureDto.getPicName())
                .picDetail(pictureDto.getPicDetail())
                .picLocation(pictureDto.getPicLocation())
                .picType(pictureDto.getPicType()).build();
        return build;
    }

    public static PictureDto picturePo2Dto(PicturePo picturePo){
        PictureDto build = PictureDto.builder()
                .picDetail(picturePo.getPicDetail())
                .picLocation(picturePo.getPicLocation())
                .picName(picturePo.getPicName())
                .picType(picturePo.getPicType())
                .id(picturePo.getId()).build();
        return build;
    }
}
