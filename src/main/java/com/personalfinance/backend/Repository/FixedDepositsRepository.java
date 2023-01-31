package com.personalfinance.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalfinance.backend.Models.FixedDeposits;


@Repository
public interface FixedDepositsRepository extends JpaRepository<FixedDeposits,Long> {
    
}
