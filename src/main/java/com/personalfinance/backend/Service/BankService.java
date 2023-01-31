package com.personalfinance.backend.Service;

import java.util.List;
import java.util.Optional;

import com.personalfinance.backend.Models.Bank;
import com.personalfinance.backend.Models.FixedDeposits;

public interface BankService {

    List<Bank> findAllBank();

    Optional<Bank> findBankById(long id);

    Bank addBank(Bank bank);

    Bank editBank(Bank bank, Long id);

    Boolean deleteBankById(Long id);

    // List<FixedDeposits> findAllDeposists_dependsOnBank(Long id);


    
}
