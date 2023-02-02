package com.personalfinance.backend.service;

import com.personalfinance.backend.model.RegisteredUsers;

public interface RegisteredUsersService {
    
    RegisteredUsers saveUser(RegisteredUsers user);

    RegisteredUsers findByEmail(String email);

    void saveToken(String emai, String token);

    String getToken(String email);

    void deleteToken(String email);
}
