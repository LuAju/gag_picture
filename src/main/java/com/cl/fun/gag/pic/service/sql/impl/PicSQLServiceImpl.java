package com.cl.fun.gag.pic.service.sql.impl;

import com.cl.fun.gag.pic.dao.PicDao;
import com.cl.fun.gag.pic.entity.sql.PicturePo;
import com.cl.fun.gag.pic.service.sql.PicSQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicSQLServiceImpl implements PicSQLService {

    @Autowired
    private PicDao picDao;

    @Override
    public int insert(PicturePo picturePo) {
        return picDao.insert(picturePo);
    }
}
