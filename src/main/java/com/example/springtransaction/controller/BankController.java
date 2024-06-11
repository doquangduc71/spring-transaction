package com.example.springtransaction.controller;

import com.example.springtransaction.dto.request.AccountRequest;
import com.example.springtransaction.dto.request.TransferRequest;
import com.example.springtransaction.dto.response.AccountResponse;
import com.example.springtransaction.dto.response.ApiResponse;
import com.example.springtransaction.dto.response.TransferResponse;
import com.example.springtransaction.enums.ErrorCode;
import com.example.springtransaction.exception.AccountException;
import com.example.springtransaction.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/transfer")
    public ApiResponse transfer(@RequestBody TransferRequest transferRequest) {
            TransferResponse result = bankService.transfer(
                    transferRequest.getFrom(),
                    transferRequest.getTo(),
                    transferRequest.getAmount());
            return ApiResponse.builder()
                    .message(ErrorCode.SUCCESS.getMessage())
                    .code(ErrorCode.SUCCESS)
                    .result(result)
                    .build();
    }

    @PostMapping("/addNewAccount")
    public ApiResponse<Boolean> addNewAccount(@RequestBody AccountRequest accountRequest) throws AccountException {
            return ApiResponse.<Boolean>builder()
                    .message(ErrorCode.SUCCESS.getMessage())
                    .code(ErrorCode.SUCCESS)
                    .result(bankService.addNewAccount(accountRequest))
                    .build();
    }

    @GetMapping("/getAccounts")
    public ApiResponse<List<AccountResponse>> getAccounts(){
        return ApiResponse.<List<AccountResponse>>builder()
                .message(ErrorCode.SUCCESS.getMessage())
                .code(ErrorCode.SUCCESS)
                .result(bankService.getAccounts())
                .build();
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
