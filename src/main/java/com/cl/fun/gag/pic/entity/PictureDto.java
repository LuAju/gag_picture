package com.cl.fun.gag.pic.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class PictureDto {
    @ApiModelProperty("主键，使用雪花算法生成")
    private String id;

    @ApiModelProperty("图片名称")
    private String picName;

    @ApiModelProperty("图片的介绍，包括出处")
    private String picDetail;

    @ApiModelProperty("图片在文件中的地址")
    private String picLocation;

    @ApiModelProperty("图片的类型，包括沙雕图、影视图、动漫图等")
    private String picType;

    @ApiModelProperty("提交人，默认是管理员")
    @Value("admin")
    private String submiter;

    @ApiModelProperty("图片的创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("图片的修改时间")
    private LocalDateTime updateTime;
}
