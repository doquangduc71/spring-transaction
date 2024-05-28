package com.example.springtransaction.controller;

import com.example.springtransaction.dto.request.TransferRequest;
import com.example.springtransaction.dto.response.TransferResponse;
import com.example.springtransaction.exception.BankException;
import com.example.springtransaction.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest transferRequest) {

        try {
            TransferResponse result = bankService.transfer(
                    transferRequest.getFrom(),
                    transferRequest.getTo(),
                    transferRequest.getAmount());

            return ResponseEntity.ok().body(result);
        } catch (BankException e) {
            TransferResponse transerError = new TransferResponse(
                    e.getErrorCode(),
                    e.getMessage() + " : " + e.getDetail(),
                    new Date());
            return ResponseEntity.badRequest().body(transerError);
        }
    }
}
