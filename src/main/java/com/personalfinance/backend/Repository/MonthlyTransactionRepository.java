package com.personalfinance.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.personalfinance.backend.model.MonthlyTransaction;
import com.personalfinance.backend.model.Transaction;

public interface MonthlyTransactionRepository extends JpaRepository<MonthlyTransaction, Long> {
  @Modifying
  @Query(value = "CREATE TABLE monthlyTransaction AS " +
    "SELECT NULL AS id, user_user_id, SUM(amount) AS amount, DATE_FORMAT(date, '%Y-%m-01') AS date " +
    "FROM transaction " +
    "GROUP BY user_user_id, DATE_FORMAT(date, '%Y-%m')", nativeQuery = true)
  void createMonthlyTransactionTable();
  
  @Query(value = "DROP PROCEDURE IF EXISTS insertRandomTransactionsNew;"
  +"DELIMITER $$" + "CREATE PROCEDURE insertRandomTransactionsNew()"
  +"BEGIN DECLARE i INT DEFAULT 1;"+"WHILE i <= 1000 DO"
  +"INSERT INTO transaction (user_user_id, amount, date, description, title, category)"
  +"VALUES (FLOOR(RAND() * 2) + 1, RAND() * 1000, DATE_ADD('2022-01-01', INTERVAL FLOOR(RAND() * 365) DAY), 'Transaction Description', 'Transaction Title', 'Transaction Category');"
  +" SET i = i + 1;"
  +"END WHILE;"
  +"END $$"
  +"DELIMITER ;"
  +"CALL insertRandomTransactionsNew();",nativeQuery = true)
  void addRandomTransactions();
}
