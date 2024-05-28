package com.example.springtransaction.exception;

import com.example.springtransaction.enums.ErrorCode;

public class BankException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String detail;

    public BankException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public String getDetail() {
        return this.detail;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

}
