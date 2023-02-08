package com.personalfinance.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.model.FixedDeposits;
import com.personalfinance.backend.service.FixedDepositsService;

@RestController
@RequestMapping("/api")
public class FixedDepositsController {
    


    @Autowired
    FixedDepositsService fixedDepositsService;


    @GetMapping("/fixed/{id}")
    public ResponseEntity<FixedDeposits> getBankById(@PathVariable("id") Long id){
        return fixedDepositsService.findFixedById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addfixed")
    public ResponseEntity<FixedDeposits> addBank(@RequestBody FixedDeposits fixedDeposits) {
        try {
            FixedDeposits savedFixed = fixedDepositsService.addDeposists(fixedDeposits);

            return new ResponseEntity<>(savedFixed, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

// //edit bank
// @PutMapping("/editbank/")
// public ResponseEntity<Bank> editBank(@RequestBody Bank bank) {
//     try {
//         Bank editBank = bankService.editBank(bank, bank.getB_id());
//         return new ResponseEntity<>(editBank, HttpStatus.CREATED);
//     } catch (Exception e) {
//         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }

    @PutMapping("/editfixed/")
    public ResponseEntity<FixedDeposits> editBank(@RequestBody FixedDeposits fixedDeposits) {
        try {
            FixedDeposits editBank = fixedDepositsService.editDeposits(fixedDeposits, fixedDeposits.getF_id());
            return new ResponseEntity<>(editBank, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletefixed/{id}")
    public ResponseEntity<Long> deleteBank(@PathVariable("id") Long id) {
        try {
            boolean done = fixedDepositsService.deleteDeposistsById(id);

            if (!done) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @GetMapping("/fixeds/{b_id}")
    public ResponseEntity<List<FixedDeposits>> getAllDeposits_byBank(@PathVariable("b_id") Long id){
        
        try{
            List<FixedDeposits> f_DepositsList = new ArrayList<FixedDeposits>();
            f_DepositsList = fixedDepositsService.findAllDepositsDependsOnBankId(id);

           

            return new ResponseEntity<>(f_DepositsList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping("/fixeds")
    public ResponseEntity<List<FixedDeposits>> getAllDeposits(){
        
        try{
            List<FixedDeposits> f_DepositsList = new ArrayList<FixedDeposits>();
            f_DepositsList = fixedDepositsService.findAllDeposits();

          
            return new ResponseEntity<>(f_DepositsList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
