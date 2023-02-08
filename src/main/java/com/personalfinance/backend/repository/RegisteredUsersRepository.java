package com.personalfinance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalfinance.backend.model.RegisteredUsers;

@Repository
public interface RegisteredUsersRepository extends JpaRepository<RegisteredUsers, Integer>{
    RegisteredUsers findByEmail(String email);

    RegisteredUsers findByFbid(String fbid);

    // RegisteredUsers findById(Integer id);


}
