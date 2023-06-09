package com.personalfinance.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.FixedDeposits;
import com.personalfinance.backend.repository.FixedDepositsRepository;

@Service
public class FixedDepositsServiceImpl implements FixedDepositsService{


    @Autowired
    FixedDepositsRepository fixedDepositsRepository;

    @Autowired
    BankService bankService;


    @Override
    public FixedDeposits addDeposists(FixedDeposits fixedDeposits){
        return fixedDepositsRepository.save(fixedDeposits);
    }


    @Override
    public FixedDeposits editDeposits(FixedDeposits fixedDeposits, Long id){
        FixedDeposits currentFixedDeposits = fixedDepositsRepository.findById(id).get();


        if (Objects.nonNull(fixedDeposits.getTenure())) {
            currentFixedDeposits.setTenure(fixedDeposits.getTenure());
        }

        if (Objects.nonNull(fixedDeposits.getMinAmount())) {
            currentFixedDeposits.setMinAmount(fixedDeposits.getMinAmount());
        }

        if (Objects.nonNull(fixedDeposits.getMaxAmount())) {
            currentFixedDeposits.setMaxAmount(fixedDeposits.getMaxAmount());
        }

        if (Objects.nonNull(fixedDeposits.getInterestRate())) {
            currentFixedDeposits.setInterestRate(fixedDeposits.getInterestRate());
        }

        currentFixedDeposits.setFd_bank(bankService.findBankById(fixedDeposits.getFd_bank().getB_id()).get());


        LocalDateTime now = LocalDateTime.now();  
        currentFixedDeposits.setUpdateDate(now);

        return fixedDepositsRepository.save(currentFixedDeposits);

    }

    @Override
    public Boolean deleteDeposistsById(Long id){
        try{
            fixedDepositsRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<FixedDeposits> findAllDepositsDependsOnBankId(Long id){

        return fixedDepositsRepository.findfddoBank(id);

    }

    @Override
    public List<FixedDeposits> findAllDeposits(){

        return fixedDepositsRepository.findAll();

    }

    @Override
    public Optional<FixedDeposits> findFixedById(long id){
        return fixedDepositsRepository.findById(id);
    }
}
