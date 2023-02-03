package com.personalfinance.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import javax.persistence.EnumType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.personalfinance.backend.exception.ResourceNotFoundException;
import com.personalfinance.backend.model.RegisteredUsers;
import com.personalfinance.backend.model.Role;
import com.personalfinance.backend.model.StatusEnum;
import com.personalfinance.backend.repository.RegisteredUsersRepository;
import com.personalfinance.backend.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegisteredUsersServiceImpl implements RegisteredUsersService, UserDetailsService {
    
    @Autowired
    RegisteredUsersRepository registeredUsersRepository;

    @Autowired
    RoleService roleService;

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
        user.setRoleSet(user.getRoleSet());
        return registeredUsersRepository.save(user);
    }

    @Override
    public void saveToken(String emai, String token){
        RegisteredUsers email = findByEmail(emai);
        email.setJwtToken(token);
        registeredUsersRepository.saveAndFlush(email);
    }

    @Override
    public void deleteToken(String emai){
        RegisteredUsers email = findByEmail(emai);
        email.setJwtToken(null);
        registeredUsersRepository.saveAndFlush(email);
    }

    @Override
    public RegisteredUsers findByEmail(String email) throws ResourceNotFoundException{
        // TODO Auto-generated method stub
    
        return registeredUsersRepository.findByEmail(email);
        
    }

    @Override
    public String createOTP(String email){
        RegisteredUsers user = findByEmail(email);
        Random rnd = new Random();
		String OTP = Integer.toString(rnd.nextInt(999999));

		System.out.println("OTP is: " + OTP);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encoderOTP = passwordEncoder.encode(OTP);

        user.setOtp(encoderOTP);
        user.setOtpReqTime(System.currentTimeMillis()+ 5 * 60 *1000);
        registeredUsersRepository.saveAndFlush(user);

        return OTP;
    }

	@Autowired
	JavaMailSender mailSender;

    @Override
    public void sendEmail(String email) throws UnsupportedEncodingException, MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Finance@contact.com", "Finance company");
        helper.setTo(email);

        RegisteredUsers user = findByEmail(email);

        String OTP = createOTP(email);

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

		String content = "<p>Hello " + user.getFullName() + "</p>"
				+ "<p>For security reason, you're required to use the following "
				+ "One Time Password to confirm your account:</p>"
				+ "<p><b>" + OTP + "</b></p>"
				+ "<br>"
				+ "<p>Note: this OTP is set to expire in 5 minutes.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);

		System.out.println("email was sent");
    }

    @Override
    public void deleteOtp(String email){
        RegisteredUsers user = findByEmail(email);
        user.setOtp(null);
        user.setOtpReqTime(null);
        registeredUsersRepository.saveAndFlush(user);
    }

    @Override
    public RegisteredUsers registerUserAccount(RegisteredUsers rUser) throws UnsupportedEncodingException, MessagingException{
        RegisteredUsers user = new RegisteredUsers();
        user.setEmail(rUser.getEmail());
        user.setFullName(rUser.getFullName());
        user.setPassword(passwordEncoder.encode( rUser.getPassword()));
        List<Role> roles=new ArrayList<>();
        roles.add(roleService.findRoleByName("User"));
        user.setRoleSet(roles);
        user.setStatus(StatusEnum.PENDING);
        registeredUsersRepository.saveAndFlush(user);
        sendEmail(rUser.getEmail());
        
        return user;
    }

    @Override
    public Integer validateOTP(String email, String OTP){
        RegisteredUsers user = findByEmail(email);

        if(user.getOtpReqTime() > System.currentTimeMillis()){
            if(OTP.equals(user.getOtp())){
                return 0;
            }
            else{
                return 1;
            }
        }
        else{
            return 2;
        }
    }
}
