package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("资源申请实体类")
public class ResourceApplicationForm {

    @ApiModelProperty("申请表编号")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("单位名称")
    private String companyName;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目类型(0-AI智算,1-HPC超算)")
    private String projectType;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("项目基本介绍")
    private String projectIntroduce;

    @ApiModelProperty("项目需求预测")
    private String projectRequire;

    @ApiModelProperty("申请结论(运营公司填写)")
    private String conclusion;

    @ApiModelProperty("申请用户id")
    private String userId;

    @ApiModelProperty("申请时间")
    private LocalDateTime applicantTime;

    @ApiModelProperty("申请状态")
    private Integer applicantStatus;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private String modifier;

    @ApiModelProperty("更新日期")
    private LocalDateTime modifyTime;

}
