package com.personalfinance.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personalfinance.backend.model.FixedDeposits;


@Repository
public interface FixedDepositsRepository extends JpaRepository<FixedDeposits,Long> {

    @Query(value = "SELECT * FROM fixed_deposits where bank_id = :id",nativeQuery = true)
    List<FixedDeposits> findfddoBank(@Param("id")Long id);
}
