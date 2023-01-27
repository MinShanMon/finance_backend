package com.personalfinance.backend.service;

import java.util.List;

import com.personalfinance.backend.model.Transaction;

public interface TransactionService {

    public List<Transaction> getAllTransactionsByUserId(long userid);
    public Transaction addTransaction(Transaction transaction);
}
