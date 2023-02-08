package com.personalfinance.backend.service;


import java.util.List;
import java.util.Optional;

import com.personalfinance.backend.model.FixedDeposits;

public interface FixedDepositsService {
   
    Optional<FixedDeposits> findFixedById(long id);

    FixedDeposits addDeposists(FixedDeposits fixedDeposits);

    FixedDeposits editDeposits(FixedDeposits fixedDeposits, Long id);

    Boolean deleteDeposistsById(Long id);
    

    List<FixedDeposits> findAllDepositsDependsOnBankId(Long id);
    List<FixedDeposits> findAllDeposits();
}
