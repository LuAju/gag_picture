package com.cl.fun.gag.pic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
@Document(indexName = "syslog",type="syslog")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="用户浏览日志类",description = "用于记录用户的浏览日志，经由MQ发送给日志的处理端")
// 如果ES没有相关索引，则创建，如果有同名索引，可能会因为索引的mapper不同而报错
// Mapper for [createTime] conflicts with existing mapping in other types:
public class ESLog {
    @Id
    private Long id;
    /**
     * 创建者
     */
    @Field(type = FieldType.Text)
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    @Field(type = FieldType.Date)
    @Field(type = FieldType.Date, index = false, format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();
    /**
     * 时间戳 查询时间范围时使用
     */
    @ApiModelProperty(name = "浏览的起始时间")
    private Long timeMillis = System.currentTimeMillis();
    /**
     * 方法操作名称
     */
    @Field(type = FieldType.Text)
    private String name;
    /**
     * 日志类型
     */
    @Field(type = FieldType.Long)
    private Integer logType;
    /**
     * 请求链接
     */
    @Field(type = FieldType.Text)
    private String requestUrl;
    /**
     * 请求类型
     */
    @Field(type = FieldType.Text)
    private String requestType;
    /**
     * 请求参数
     */
    @Field(type = FieldType.Text)
    private String requestParam;
    /**
     * 请求用户
     */
    @Field(type = FieldType.Text)
    private String username;
    /**
     * ip
     */
    @Field(type = FieldType.Text)
    private String ip;
    /**
     * 花费时间
     */
    @Field(type = FieldType.Long)
    private Long costTime;

}
