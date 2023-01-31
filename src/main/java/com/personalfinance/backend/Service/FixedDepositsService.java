package com.personalfinance.backend.Service;


import java.util.List;
import java.util.Optional;

import com.personalfinance.backend.Models.FixedDeposits;

public interface FixedDepositsService {
   
    Optional<FixedDeposits> findFixedById(long id);

    FixedDeposits addDeposists(FixedDeposits fixedDeposits);

    FixedDeposits editDeposits(FixedDeposits fixedDeposits, Long id);

    Boolean deleteDeposistsById(Long id);
    

    List<FixedDeposits> findAllDepositsDependsOnBankId(Long id);
}
