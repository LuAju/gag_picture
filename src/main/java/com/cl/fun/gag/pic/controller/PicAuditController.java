package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class PicAuditController {
    @Autowired
    private PicService picService;

    @RequestMapping("/{id}")
    public Object audit(@PathVariable("id") Long id){
        boolean b = picService.saveAuditedPicturePo(id);
        if (b) {
            return CommonResult.success("审核成功");
        } else {
            return CommonResult.fail("审核失败");
        }
    }
}
