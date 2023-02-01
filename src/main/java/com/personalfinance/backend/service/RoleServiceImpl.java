package com.personalfinance.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Role;
import com.personalfinance.backend.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    RoleRepository roleRepository;
    
    
    
    @Override
    public Role saveRole(Role role) {
        // TODO Auto-generated method stub
        
        return roleRepository.saveAndFlush(role);
    }
    
}
