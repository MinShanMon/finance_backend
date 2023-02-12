package com.personalfinance.backend.service;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repository.MonthlyTransactionRepository;


@Service
public class MonthlyTransactionServiceImpl implements MonthlyTransactionService{
  @Autowired
  MonthlyTransactionRepository monthlyTransactionRepo;



  @Override
  public void createMonthlyTransactionTable() {
    monthlyTransactionRepo.createMonthlyTransactionTable();
  }
}

