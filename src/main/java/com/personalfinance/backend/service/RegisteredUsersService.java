package com.personalfinance.backend.service;

import com.personalfinance.backend.model.RegisteredUsers;

public interface RegisteredUsersService {
    
    RegisteredUsers saveUser(RegisteredUsers user);
}
