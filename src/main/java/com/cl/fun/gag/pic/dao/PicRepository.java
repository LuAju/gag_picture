package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.PicturePo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface PicRepository extends ElasticsearchRepository<PicturePo,Long> {
}
