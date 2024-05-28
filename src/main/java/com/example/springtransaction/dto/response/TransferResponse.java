package com.example.springtransaction.dto.response;

import com.example.springtransaction.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private ErrorCode resultCode; //200 success, 404 account not found, 405 balance not enought
    private String message;
    private Date transferDate;
}
