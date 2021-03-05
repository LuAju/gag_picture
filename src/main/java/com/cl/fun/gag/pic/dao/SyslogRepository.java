package com.cl.fun.gag.pic.dao;

import com.cl.fun.gag.pic.entity.ESLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface SyslogRepository extends ElasticsearchRepository<ESLog, Long> {
}
