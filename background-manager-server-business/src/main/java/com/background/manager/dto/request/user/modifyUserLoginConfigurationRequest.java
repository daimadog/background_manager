package com.background.manager.dto.request.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.validation.constraints.Max;

@Data
@ApiModel("修改用户登录配置请求体")
public class modifyUserLoginConfigurationRequest {

    @TableId(type = IdType.AUTO)
    private Long id;

    @Max(value = 20,message = "用户登录密码错误次数最大为20次")
    private Integer loginFailNum;

    @Max(value = 30,message = "锁定时间最大为30分钟")
    private Integer lockTime;

}
