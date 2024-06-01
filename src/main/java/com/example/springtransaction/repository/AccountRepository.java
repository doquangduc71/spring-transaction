package com.example.springtransaction.repository;

import com.example.springtransaction.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Modifying
    @Query(value = "INSERT INTO account (balance,owner,state) VALUES (:balance,:owner,1)", nativeQuery = true)
    int addNewAccount(@Param("owner") String owner,@Param("balance") Long balance);

    boolean existsByOwner(String owner);

}
