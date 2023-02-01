package com.personalfinance.backend.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.personalfinance.backend.exception.ResourceNotFoundException;
import com.personalfinance.backend.model.RegisteredUsers;
import com.personalfinance.backend.repository.RegisteredUsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegisteredUsersServiceImpl implements RegisteredUsersService, UserDetailsService {
    
    @Autowired
    RegisteredUsersRepository registeredUsersRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        RegisteredUsers user = findByEmail(email);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
        else{
            log.info("User found in the database:{}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoleSet().forEach(
            role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        );
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    } 
    
    @Override
    public String getToken(String email){
        RegisteredUsers user = findByEmail(email);
        return user.getJwtToken();
    }

    @Override
    public RegisteredUsers saveUser(RegisteredUsers user) {
        // TODO Auto-generated method stub
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registeredUsersRepository.saveAndFlush(user);
    }

    @Override
    public void saveToken(String emai, String token){
        RegisteredUsers email = findByEmail(emai);
        email.setJwtToken(token);
        registeredUsersRepository.saveAndFlush(email);
    }

    @Override
    public RegisteredUsers findByEmail(String email) throws ResourceNotFoundException{
        // TODO Auto-generated method stub
    
        return registeredUsersRepository.findByEmail(email);
        
    }

    

    
}
