package com.personalfinance.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalfinance.backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    public List<Transaction> findAllTransactionsByUserId(long userId);
}
