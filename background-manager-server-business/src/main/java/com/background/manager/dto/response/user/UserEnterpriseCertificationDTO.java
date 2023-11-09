package com.background.manager.dto.response.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@ApiModel("用户企业认证信息DTO")
public class UserEnterpriseCertificationDTO {
    @ApiModelProperty("主键Id")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("公司法人代表名称")
    private String corporateName;

    @ApiModelProperty("统一社会信用号")
    private String taxNum;

    @ApiModelProperty("营业执照图片url地址")
    private String businessLicenseUrl;

    @ApiModelProperty("企业认证状态(0-未认证,1-认证成功,2-认证失败)")
    private Integer certificationStatus;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
