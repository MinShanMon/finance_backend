package com.personalfinance.backend.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class EnquiryServiceImpl implements EnquiryService {
    @Resource
    private EnquiryRepository enqRepository;

    @Autowired
    private TicketService ticketService;

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

    @Override
    public Enquiry submitEnquiry(Enquiry enquiry) {
        enquiry.setTicket(ticketService.addTik());
        return enqRepository.save(enquiry);
    }

}




