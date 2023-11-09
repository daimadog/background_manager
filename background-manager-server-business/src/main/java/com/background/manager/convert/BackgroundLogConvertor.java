package com.background.manager.convert;

import com.background.manager.dto.response.log.LoginLogDigestDTO;
import com.background.manager.dto.response.log.OperationLogDigestDTO;
import com.background.manager.model.BackgroundLoginLog;
import com.background.manager.model.BackgroundOperationLog;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 日志操作转换器
 * @Author: 杜黎明
 * @Date: 2022/10/14 09:49:01
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundLogConvertor {

    LoginLogDigestDTO LoginLogDigestDTO (BackgroundLoginLog backgroundLoginLog);

    List<LoginLogDigestDTO> LoginLogDigestDTOs(List<BackgroundLoginLog> records);

    OperationLogDigestDTO  toOperationLogDTO(BackgroundOperationLog backgroundOperationLog);

    List<OperationLogDigestDTO> toOperationLogDTOS(List<BackgroundOperationLog> records);
}
