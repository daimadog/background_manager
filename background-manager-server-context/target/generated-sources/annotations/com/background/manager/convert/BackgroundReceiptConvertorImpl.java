package com.background.manager.convert;

import com.background.manager.model.BackgroundReceipt;
import com.background.manager.model.dto.request.receipt.AddReceiptRequest;
import com.background.manager.model.dto.request.receipt.ModifyReceiptRequest;
import com.background.manager.model.dto.response.receipt.ReceiptDTO;
import com.background.manager.model.dto.response.receipt.ReceiptDigestDTO;
import com.background.manager.model.dto.response.receipt.ReceiptRecordDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundReceiptConvertorImpl implements BackgroundReceiptConvertor {

    @Override
    public BackgroundReceipt toBackgroundReceipt(AddReceiptRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundReceipt backgroundReceipt = new BackgroundReceipt();

        backgroundReceipt.setTitle( request.getTitle() );
        backgroundReceipt.setTaxIdNumber( request.getTaxIdNumber() );
        backgroundReceipt.setAccountBank( request.getAccountBank() );
        backgroundReceipt.setAccountNumber( request.getAccountNumber() );
        backgroundReceipt.setRegisterAddress( request.getRegisterAddress() );
        backgroundReceipt.setCompanyPhone( request.getCompanyPhone() );
        backgroundReceipt.setAmount( request.getAmount() );
        backgroundReceipt.setType( request.getType() );
        backgroundReceipt.setReceiver( request.getReceiver() );
        backgroundReceipt.setReceiverAddress( request.getReceiverAddress() );
        backgroundReceipt.setPostalCode( request.getPostalCode() );
        backgroundReceipt.setReceiverPhone( request.getReceiverPhone() );
        backgroundReceipt.setRemarks( request.getRemarks() );

        return backgroundReceipt;
    }

    @Override
    public ReceiptDigestDTO toReceiptDigestDTO(BackgroundReceipt backgroundReceipt) {
        if ( backgroundReceipt == null ) {
            return null;
        }

        ReceiptDigestDTO receiptDigestDTO = new ReceiptDigestDTO();

        receiptDigestDTO.setId( backgroundReceipt.getId() );
        receiptDigestDTO.setReceiptTime( backgroundReceipt.getReceiptTime() );
        receiptDigestDTO.setType( backgroundReceipt.getType() );
        receiptDigestDTO.setStatus( backgroundReceipt.getStatus() );
        receiptDigestDTO.setTitle( backgroundReceipt.getTitle() );
        receiptDigestDTO.setRemarks( backgroundReceipt.getRemarks() );

        return receiptDigestDTO;
    }

    @Override
    public List<ReceiptDigestDTO> toReceiptDigestDTOS(List<BackgroundReceipt> records) {
        if ( records == null ) {
            return null;
        }

        List<ReceiptDigestDTO> list = new ArrayList<ReceiptDigestDTO>( records.size() );
        for ( BackgroundReceipt backgroundReceipt : records ) {
            list.add( toReceiptDigestDTO( backgroundReceipt ) );
        }

        return list;
    }

    @Override
    public BackgroundReceipt toBackgroundReceipt(ModifyReceiptRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundReceipt backgroundReceipt = new BackgroundReceipt();

        backgroundReceipt.setId( request.getId() );
        backgroundReceipt.setTitle( request.getTitle() );
        backgroundReceipt.setTaxIdNumber( request.getTaxIdNumber() );
        backgroundReceipt.setAccountBank( request.getAccountBank() );
        backgroundReceipt.setAccountNumber( request.getAccountNumber() );
        backgroundReceipt.setRegisterAddress( request.getRegisterAddress() );
        backgroundReceipt.setCompanyPhone( request.getCompanyPhone() );
        backgroundReceipt.setAmount( request.getAmount() );
        backgroundReceipt.setType( request.getType() );
        backgroundReceipt.setReceiver( request.getReceiver() );
        backgroundReceipt.setReceiverAddress( request.getReceiverAddress() );
        backgroundReceipt.setPostalCode( request.getPostalCode() );
        backgroundReceipt.setReceiverPhone( request.getReceiverPhone() );
        backgroundReceipt.setRemarks( request.getRemarks() );

        return backgroundReceipt;
    }

    @Override
    public ReceiptDTO toReceiptDTO(BackgroundReceipt backgroundReceipt) {
        if ( backgroundReceipt == null ) {
            return null;
        }

        ReceiptDTO receiptDTO = new ReceiptDTO();

        receiptDTO.setId( backgroundReceipt.getId() );
        receiptDTO.setType( backgroundReceipt.getType() );
        receiptDTO.setTitle( backgroundReceipt.getTitle() );
        receiptDTO.setTaxIdNumber( backgroundReceipt.getTaxIdNumber() );
        receiptDTO.setAccountBank( backgroundReceipt.getAccountBank() );
        receiptDTO.setAccountNumber( backgroundReceipt.getAccountNumber() );
        receiptDTO.setRegisterAddress( backgroundReceipt.getRegisterAddress() );
        receiptDTO.setCompanyPhone( backgroundReceipt.getCompanyPhone() );
        receiptDTO.setAmount( backgroundReceipt.getAmount() );
        receiptDTO.setReceiptTime( backgroundReceipt.getReceiptTime() );
        receiptDTO.setApplyStatus( backgroundReceipt.getApplyStatus() );
        receiptDTO.setStatus( backgroundReceipt.getStatus() );
        receiptDTO.setReceiver( backgroundReceipt.getReceiver() );
        receiptDTO.setReceiverAddress( backgroundReceipt.getReceiverAddress() );
        receiptDTO.setPostalCode( backgroundReceipt.getPostalCode() );
        receiptDTO.setReceiverPhone( backgroundReceipt.getReceiverPhone() );
        receiptDTO.setRemarks( backgroundReceipt.getRemarks() );

        return receiptDTO;
    }

    @Override
    public ReceiptRecordDTO toReceiptRecordDTO(BackgroundReceipt backgroundReceipt) {
        if ( backgroundReceipt == null ) {
            return null;
        }

        ReceiptRecordDTO receiptRecordDTO = new ReceiptRecordDTO();

        receiptRecordDTO.setId( backgroundReceipt.getId() );
        receiptRecordDTO.setReceiptTime( backgroundReceipt.getReceiptTime() );
        receiptRecordDTO.setAmount( backgroundReceipt.getAmount() );
        receiptRecordDTO.setReceiver( backgroundReceipt.getReceiver() );
        receiptRecordDTO.setType( backgroundReceipt.getType() );
        receiptRecordDTO.setStatus( backgroundReceipt.getStatus() );
        receiptRecordDTO.setTitle( backgroundReceipt.getTitle() );
        receiptRecordDTO.setRemarks( backgroundReceipt.getRemarks() );

        return receiptRecordDTO;
    }

    @Override
    public List<ReceiptRecordDTO> toReceiptRecordDTOS(List<BackgroundReceipt> records) {
        if ( records == null ) {
            return null;
        }

        List<ReceiptRecordDTO> list = new ArrayList<ReceiptRecordDTO>( records.size() );
        for ( BackgroundReceipt backgroundReceipt : records ) {
            list.add( toReceiptRecordDTO( backgroundReceipt ) );
        }

        return list;
    }
}
