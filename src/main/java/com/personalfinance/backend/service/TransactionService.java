package com.personalfinance.backend.service;

import java.util.List;

import com.personalfinance.backend.model.Transaction;

public interface TransactionService {

    public List<Transaction> getAllTransactionsByUserId(int userId);
    public List<Transaction> getAllTransactionsByUserIdAndMonth(int userId, Integer month);
    public Transaction addTransaction(Transaction transaction);
    public Transaction updateTransaction(Transaction transaction);
}
