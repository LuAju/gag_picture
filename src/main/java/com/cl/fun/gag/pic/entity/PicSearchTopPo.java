package com.cl.fun.gag.pic.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("搜索热度")
public class PicSearchTopPo {
    private String name;

    private Long heat;

}
