package com.personalfinance.backend.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalfinance.backend.model.Enquiry;


@Transactional
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
	
}