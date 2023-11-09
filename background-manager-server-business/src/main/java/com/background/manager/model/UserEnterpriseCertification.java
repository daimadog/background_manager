package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@TableName("t_user_enterprise_certification")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEnterpriseCertification {

    @ApiModelProperty("主键Id")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户主键Id")
    private String userId;

    @ApiModelProperty("公司法人代表名称")
    private String corporateName;

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("统一社会信用号")
    private String taxNum;

    @ApiModelProperty("营业执照图片url地址")
    private String businessLicenseUrl;

    @ApiModelProperty("企业认证状态(0-未认证,1-认证成功,2-认证失败)")
    private Integer certificationStatus;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("修改时间")
    private Date modifyTime;
}
