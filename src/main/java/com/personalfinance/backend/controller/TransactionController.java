package com.personalfinance.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.model.RegUser;
import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.service.RegUserService;
import com.personalfinance.backend.service.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionSvc;

    @Autowired
    RegUserService regUserService;

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

    @PostMapping("/transaction/{userId}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable("userId") long userId,
            @RequestBody Transaction transaction) {

        try {
            Transaction createdTransaction = new Transaction(transaction.getTitle(), transaction.getDescription(),
                    transaction.getDate(), transaction.getCategory(), transaction.getAmount());
            RegUser user = regUserService.getUserById(userId);
            createdTransaction.setUser(user);
            transactionSvc.addTransaction(createdTransaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/transaction/{userId}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("userId") long userId,
            @RequestBody Transaction transaction) {

        try {
            Transaction updatedTransaction = transactionSvc.updateTransaction(transaction);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transaction/{userid}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable("userId") long userId,
            @RequestBody Transaction transaction) {

        try {
            Transaction updatedTransaction = transactionSvc.updateTransaction(transaction);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
