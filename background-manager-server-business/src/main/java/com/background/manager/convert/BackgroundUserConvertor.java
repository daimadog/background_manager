package com.background.manager.convert;

import com.background.manager.business.dto.IBackgroundUserDTO;
import com.background.manager.dto.request.user.AddUserRequest;
import com.background.manager.model.BackgroundUser;
import org.mapstruct.Mapper;

/**
 * @Description: 后台用户转换器
 * @Author: 杜黎明
 * @Date: 2022/10/08 16:13:20
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundUserConvertor {

    BackgroundUser toBackgroundUser(AddUserRequest request);

    IBackgroundUserDTO toIBackgroundUserDTO(BackgroundUser backgroundUser);
}
