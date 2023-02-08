package com.personalfinance.backend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.backend.Models.Bank;
import com.personalfinance.backend.Service.BankService;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    BankService bankService;


    //retrieve all banks
    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getAllBank(){
        
        try{
            List<Bank> bankList = new ArrayList<Bank>();
            bankList = bankService.findAllBank();

          
            return new ResponseEntity<>(bankList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //retrieve bank by id
    @GetMapping("/bank/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable("id") Long id){
        return bankService.findBankById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //add new bank
    @PostMapping("/addbank")
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        try {
            Bank savedBank = bankService.addBank(bank);

            return new ResponseEntity<>(savedBank, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //edit bank
    @PutMapping("/editbank/")
    public ResponseEntity<Bank> editBank(@RequestBody Bank bank) {
        try {
            Bank editBank = bankService.editBank(bank, bank.getB_id());
            return new ResponseEntity<>(editBank, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //delete bank by id
    @DeleteMapping("/deletebank/{id}")
    public ResponseEntity<Long> deleteBank(@PathVariable("id") Long id) {
        try {
            boolean done = bankService.deleteBankById(id);

            if (!done) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //retrieve all banks
    // @GetMapping("/fixeds/{id}")
    // public ResponseEntity<List<FixedDeposits>> getAllDeposits_byBank(@PathVariable("id") Long id){
        
    //     try{
    //         List<FixedDeposits> f_DepositsList = new ArrayList<FixedDeposits>();
    //         f_DepositsList = bankService.findAllDeposists_dependsOnBank(id);

    //         if(f_DepositsList.isEmpty()){
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }

    //         return new ResponseEntity<>(f_DepositsList, HttpStatus.OK);
    //     }catch(Exception e){
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }







    

}
