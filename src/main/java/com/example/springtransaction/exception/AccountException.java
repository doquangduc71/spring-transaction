package com.example.springtransaction.exception;

import com.example.springtransaction.enums.ErrorCode;

public class AccountException extends Exception{
    private final ErrorCode errorCode;
    public AccountException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
