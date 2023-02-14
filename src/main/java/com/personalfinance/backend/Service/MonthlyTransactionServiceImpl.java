package com.personalfinance.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.MonthlyTransaction;
import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repository.MonthlyTransactionRepository;
import com.personalfinance.backend.repository.TransactionRepository;

@Service
public class MonthlyTransactionServiceImpl implements MonthlyTransactionService{
  @Autowired
  MonthlyTransactionRepository monthlyTransactionRepository;

  @Autowired
  TransactionRepository transactionRepository;



  @Override
  public void updateMonthlyTransactions(){
    
    List<Transaction> allTransactions = transactionRepository.findAll();
  
    Map<Integer, Map<LocalDate, Double>> userTransactions = allTransactions.stream()
    .filter(t -> !t.getCategory().equalsIgnoreCase("income"))
    .collect(Collectors.groupingBy(transaction -> transaction.getUser().getId(),
        Collectors.groupingBy(transaction -> transaction.getDate().withDayOfMonth(1),
            Collectors.summingDouble(Transaction::getAmount))));

    userTransactions.forEach((userId, monthlyTransactions) -> {
      monthlyTransactions.forEach((month, amount) -> {
        MonthlyTransaction monthlyTransaction = new MonthlyTransaction();
        monthlyTransaction.setUserId(userId);
        monthlyTransaction.setAmount(amount);
        monthlyTransaction.setDate(month);
        monthlyTransactionRepository.save(monthlyTransaction);
      });
    });
    System.out.println("executed scheduled!");
  }
}

