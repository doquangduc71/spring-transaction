package com.example.springtransaction.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(200,"Success"),
    UNKNOWN_ERROR(400,"Unknown error"),
    ACCOUNT_DISABLED(401,"Account has been disabled"),
    ID_NOT_FOUND(404,"Account id does not exist"),
    BALANCE_NOT_ENOUGH(405,"Balance not enough"),
    DATABASE_ERROR(500,"Server error");

    private final int value;
    private final String message;
    ErrorCode(int value, String message) {
        this.message = message;
        this.value = value;
    }

}
