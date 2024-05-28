package com.example.springtransaction.repository;

import com.example.springtransaction.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
