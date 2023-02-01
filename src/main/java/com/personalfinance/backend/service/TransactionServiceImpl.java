package com.personalfinance.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepo;

    @Override
    public List<Transaction> getAllTransactionsByUserId(long userId) {
        return transactionRepo.findAllTransactionsByUserId(userId);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserIdAndMonth(long userId, Integer month) {
        return transactionRepo.findAllTransactionsByUserId(userId);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Transaction updatedTransaction = transactionRepo.findById(transaction.getId()).get();
        updatedTransaction.setAmount(transaction.getAmount());
        updatedTransaction.setCategory(transaction.getCategory());
        updatedTransaction.setDate(transaction.getDate());
        updatedTransaction.setDescription(transaction.getDescription());
        updatedTransaction.setTitle(transaction.getTitle());
        transactionRepo.save(transaction);
        return updatedTransaction;
    }


    
}
