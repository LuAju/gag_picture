package com.cl.fun.gag.pic.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(indexName = "picture",type="picture")
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class PicturePo {
    @Id
    @ApiModelProperty("主键，使用雪花算法生成")
    private Long id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    @ApiModelProperty("图片名称")
    private String picName;

    @ApiModelProperty("图片的介绍，包括出处")
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String picDetail;

    @ApiModelProperty("图片在文件中的地址")
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String picLocation;

    @ApiModelProperty("图片的类型，包括沙雕图、影视图、动漫图等")
    @Field(analyzer = "ik_smart", type = FieldType.Keyword)
    private String picType;

    @ApiModelProperty("提交人，默认是管理员")
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    @Value("admin")
    private String submitter;

    @ApiModelProperty("图片的创建时间")
    @Field(analyzer = "ik_smart", type = FieldType.Date)
    private Date createTime;

    @ApiModelProperty("图片已经被审核")
    @Field( type = FieldType.Boolean)
    private Boolean hasAudited;

    @ApiModelProperty("图片处于展示状态")
    @Field( type = FieldType.Boolean)
    private Boolean isDisplaying;

    @ApiModelProperty("图片的修改时间")
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private Date updateTime;

}
