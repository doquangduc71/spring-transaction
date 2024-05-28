package com.example.springtransaction.repository;

import com.example.springtransaction.entity.AllLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllLogRepository extends JpaRepository<AllLog,Long> {
}
