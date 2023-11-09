package com.background.manager.convert;

import com.background.manager.dto.request.user.AddUserRequest;
import com.background.manager.dto.request.user.ModifyUserInfoRequest;
import com.background.manager.dto.request.user.ModifyUserRequest;
import com.background.manager.dto.response.user.LoginUserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDTO;
import com.background.manager.dto.response.user.UserInfoDigestDTO;
import com.background.manager.model.BackgroundUserInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:24+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundUserInfoConvertorImpl implements BackgroundUserInfoConvertor {

    @Override
    public UserInfoDigestDTO toUserInfoDigestDTO(BackgroundUserInfo backgroundUserInfo) {
        if ( backgroundUserInfo == null ) {
            return null;
        }

        UserInfoDigestDTO userInfoDigestDTO = new UserInfoDigestDTO();

        userInfoDigestDTO.setId( backgroundUserInfo.getId() );
        userInfoDigestDTO.setUsername( backgroundUserInfo.getUsername() );
        userInfoDigestDTO.setProfile( backgroundUserInfo.getProfile() );
        userInfoDigestDTO.setBirthday( backgroundUserInfo.getBirthday() );
        userInfoDigestDTO.setSex( backgroundUserInfo.getSex() );
        userInfoDigestDTO.setEmail( backgroundUserInfo.getEmail() );
        if ( backgroundUserInfo.getStatus() != null ) {
            userInfoDigestDTO.setStatus( String.valueOf( backgroundUserInfo.getStatus() ) );
        }
        userInfoDigestDTO.setCreateTime( backgroundUserInfo.getCreateTime() );
        userInfoDigestDTO.setModifyTime( backgroundUserInfo.getModifyTime() );

        return userInfoDigestDTO;
    }

    @Override
    public List<UserInfoDigestDTO> toUserInfoDigestDTOS(List<BackgroundUserInfo> backgroundUserInfos) {
        if ( backgroundUserInfos == null ) {
            return null;
        }

        List<UserInfoDigestDTO> list = new ArrayList<UserInfoDigestDTO>( backgroundUserInfos.size() );
        for ( BackgroundUserInfo backgroundUserInfo : backgroundUserInfos ) {
            list.add( toUserInfoDigestDTO( backgroundUserInfo ) );
        }

        return list;
    }

    @Override
    public UserInfoDTO toUserInfoDTO(BackgroundUserInfo backgroundUserInfo) {
        if ( backgroundUserInfo == null ) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setUserId( backgroundUserInfo.getUserId() );
        userInfoDTO.setUsername( backgroundUserInfo.getUsername() );
        userInfoDTO.setProfile( backgroundUserInfo.getProfile() );
        userInfoDTO.setPhone( backgroundUserInfo.getPhone() );
        userInfoDTO.setEmail( backgroundUserInfo.getEmail() );
        userInfoDTO.setBirthday( backgroundUserInfo.getBirthday() );
        userInfoDTO.setSex( backgroundUserInfo.getSex() );
        userInfoDTO.setStatus( backgroundUserInfo.getStatus() );

        return userInfoDTO;
    }

    @Override
    public BackgroundUserInfo toUserInfo(ModifyUserInfoRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundUserInfo backgroundUserInfo = new BackgroundUserInfo();

        backgroundUserInfo.setId( request.getId() );
        backgroundUserInfo.setUsername( request.getUsername() );
        backgroundUserInfo.setProfile( request.getProfile() );
        backgroundUserInfo.setBirthday( request.getBirthday() );
        backgroundUserInfo.setSex( request.getSex() );
        backgroundUserInfo.setPhone( request.getPhone() );
        backgroundUserInfo.setEmail( request.getEmail() );

        return backgroundUserInfo;
    }

    @Override
    public LoginUserInfoDTO toLoginUserInfoDTO(BackgroundUserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        LoginUserInfoDTO loginUserInfoDTO = new LoginUserInfoDTO();

        loginUserInfoDTO.setUserId( userInfo.getUserId() );
        loginUserInfoDTO.setUsername( userInfo.getUsername() );
        loginUserInfoDTO.setProfile( userInfo.getProfile() );
        loginUserInfoDTO.setPhone( userInfo.getPhone() );
        loginUserInfoDTO.setEmail( userInfo.getEmail() );
        loginUserInfoDTO.setBirthday( userInfo.getBirthday() );
        loginUserInfoDTO.setSex( userInfo.getSex() );
        loginUserInfoDTO.setStatus( userInfo.getStatus() );

        return loginUserInfoDTO;
    }

    @Override
    public BackgroundUserInfo toBackgroundUserInfo(AddUserRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundUserInfo backgroundUserInfo = new BackgroundUserInfo();

        backgroundUserInfo.setUsername( request.getUsername() );
        backgroundUserInfo.setProfile( request.getProfile() );
        backgroundUserInfo.setBirthday( request.getBirthday() );
        backgroundUserInfo.setSex( request.getSex() );
        backgroundUserInfo.setPhone( request.getPhone() );
        backgroundUserInfo.setEmail( request.getEmail() );

        return backgroundUserInfo;
    }

    @Override
    public BackgroundUserInfo toBackgroundUserInfo(ModifyUserRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundUserInfo backgroundUserInfo = new BackgroundUserInfo();

        backgroundUserInfo.setId( request.getId() );
        backgroundUserInfo.setUsername( request.getUsername() );
        backgroundUserInfo.setEmail( request.getEmail() );

        return backgroundUserInfo;
    }
}
