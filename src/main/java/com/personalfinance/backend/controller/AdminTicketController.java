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
import org.springframework.web.bind.annotation.PathVariable;

@RestController

@RequestMapping("/admin")
public class AdminTicketController {
    
    @Autowired
    private EnquiryService enqService;
    
    @GetMapping("/enquiries")
    public ResponseEntity <List<Enquiry>> viewEnquiries(){
        return new ResponseEntity <>(enqService.getAllEnquiry(), HttpStatus.OK);
    }

    @GetMapping("/enquiries/open")
    public ResponseEntity <List<Enquiry>> viewOpenEnquiries(){
        return new ResponseEntity <>(enqService.getOpenEnquiry(), HttpStatus.OK);
    }
  
    @GetMapping("/enquiries/closed")
    public ResponseEntity <List<Enquiry>> viewClosedEnquiries(){
        return new ResponseEntity <>(enqService.getClosedEnquiry(), HttpStatus.OK);
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity <Enquiry> getDetail(@PathVariable int id){
        return new ResponseEntity<>(enqService.getOneEnquiry(id),HttpStatus.OK);
    }
}