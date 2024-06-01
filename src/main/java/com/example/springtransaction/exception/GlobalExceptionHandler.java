package com.example.springtransaction.exception;

import com.example.springtransaction.dto.response.ApiResponse;
import com.example.springtransaction.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(Exception e){
        return ResponseEntity.badRequest().body(ApiResponse
                .builder()
                .code(ErrorCode.UNKNOWN_ERROR)
                .message(ErrorCode.UNKNOWN_ERROR.getMessage())
                .build());
    }

    @ExceptionHandler(value = BankException.class)
    ResponseEntity<ApiResponse> handlingBankException(BankException bankException){
        return ResponseEntity.badRequest().body(ApiResponse
                .builder()
                .code(bankException.getErrorCode())
                .message(bankException.getErrorCode().getMessage())
                .build());
    }

    @ExceptionHandler(value = AccountException.class)
    ResponseEntity<ApiResponse> handlingBankException(AccountException accountException){
        return ResponseEntity.badRequest().body(ApiResponse
                .builder()
                .code(accountException.getErrorCode())
                .message(accountException.getErrorCode().getMessage())
                .build());
    }
}
