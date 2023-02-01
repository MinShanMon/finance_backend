package com.personalfinance.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.RegUser;
import com.personalfinance.backend.repository.RegUserRepository;

@Service
public class RegUserServiceImpl implements RegUserService {

    @Autowired
    RegUserRepository regUserRepo;
    
    public RegUser getUserById(long id) {
        return regUserRepo.findById(id).get();
    }
}
