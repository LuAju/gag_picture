package com.cl.fun.gag.pic.service;

import com.cl.fun.gag.pic.entity.PicturePo;

import java.util.List;

public interface PicService {
    // 根据id名，查询所有的信息
    PicturePo getPicDetail();

    // 分页查询数据
    List<PicturePo> getPicList();

    // 根据热度查询，查询前n条
    List<PicturePo> getTopPicList(List<String> picIds);

    // 查询最新的,带分页
    List<PicturePo> getRecentPics();

    // 存储图片，返回对象给前台
    PicturePo savePicturePo(PicturePo picturePo);


}
