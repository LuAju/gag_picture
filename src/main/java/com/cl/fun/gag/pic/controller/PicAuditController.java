package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.service.PicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
@Api(value = "图片审核接口",description = "审核图片")
public class PicAuditController {
    @Autowired
    private PicService picService;

    @RequestMapping("/{id}")
    @ApiOperation("审核接口")
    @ApiImplicitParam(name="id",value = "待审核的主键Id")
    public Object audit(@ApiParam("待审核的主键Id") @PathVariable("id") Long id){
        boolean b = picService.saveAuditedPicturePo(id);
        if (b) {
            return CommonResult.success("审核成功");
        } else {
            return CommonResult.fail("审核失败");
        }
    }
}
