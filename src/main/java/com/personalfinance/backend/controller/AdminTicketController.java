package com.personalfinance.backend.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.Ticket;
import com.personalfinance.backend.service.EnquiryService;
import com.personalfinance.backend.service.TicketService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

@RequestMapping("/admin")
public class AdminTicketController {
    
    @Autowired
    private EnquiryService enqService;
    
    @Autowired
    private TicketService tikService;

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

    @GetMapping("/view/{id}")
    public ResponseEntity <Enquiry> getDetail(@PathVariable int id){
        return new ResponseEntity<>(enqService.getOneEnquiry(id),HttpStatus.OK);
    }

    @PutMapping("/updateReply/{id}")
    public ResponseEntity<Ticket> editReply(@RequestBody Ticket tik, @PathVariable int id){   
       return new ResponseEntity<>(tikService.updateReply(tik, id),HttpStatus.OK); 
    }
 
    @PostMapping("/reply/{id}")
    public ResponseEntity<?> replyEmail(@PathVariable int id) throws MessagingException, UnsupportedEncodingException{
    {
        boolean result = this.tikService.sendEmail(id);

        if(result){

            return  ResponseEntity.ok("Email Sent Successfully.");

        }else{

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Sending Fails");
        }
    }
    }
}    
