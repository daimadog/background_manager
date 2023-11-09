package com.background.manager.convert;

import com.background.manager.model.dto.request.column.AddColumnRequest;
import com.background.manager.model.dto.request.column.ModifyColumnRequest;
import com.background.manager.model.dto.response.column.ColumnDigestDTO;
import com.background.manager.model.BackgroundCmsColumn;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackgroundColumnConvertor {

    ColumnDigestDTO toColumnDigestDTO(BackgroundCmsColumn cmsColumn);

    List<ColumnDigestDTO> toColumnDigestDTOS(List<BackgroundCmsColumn> columnList);

    BackgroundCmsColumn  toBackgroundCmsColumn (AddColumnRequest request);

    BackgroundCmsColumn toBackgroundCmsColumn(ModifyColumnRequest request);
}
