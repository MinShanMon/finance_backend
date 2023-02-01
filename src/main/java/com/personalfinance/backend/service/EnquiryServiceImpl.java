package com.personalfinance.backend.service;

import java.util.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.repo.EnquiryRepository;


@Service
public class EnquiryServiceImpl implements EnquiryService {
    @Resource
    private EnquiryRepository enqRepository;

    @Override
    public List<Enquiry> getAllEnquiry(){
       return enqRepository.findAll();
    }
}

