package com.personalfinance.backend.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.repo.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    @Resource
    private ReviewRepository rivRepository;

    @Override
    public Enquiry getOneReview(Integer id){
        // Enquiry enq = rivRepository.findById(id).get();
        // if(enq.getTicket().getTikStatus().
        // equals(TicketStatusEnum.OPEN)){
        return rivRepository.getDetail(id);
    //     } 
    //     return rivRepository.getDetail(id);
    }
    
}
