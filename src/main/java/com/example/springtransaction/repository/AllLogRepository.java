package com.example.springtransaction.repository;

import com.example.springtransaction.entity.AllLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllLogRepository extends JpaRepository<AllLog,Long> {
}
