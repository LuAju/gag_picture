package com.cl.fun.gag.pic.entity.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicturePo {
    private Long id;
    private String location;
    private LocalDateTime uploadTime;
}
