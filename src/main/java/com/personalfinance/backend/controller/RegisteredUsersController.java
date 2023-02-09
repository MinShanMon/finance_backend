package com.personalfinance.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/user/addSessionUser")
    public ResponseEntity<RegisteredUsers> getUserByEmail(@RequestParam("email") String email, HttpServletRequest request){
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
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/deleteToken")
    public ResponseEntity<Long> deleteToken(@RequestParam Integer id, HttpServletRequest request){
        try{
            Long ids = 1L;
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());  
            ckToken(id, token);
            userService.deleteToken(id);
            return new ResponseEntity<>(ids, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/checkToken")
    public ResponseEntity<Token> checkToken(@RequestParam("id") Integer id, HttpServletRequest request) {
        
        try {
            String utk = userService.getToken(id);
            Long ids = 1L;
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());   
            Token tokens = new Token();
            if (utk.equals(token)) {
                
                tokens.setStatus("200");
                return new ResponseEntity<>(tokens, HttpStatus.OK);
            }
            else{
                // tokens.setStatus("403");
                // return new ResponseEntity<>(tokens, HttpStatus.FORBIDDEN);
                throw new Exception();
            }
            // return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/user/checkTokenAndroid")
    // public ResponseEntity<Long> checkFbToken(@RequestParam("id") Integer id, HttpServletRequest request){

    //     try{
    //         String utk = userService.getTokenById(id);
    //         Long ids = 1L;
    //         String authorizationHeader = request.getHeader(AUTHORIZATION);
    //         String token = authorizationHeader.substring("Bearer ".length()); 
    //         if(utk.equals(token)){
    //             return new ResponseEntity<>(ids, HttpStatus.OK);
    //         }
    //         else{
    //             throw new Exception();
    //         }
    //     }
    //     catch(Exception e){
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    public void ckToken(Integer id, String token) throws Exception{
        String utk = userService.getToken(id);
        if(utk.equals(token)){
            return;
        }
        else{
            throw new Exception();
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

    @PostMapping("/user/register")
    public ResponseEntity<RegisteredUsers> registerUserAccount(@RequestBody RegisteredUsers ruser){

        try{
            RegisteredUsers userck = userService.findByEmail(ruser.getEmail());
            if(userck != null){
                throw new Exception();
            }
            RegisteredUsers user =  userService.registerUserAccount(ruser);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/loginWithFb")
    public ResponseEntity<RegisteredUsers> registerFbUserAccount(@RequestBody RegisteredUsers ruser, HttpServletRequest request, HttpServletResponse response){
        try{
            String hex = userService.asciiToHex(ruser.getFbid());
            RegisteredUsers dbUser = userService.findByFbid(hex);
            if(dbUser != null){
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                String access_token = JWT.create()
                .withSubject(dbUser.getEmail())
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", dbUser.getRoleSet().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
                userService.saveFbToken(hex, access_token);
                return new ResponseEntity<>(dbUser, HttpStatus.OK);
            }
            else {
                RegisteredUsers saveUser = userService.registerFacebook(ruser);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                String access_token = JWT.create()
                .withSubject(saveUser.getEmail())
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", saveUser.getRoleSet().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
                userService.saveFbToken(hex, access_token);
                return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/sentOTPByEmail")
    public ResponseEntity<Long> sentOTP(@RequestParam String email, HttpServletRequest request,  HttpServletResponse response){

        try{
            RegisteredUsers user = userService.findByEmail(email);
            if(user == null){
                // Map<String, String> status = new HashMap<>();
                // status.put("status", "400");
                // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                // new ObjectMapper().writeValue(response.getOutputStream(), status);
                Token tokenss = new Token();
                tokenss.setAccess_token("400");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokenss);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            userService.sendEmail(email);
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 *60 *1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoleSet().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);

            Token tokenss = new Token();
            tokenss.setAccess_token(access_token);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokenss);

            userService.saveToken(email, access_token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // catch(ForbiddenException e){
        //     return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        // }
        catch(ResourceNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/otpverify")
    public ResponseEntity<Long> verifyOTP(@RequestParam String email, @RequestParam String otp, HttpServletRequest request, HttpServletResponse response){
        try{
            String utk = userService.getTokenByEmail(email);            
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());
            
            
            Token tokenss = new Token();
            if (!utk.equals(token)){
                throw new Exception();
            }
            Integer i = userService.validateOTP(email, otp);
            //empty user
            if(i == 0){
                
                RegisteredUsers user = userService.findByEmail(email);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoleSet().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);

                
                tokenss.setRefresh_token(access_token);
                tokenss.setStatus("0");

                userService.saveToken(email, access_token);
                // throw new ForbiddenException();
            }
            //otp timeout
            else if(i == 2){
                tokenss.setStatus("2");
                // throw new TimeOutException();
            }
            //incorrect otp
            else if(i == 1){
                tokenss.setStatus("1");
                // throw new ResourceNotFoundException();
            }
            else{
                tokenss.setStatus("3");
            }
            
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokenss);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @PostMapping("/user/resetpassword")
    public ResponseEntity<Long> resetPassword(@RequestParam String email, @RequestParam String password, @RequestParam String otp, HttpServletRequest request, HttpServletResponse response){
        try{
            String utk = userService.getTokenByEmail(email);
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());   
            if (utk.equals(token)) {
                boolean pass = userService.resetPassword(email, password, otp);
                if(pass){
                    Map<String, String> statuss = new HashMap<>();
                    statuss.put("status", "0");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), statuss);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else{
                    throw new Exception();
                }
            }
            else{
                throw new Exception();
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/edit/profile")
    public ResponseEntity<RegisteredUsers> editProfile(@RequestBody RegisteredUsers user, HttpServletRequest request){
        try{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());  
            ckToken(user.getId(), token);
            RegisteredUsers updateUser = userService.editProfile(user);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/edit/password")
    public ResponseEntity<Long> editPassword(@RequestParam("id") Integer id, @RequestParam("password") String password, HttpServletRequest request){
        try{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer ".length());  
            ckToken(id, token);
            boolean check = userService.editProfilReset(id, password);
            if(check){
                return new ResponseEntity<>(1L, HttpStatus.OK);
            }
            else{
                throw new Exception();
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
