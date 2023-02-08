package com.personalfinance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalfinance.backend.model.Bank;


@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
    

    // @Query(value = "SELECT * FROM fixed_deposits where bank_id = :id",nativeQuery = true)
    // List<FixedDeposits> findfddoBank(@Param("id")Long id);
}
