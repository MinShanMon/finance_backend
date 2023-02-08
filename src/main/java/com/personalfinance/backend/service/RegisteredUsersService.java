package com.personalfinance.backend.service;

import com.personalfinance.backend.model.RegisteredUsers;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

public interface RegisteredUsersService {
    
    RegisteredUsers saveUser(RegisteredUsers user);

    RegisteredUsers findByEmail(String email);

    void saveToken(String emai, String token);

    String getToken(Integer id);

    String getTokenByEmail(String email);

    void deleteToken(Integer id);

    String createOTP(String email);

    void sendEmail(String email) throws UnsupportedEncodingException, MessagingException;

    void deleteOtp(String email);

    RegisteredUsers registerUserAccount(RegisteredUsers ruser)  throws UnsupportedEncodingException, MessagingException;

    Integer validateOTP(String email, String OTP);

    boolean checkStatus(String email) throws UnsupportedEncodingException, MessagingException;

    boolean resetPassword(String email, String password, String otp);

    RegisteredUsers getUserById(int id);
    
    RegisteredUsers registerFacebook(RegisteredUsers user);

    RegisteredUsers findByFbid(String fbid);

    void saveFbToken(String fbid, String token);

    RegisteredUsers editProfile(RegisteredUsers user) throws Exception;

    boolean editProfilReset(Integer id, String password);

    String asciiToHex(String asciiStr);
    
}
