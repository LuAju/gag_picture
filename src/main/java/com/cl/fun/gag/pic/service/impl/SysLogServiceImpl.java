package com.cl.fun.gag.pic.service.impl;

import com.cl.fun.gag.pic.dao.SyslogRepository;
import com.cl.fun.gag.pic.entity.ESLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl {
    @Autowired
    private SyslogRepository syslogRepository;

    public void save(ESLog esLog) {
        syslogRepository.save(esLog);
    }
}
