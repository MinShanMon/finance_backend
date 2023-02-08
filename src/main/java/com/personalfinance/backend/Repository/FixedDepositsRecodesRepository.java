package com.personalfinance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalfinance.backend.model.FixedDepositsRecords;


@Repository
public interface FixedDepositsRecodesRepository extends JpaRepository<FixedDepositsRecords,Long> {

   
}
