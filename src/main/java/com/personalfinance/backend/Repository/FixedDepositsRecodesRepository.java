package com.personalfinance.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personalfinance.backend.Models.FixedDeposits;
import com.personalfinance.backend.Models.FixedDepositsRecords;


@Repository
public interface FixedDepositsRecodesRepository extends JpaRepository<FixedDepositsRecords,Long> {

   
}
