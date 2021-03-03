package com.cl.fun.gag.pic.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "图片名称搜索DTO",description = "根据图片名称搜索")
public class PicNameSearchDto {
    @ApiModelProperty(value = "页码")
    private int page;
    @ApiModelProperty(value = "页大小")
    private int size;
    @ApiModelProperty(value = "图片名称")
    private String picName;

}
