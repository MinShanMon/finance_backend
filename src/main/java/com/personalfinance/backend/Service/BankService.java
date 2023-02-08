package com.personalfinance.backend.service;

import java.util.List;
import java.util.Optional;

import com.personalfinance.backend.model.Bank;
import com.personalfinance.backend.model.FixedDeposits;

public interface BankService {

    List<Bank> findAllBank();

    Optional<Bank> findBankById(long id);

    Bank addBank(Bank bank);

    Bank editBank(Bank bank, Long id);

    Boolean deleteBankById(Long id);

    // List<FixedDeposits> findAllDeposists_dependsOnBank(Long id);


    
}
