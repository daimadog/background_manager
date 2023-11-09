package com.background.manager.convert;

import com.background.manager.model.dto.request.offlineRegistration.AddRegistrationRequest;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDTO;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDigestDTO;
import com.background.manager.model.BackgroundOfflineMessage;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 离线登记转换器
 * @Author: 杜黎明
 * @Date: 2022/11/03 09:46:34
 * @Version: 1.0.0
 */
@Mapper(componentModel ="spring")
public interface BackgroundOfflineMessageConvertor {

    BackgroundOfflineMessage toBackgroundOfflineMessage(AddRegistrationRequest request);

    OfflineMessageDigestDTO toOfflineMessageDTO(BackgroundOfflineMessage backgroundOfflineMessage);

    List<OfflineMessageDigestDTO> toOfflineMessageDTOS(List<BackgroundOfflineMessage> records);

    OfflineMessageDTO toBackgroundOfflineMessage(BackgroundOfflineMessage backgroundOfflineMessage);
}
