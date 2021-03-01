package com.cl.fun.gag.pic.entity.sql;

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
public class PicturePo {
    private Long id;
    private String location;
    private LocalDateTime uploadTime;
}
