package com.personalfinance.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.service.TransactionService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService transactionSvc;

    @GetMapping("/transaction/{userId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(@PathVariable("userId") long userId,
            @RequestParam(value = "month", required = false) Integer month) {
        try {
            if (month == null) {
                List<Transaction> transactions = transactionSvc.getAllTransactionsByUserId(userId);
                return new ResponseEntity<>(transactions, HttpStatus.OK);
            }
            List<Transaction> transactions = transactionSvc.getAllTransactionsByUserIdAndMonth(userId, month);
            return new ResponseEntity<>(transactions, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionSvc.addTransaction(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
