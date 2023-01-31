package com.personalfinance.backend.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.Models.Bank;
import com.personalfinance.backend.Models.FixedDeposits;
import com.personalfinance.backend.Repository.BankRepository;


@Service
public class BankServiceImpl implements BankService {
    

    @Autowired
    BankRepository bankRepository;

    @Override
    public List<Bank> findAllBank(){
        return bankRepository.findAll();
    }

    @Override
    public Optional<Bank> findBankById(long id){
        return bankRepository.findById(id);
    }

    @Override
    public Bank addBank(Bank bank){
        return bankRepository.save(bank);
    }

    @Override
    public Bank editBank(Bank bank, Long id){
        Bank currentBank = bankRepository.findById(id).get();

        if (Objects.nonNull(bank.getBankName()) && !"".equalsIgnoreCase(bank.getBankName())) {
            currentBank.setBankName(bank.getBankName());
        }

        if (Objects.nonNull(bank.getBankLink()) && !"".equalsIgnoreCase(bank.getBankName())){
            currentBank.setBankLink(bank.getBankLink());
        }

        return bankRepository.save(currentBank);
    }

    @Override
    public Boolean deleteBankById(Long id){
        try {
            bankRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // @Override
    // public List<FixedDeposits> findAllDeposists_dependsOnBank(Long id){
    //     Bank bank = bankRepository.findById(id).get();
    //     return bankRepository.findfddoBank(bank.getB_id());
    // }


}
