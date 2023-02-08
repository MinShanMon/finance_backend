package com.personalfinance.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.model.FixedDepositsRecords;
import com.personalfinance.backend.service.FixedDeposistsRecordService;

@RestController
@RequestMapping("/api")
public class FixedDepositsRecordsController {
    


    @Autowired
    FixedDeposistsRecordService fixedDeposistsRecordService;


    @PostMapping("/recordfixed")
    public ResponseEntity<FixedDepositsRecords> recordFixed(@RequestBody FixedDepositsRecords fixedDepositsRecords) {
        try {
            FixedDepositsRecords recordFixed = fixedDeposistsRecordService.recordFixedDeposits(fixedDepositsRecords);

            return new ResponseEntity<>(recordFixed, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<FixedDepositsRecords>> getallrecord(){
        
        try{
            List<FixedDepositsRecords> recordList = new ArrayList<FixedDepositsRecords>();
            recordList = fixedDeposistsRecordService.listRecord();

          
            return new ResponseEntity<>(recordList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
