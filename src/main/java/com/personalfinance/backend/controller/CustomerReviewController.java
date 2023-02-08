package com.personalfinance.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.service.ReviewService;

@RestController

@RequestMapping("/customer")
public class CustomerReviewController {
    
    @Autowired
    private ReviewService rivService;

    @GetMapping("/review/{id}")
    public ResponseEntity <Enquiry> getReviewDetail(@PathVariable int id){
        return new ResponseEntity<>(rivService.getOneReview(id),HttpStatus.OK);
    }

    // @PutMapping("/ticket")
    // public ResponseEntity<Ticket> editReply(@RequestBody Ticket tik){   
    //     try {
    //         Ticket savedTik = tikService.updateTik(tik, tik.getId());
    //         return new ResponseEntity<>(savedTik, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}
  