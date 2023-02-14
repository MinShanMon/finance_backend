package com.personalfinance.backend.service;
import java.util.List;
import java.util.Optional;

import com.personalfinance.backend.repository.MonthlyTransactionRepository;
import com.personalfinance.backend.model.Bank;
import com.personalfinance.backend.model.FixedDeposits;

public interface MonthlyTransactionService {

  public void updateMonthlyTransactions() ;
}
