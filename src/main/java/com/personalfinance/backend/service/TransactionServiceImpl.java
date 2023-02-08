package com.personalfinance.backend.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepo;

    @Override
    public List<Transaction> getAllTransactionsByUserId(int userId) {
        return transactionRepo.findAllTransactionsByUserId(userId);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserIdAndMonth(int userId, Integer month) {
        return transactionRepo.findAllTransactionsByUserId(userId);
    }

    @Override
    public Transaction getTransactionById(long transactionId) {
        return transactionRepo.findById(transactionId).orElseThrow(NoResultException::new);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Transaction updatedTransaction = transactionRepo.findById(transaction.getId()).get();
        updatedTransaction.setAmount(transaction.getAmount());
        updatedTransaction.setCategory(transaction.getCategory());
        updatedTransaction.setDate(transaction.getDate());
        updatedTransaction.setDescription(transaction.getDescription());
        updatedTransaction.setTitle(transaction.getTitle());
        transactionRepo.save(updatedTransaction);
        return updatedTransaction;
    }

    @Override
    public boolean deleteTransactionById(long transactionId) {
        try {
            transactionRepo.deleteById(transactionId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
