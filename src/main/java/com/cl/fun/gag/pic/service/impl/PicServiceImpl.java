package com.cl.fun.gag.pic.service.impl;

import com.cl.fun.gag.pic.dao.PicRepository;
import com.cl.fun.gag.pic.entity.PicturePo;
import com.cl.fun.gag.pic.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    private PicRepository picRepository;

    @Override
    public PicturePo getPicDetail(Long id) {
        return picRepository.findById(id).get();
    }

    @Override
    public List<PicturePo> getPicList() {
        return null;
    }

    @Override
    public List<PicturePo> getTopPicList(List<String> picIds) {
        return null;
    }

    @Override
    public List<PicturePo> getRecentPics() {
        return null;
    }

    @Override
    public PicturePo savePicturePo(PicturePo picturePo) {
        PicturePo save = picRepository.save(picturePo);
        return save;
    }
}
