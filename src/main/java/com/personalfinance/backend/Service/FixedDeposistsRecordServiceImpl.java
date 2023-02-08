package com.personalfinance.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.Models.FixedDepositsRecords;
import com.personalfinance.backend.Repository.FixedDepositsRecodesRepository;
import com.personalfinance.backend.Repository.FixedDepositsRepository;

@Service
public class FixedDeposistsRecordServiceImpl implements FixedDeposistsRecordService{
 
    @Autowired
    FixedDepositsRecodesRepository fixedDepositsRecodesRepository;


    @Override
    public FixedDepositsRecords recordFixedDeposits(FixedDepositsRecords fixedDepositsRecords){
        return fixedDepositsRecodesRepository.save(fixedDepositsRecords);
    }

    @Override
    public List<FixedDepositsRecords> listRecord(){

        return fixedDepositsRecodesRepository.findAll();

    }
}
