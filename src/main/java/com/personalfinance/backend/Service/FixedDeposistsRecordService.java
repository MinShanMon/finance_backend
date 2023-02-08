package com.personalfinance.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.personalfinance.backend.model.FixedDepositsRecords;
import com.personalfinance.backend.repository.FixedDepositsRecodesRepository;

public interface FixedDeposistsRecordService {
 
    FixedDepositsRecords recordFixedDeposits(FixedDepositsRecords fixedDepositsRecords);

    List<FixedDepositsRecords> listRecord();

}
