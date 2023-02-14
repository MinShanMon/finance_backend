package com.personalfinance.backend.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashMap;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return transactionRepo.findAllTransactionsByUserIdAndMonth(userId, month);
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

    @Override
    public Map<String, Double> getSpendingForecast(long userId) {
        final String API_URL = "http://localhost:5000/predict?userid=" + userId;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Map<String, Double>> predictionsJson = restTemplate.getForObject(API_URL, Map.class);
        Map<String, Double> predictions = new HashMap<>();
        predictionsJson.values().forEach( v -> {
            predictions.putAll(v);
         } );
         return predictions;
    }
}
