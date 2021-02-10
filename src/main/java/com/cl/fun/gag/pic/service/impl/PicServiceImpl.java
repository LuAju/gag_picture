package com.cl.fun.gag.pic.service.impl;

import com.cl.fun.gag.pic.dao.PicRepository;
import com.cl.fun.gag.pic.entity.PicturePo;
import com.cl.fun.gag.pic.service.PicService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    private PicRepository picRepository;

    @Override
    // 使用自带的api进行查询操作，返回的是Optional对象，获取一下
    public PicturePo getPicDetail(Long id) {
        Optional<PicturePo> byId = picRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        } else {
            return null;
        }
    }

    @Override
    public List<PicturePo> getPicList() {
        return null;
    }

    @Override
    public List<PicturePo> getPicListByPicName(String picName,int page,int size) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(picName,"picName" ))
                .withPageable(PageRequest.of(page,size))
                .build();
        Page<PicturePo> search = picRepository.search(nativeSearchQuery);
        List<PicturePo> content = search.getContent();
        return content;
    }

    @Override
    public List<PicturePo> getPicListByPicDetail(String picDetail,int page,int size) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(picDetail,"picName" ))
                .withPageable(PageRequest.of(page,size))
                .build();
        Page<PicturePo> search = picRepository.search(nativeSearchQuery);
        List<PicturePo> content = search.getContent();
        return content;
    }

    @Override
    public List<PicturePo> getTopPicList(List<String> picIds) {
        String[] ids = picIds.toArray(new String[picIds.size()]);
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds(ids);
        Iterable<PicturePo> search = picRepository.search(queryBuilder);
        List<PicturePo> picturePos = new ArrayList<>();
        for (PicturePo picturePo : search) {
            picturePos.add(picturePo);
        }
        return picturePos;
    }

    @Override
    public List<PicturePo> getRecentPics(int page) {
        return getRecentPics(page,10);
    }

    @Override
    public List<PicturePo> getRecentPics(int page,int size) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(page,10))
                .build();
        Page<PicturePo> search = picRepository.search(nativeSearchQuery);
        List<PicturePo> content = search.getContent();
        return content;
    }

    @Override
    public PicturePo savePicturePo(PicturePo picturePo) {
        PicturePo save = picRepository.save(picturePo);
        return save;
    }
}
