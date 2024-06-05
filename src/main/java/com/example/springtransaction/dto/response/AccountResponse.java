package com.example.springtransaction.dto.response;

import com.example.springtransaction.enums.AccountState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {
    private Long id;
    private String owner;
    private Long balance;
    private AccountState state;
}
