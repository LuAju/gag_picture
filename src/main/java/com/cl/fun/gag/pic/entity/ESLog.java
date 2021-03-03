package com.cl.fun.gag.pic.entity;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="用户浏览日志类",description = "用于记录用户的浏览日志，经由MQ发送给日志的处理端")
public class ESLog {
    @Id
    private String id;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(type = FieldType.Date, index = false, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();
    /**
     * 时间戳 查询时间范围时使用
     */
    @ApiModelProperty(name = "浏览的起始时间")
    private Long timeMillis = System.currentTimeMillis();
    /**
     * 方法操作名称
     */
    private String name;
    /**
     * 日志类型
     */
    private Integer logType;
    /**
     * 请求链接
     */
    private String requestUrl;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 请求用户
     */
    private String username;
    /**
     * ip
     */
    private String ip;
    /**
     * 花费时间
     */
    private Long costTime;

}
