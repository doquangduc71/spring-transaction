package com.example.springtransaction.repository;

import com.example.springtransaction.entity.TransactLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactLogRepository extends JpaRepository<TransactLog,Long> {
}
