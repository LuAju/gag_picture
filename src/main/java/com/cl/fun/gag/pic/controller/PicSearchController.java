package com.cl.fun.gag.pic.controller;

import com.cl.fun.gag.pic.aop.annotation.SearchCounter;
import com.cl.fun.gag.pic.aop.annotation.SysLog;
import com.cl.fun.gag.pic.common.result.CommonResult;
import com.cl.fun.gag.pic.entity.PicDetailSearchDto;
import com.cl.fun.gag.pic.entity.PicNameSearchDto;
import com.cl.fun.gag.pic.entity.PicturePo;
import com.cl.fun.gag.pic.service.PicService;
import com.cl.fun.gag.pic.utils.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
public class PicSearchController {
    @Autowired
    private PicService picService;

    @ApiOperation("通过图片名称简单搜索")
    @PostMapping("/getPicsByName")
    @SysLog
    @SearchCounter
    public Object getPicsByName(@RequestBody PicNameSearchDto picNameSearchDto) {
        // TODO 将搜索添加打redis里
        List<PicturePo> picListByPicName = picService.getPicListByPicName(picNameSearchDto.getPicName(), picNameSearchDto.getPage(), picNameSearchDto.getSize());
        log.info("用户搜索{}，返回{}条数据", picNameSearchDto.getPicName(), picListByPicName.size());
        return CommonResult.success(picListByPicName);
    }

    @ApiOperation("通过图片的主键ID查询")
    @GetMapping("/getPicByPrimaryKey/{id}")
    @SysLog
    public Object getPicByPrimaryKey(@PathVariable("id") String id) {
        PicturePo picDetail = picService.getPicDetail(Long.valueOf(id));
        if (picDetail != null) {
            return CommonResult.success(EntityConverter.picturePo2Dto(picDetail));
        } else {
            return CommonResult.fail("找不到对应数据");
        }
    }

    @ApiOperation("根据图片详情获取")
    @PostMapping("/getPicsByDetail")
    @SearchCounter
    @SysLog
    public Object getPicsByDetail(@RequestBody PicDetailSearchDto picDetailSearchDto) {
        // TODO 将搜索添加打redis里
        List<PicturePo> picListByPicDetail = picService.getPicListByPicDetail(picDetailSearchDto.getPicDetail(), picDetailSearchDto.getPage(), picDetailSearchDto.getSize());
        log.info("用户搜索{}，返回{}条数据", picDetailSearchDto.getPicDetail(), picListByPicDetail.size());
        return CommonResult.success(picListByPicDetail);
    }

//    @ApiOperation("根据图片详情获取")
//    @PostMapping("/getPicsByDetailOrName")
//    @SearchCounter
//    @SysLog
//    public Object getPicsByDetailOrName(@RequestBody PicDetailSearchDto picDetailSearchDto) {
//        // TODO 将搜索添加打redis里
//        List<PicturePo> picListByPicDetail = picService.getPicListByPicDetail(picDetailSearchDto.getPicDetail(), picDetailSearchDto.getPage(), picDetailSearchDto.getSize());
//        log.info("用户搜索{}，返回{}条数据", picDetailSearchDto.getPicDetail(), picDetailSearchDto.getSize());
//        return CommonResult.success(picListByPicDetail);
//    }

    @ApiOperation("根据图片详情获取")
    @PostMapping("/getPicsByDetailOrName")
    @SearchCounter
    @SysLog
    public Object getPicListByPicDetailOrName(@RequestBody PicDetailSearchDto picDetailSearchDto) {
        // TODO 将搜索添加打redis里
        List<PicturePo> picListByPicDetail = picService.getPicListByPicDetailOrName(picDetailSearchDto.getPicDetail(), picDetailSearchDto.getPage(), picDetailSearchDto.getSize());
        log.info("用户搜索{}，返回{}条数据", picDetailSearchDto.getPicDetail(), picListByPicDetail.size());
        return CommonResult.success(picListByPicDetail);
    }

    @ApiOperation("搜索热度")
    @GetMapping("/getTopPicList")
    public Object getTopPicList() {
        // TODO 从redis里获取搜索次数
        List<String> topId = new ArrayList<>();
        List<PicturePo> topPicList = picService.getTopPicList(topId);
        return CommonResult.success(topPicList);
    }

    // todo 今日新图
    // 根据时间显示新添加的图 最好从redis里获取
}
