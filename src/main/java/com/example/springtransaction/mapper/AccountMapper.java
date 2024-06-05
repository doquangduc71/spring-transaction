package com.example.springtransaction.mapper;

import com.example.springtransaction.dto.response.AccountResponse;
import com.example.springtransaction.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring"
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        ,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountMapper {
    AccountResponse toAccountResponse(Account account);
}
