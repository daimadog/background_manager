package com.background.manager.convert;

import com.background.manager.model.BackgroundAnnouncement;
import com.background.manager.model.dto.request.announcement.AddAnnouncementRequest;
import com.background.manager.model.dto.response.announcement.BackgroundAnnouncementDigestDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 公告管理映射器
 * @Author: 杜黎明
 * @Date: 2023/02/23 14:06:23
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundAnnouncementConvertor {

    BackgroundAnnouncement toBackgroundAnnouncement(AddAnnouncementRequest request);

    BackgroundAnnouncementDigestDTO toBackgroundAnnouncementDigestDTO(BackgroundAnnouncement backgroundAnnouncement);

    List<BackgroundAnnouncementDigestDTO> toBackgroundAnnouncementDigestDTOs(List<BackgroundAnnouncement> records);
}
