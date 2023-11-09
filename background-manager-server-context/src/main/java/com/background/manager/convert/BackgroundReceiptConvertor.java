package com.background.manager.convert;

import com.background.manager.model.BackgroundReceipt;
import com.background.manager.model.dto.request.receipt.AddReceiptRequest;
import com.background.manager.model.dto.request.receipt.ModifyReceiptRequest;
import com.background.manager.model.dto.response.receipt.ReceiptDTO;
import com.background.manager.model.dto.response.receipt.ReceiptDigestDTO;
import com.background.manager.model.dto.response.receipt.ReceiptRecordDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 发票装配器
 * @Author: 杜黎明
 * @Date: 2023/01/09 08:54:00
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundReceiptConvertor {

    BackgroundReceipt toBackgroundReceipt(AddReceiptRequest request);

    ReceiptDigestDTO toReceiptDigestDTO(BackgroundReceipt backgroundReceipt);

    List<ReceiptDigestDTO> toReceiptDigestDTOS(List<BackgroundReceipt> records);

    BackgroundReceipt toBackgroundReceipt(ModifyReceiptRequest request);

    ReceiptDTO toReceiptDTO(BackgroundReceipt backgroundReceipt);

    ReceiptRecordDTO toReceiptRecordDTO(BackgroundReceipt backgroundReceipt);

    List<ReceiptRecordDTO> toReceiptRecordDTOS(List<BackgroundReceipt> records);
}
