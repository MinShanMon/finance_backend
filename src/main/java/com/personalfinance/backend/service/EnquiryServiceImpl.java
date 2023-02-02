package com.personalfinance.backend.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repo.EnquiryRepository;


@Service
public class EnquiryServiceImpl implements EnquiryService {
    @Resource
    private EnquiryRepository enqRepository;

    @Override
    public List<Enquiry> getAllEnquiry(){
       return enqRepository.findAll();
    }

        
    @Override
    public List<Enquiry> getOpenEnquiry(){
        List<Enquiry> enquiries = enqRepository.findAll();
        List<Enquiry> openEnquiries = enquiries.stream().filter(u -> u.getTicket().getTikStatus().
        equals(TicketStatusEnum.OPEN)).collect(Collectors.toList());

        return openEnquiries;
    }

    @Override
    public List<Enquiry> getClosedEnquiry() {
        List<Enquiry> enquiries = enqRepository.findAll();
        List<Enquiry> closedEnquiries = enquiries.stream().filter(u -> u.getTicket().getTikStatus().
        equals(TicketStatusEnum.CLOSED)).collect(Collectors.toList());

        return closedEnquiries;
    }

    @Override
    public Enquiry getOneEnquiry(Integer id){
        Enquiry enq = enqRepository.findById(id).get();
        if(enq.getTicket().getTikStatus().
        equals(TicketStatusEnum.OPEN)){
        return enqRepository.getDetail(id);
        } 
        return enqRepository.getDetail(id);

    }
}

