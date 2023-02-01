package com.personalfinance.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.service.EnquiryService;

@RestController

@RequestMapping("/admin")
public class AdminTicketController {
    
    @Autowired
    private EnquiryService enqService;
    
    @GetMapping("/enquiries")
    public ResponseEntity <List<Enquiry>> getSubHistory(){
        return new ResponseEntity <>(enqService.getAllEnquiry(), HttpStatus.OK);
    }
  
}