package com.cl.fun.gag.pic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicNameSearchDto {
    private int page;

    private int size;

    private String picName;

}
