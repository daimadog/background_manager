package com.background.manager.convert;

import com.background.manager.model.AiOrderApplicationRecord;
import com.background.manager.model.dto.response.order.AiOrderApplicationRecordDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:39+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class AiOrderApplicationRecordConvertorImpl implements AiOrderApplicationRecordConvertor {

    @Override
    public AiOrderApplicationRecordDTO toAiOrderApplicationRecordDTO(AiOrderApplicationRecord aiOrderApplicationRecord) {
        if ( aiOrderApplicationRecord == null ) {
            return null;
        }

        AiOrderApplicationRecordDTO aiOrderApplicationRecordDTO = new AiOrderApplicationRecordDTO();

        aiOrderApplicationRecordDTO.setId( aiOrderApplicationRecord.getId() );
        aiOrderApplicationRecordDTO.setOrderId( aiOrderApplicationRecord.getOrderId() );
        aiOrderApplicationRecordDTO.setOrderSn( aiOrderApplicationRecord.getOrderSn() );
        aiOrderApplicationRecordDTO.setProductName( aiOrderApplicationRecord.getProductName() );
        aiOrderApplicationRecordDTO.setFinalCost( aiOrderApplicationRecord.getFinalCost() );
        aiOrderApplicationRecordDTO.setApplyType( aiOrderApplicationRecord.getApplyType() );
        aiOrderApplicationRecordDTO.setApplyStatus( aiOrderApplicationRecord.getApplyStatus() );
        aiOrderApplicationRecordDTO.setApplyTime( aiOrderApplicationRecord.getApplyTime() );
        aiOrderApplicationRecordDTO.setRenewalTime( aiOrderApplicationRecord.getRenewalTime() );
        aiOrderApplicationRecordDTO.setRemark( aiOrderApplicationRecord.getRemark() );

        return aiOrderApplicationRecordDTO;
    }
}
