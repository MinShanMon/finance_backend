package com.personalfinance.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.personalfinance.backend.Models.FixedDepositsRecords;
import com.personalfinance.backend.Repository.FixedDepositsRecodesRepository;

public interface FixedDeposistsRecordService {
 
    FixedDepositsRecords recordFixedDeposits(FixedDepositsRecords fixedDepositsRecords);

    List<FixedDepositsRecords> listRecord();

}
