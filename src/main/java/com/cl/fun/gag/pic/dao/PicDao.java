package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.sql.PicturePo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PicDao {
    int insert(PicturePo picturePo);
}
