package com.personalfinance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalfinance.backend.model.RegUser;

public interface RegUserRepository extends JpaRepository<RegUser, Long> {
    
}
