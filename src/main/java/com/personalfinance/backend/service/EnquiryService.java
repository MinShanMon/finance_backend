package com.personalfinance.backend.service;

import java.util.List;
import com.personalfinance.backend.model.Enquiry;

public interface EnquiryService {

    List<Enquiry>  getAllEnquiry(); 
    List<Enquiry>  getOpenEnquiry(); 
    List<Enquiry>  getClosedEnquiry(); 
    Enquiry getOneEnquiry(Integer id);
    
}

