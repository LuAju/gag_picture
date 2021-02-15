package com.cl.fun.gag.pic.entity;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
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
