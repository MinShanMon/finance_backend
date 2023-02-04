package com.personalfinance.backend.service;

import com.personalfinance.backend.model.RegisteredUsers;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

public interface RegisteredUsersService {
    
    RegisteredUsers saveUser(RegisteredUsers user);

    RegisteredUsers findByEmail(String email);

    void saveToken(String emai, String token);

    String getToken(String email);

    void deleteToken(String email);

    String createOTP(String email);

    void sendEmail(String email) throws UnsupportedEncodingException, MessagingException;

    void deleteOtp(String email);

    RegisteredUsers registerUserAccount(RegisteredUsers ruser)  throws UnsupportedEncodingException, MessagingException;

    Integer validateOTP(String email, String OTP);

    boolean checkStatus(String email) throws UnsupportedEncodingException, MessagingException;

    boolean resetPassword(String email, String password);

    RegisteredUsers getUserById(int id);
    
    
}
