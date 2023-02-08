package com.personalfinance.backend.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.personalfinance.backend.model.Enquiry;

@Transactional
public interface ReviewRepository extends JpaRepository<Enquiry, Integer> {
    @Query("SELECT e from Enquiry e WHERE e.id =:id")
    Enquiry getDetail(@Param("id") Integer id);
}