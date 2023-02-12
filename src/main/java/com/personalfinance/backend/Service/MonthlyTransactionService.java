package com.personalfinance.backend.Service;
import java.util.List;
import java.util.Optional;

import com.personalfinance.backend.Repository.MonthlyTransactionRepository;
import com.personalfinance.backend.model.Bank;
import com.personalfinance.backend.model.FixedDeposits;

public interface MonthlyTransactionService {

  public void createMonthlyTransactionTable() ;
}
