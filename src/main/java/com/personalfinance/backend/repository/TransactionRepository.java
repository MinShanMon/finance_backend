package com.personalfinance.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.personalfinance.backend.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId ORDER BY t.date DESC")
    public List<Transaction> findAllTransactionsByUserId(int userId);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND MONTH(t.date) = :month AND YEAR(t.date) = :year ORDER BY t.date DESC")
    public List<Transaction> findAllCurrentTransactionsByUserIdAndMonth(@Param("userId") int userId, @Param("month") int month, @Param("year") int year);
}
