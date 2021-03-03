package com.cl.fun.gag.pic.entity.sql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 *
 *  数据库中的图片对象，主要是存储图片的地址和上传时间。
 *
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("上传的图片持久化日志")
public class PicturePo {
    @ApiModelProperty(value="主键id", required = true)
    private Long id;
    @ApiModelProperty(value="文件的服务器相对位置", required = true)
    private String location;
    @ApiModelProperty(value="上传时间")
    private LocalDateTime uploadTime;
}
