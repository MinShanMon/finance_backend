package com.personalfinance.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.exception.ForbiddenException;
import com.personalfinance.backend.exception.ResourceNotFoundException;
import com.personalfinance.backend.model.RegisteredUsers;
import com.personalfinance.backend.service.RegisteredUsersService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.JWTVerifier;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.personalfinance.backend.model.Role;
import com.personalfinance.backend.model.Token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors; 


@RestController
@RequestMapping("/api")
public class RegisteredUsersController {

    @Autowired
    RegisteredUsersService userService;

    @GetMapping("/user/addSessionAdmin")
    public ResponseEntity<RegisteredUsers> getAdminUserByEmail(@RequestParam("email") String email, HttpServletRequest request) throws ResourceNotFoundException{
        
        try{
            RegisteredUsers user = userService.findByEmail(email);
            if(user == null){
                throw new ResourceNotFoundException();
            }
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());
            userService.saveToken(email, token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(ResourceNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch(ForbiddenException e){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/user/checkToken")
    public ResponseEntity<Long> deleteMember(@RequestParam("email") String email, HttpServletRequest request) {
        
        try {
            String utk = userService.getToken(email);
            Long id = 1L;
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());   
            if (utk.equals(token)) {
                return new ResponseEntity<>(id, HttpStatus.OK);
            }

            else{
                throw new Exception();
            }
            // return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException{
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT  decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                RegisteredUsers user = userService.findByEmail(username); 
                
                String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 *60 *1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoleSet().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);

                Token tokenss = new Token();
                tokenss.setAccess_token(access_token);
                tokenss.setRefresh_token(refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokenss);
                
            }
            catch(Exception exception){
                
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else{
            throw new RuntimeException("Refresh Token is Missing");
        }
    }
}
