package com.background.manager.convert;

import com.background.manager.dto.request.user.AddUserRequest;
import com.background.manager.dto.request.user.ImproveUserInfoRequest;
import com.background.manager.dto.request.user.ModifyUserInfoRequest;
import com.background.manager.dto.request.user.ModifyUserRequest;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDigestDTO;
import com.background.manager.model.BackgroundUserInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundUserInfoConvertor {

    UserInfoDigestDTO toUserInfoDigestDTO(BackgroundUserInfo backgroundUserInfo);

    List<UserInfoDigestDTO> toUserInfoDigestDTOS(List<BackgroundUserInfo> backgroundUserInfos);

    UserInfoDTO toUserInfoDTO(BackgroundUserInfo backgroundUserInfo);

    BackgroundUserInfo toUserInfo(ModifyUserInfoRequest request);

    LoginUserInfoDTO toLoginUserInfoDTO(BackgroundUserInfo userInfo);

    BackgroundUserInfo toBackgroundUserInfo(AddUserRequest request);

    BackgroundUserInfo toBackgroundUserInfo(ModifyUserRequest request);
}
