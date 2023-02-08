package com.personalfinance.backend.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.repo.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    @Resource
    private ReviewRepository rivRepository;

    @Override
    public Enquiry getOneReview(Integer id){
        return rivRepository.getDetail(id);
    }

    @Override
    public Enquiry updateReview(Enquiry updateEnq,Integer id){
        Enquiry enq = rivRepository.findById(id).get();
        if(enq != null){
            enq.setRating(updateEnq.getRating());
            enq.setComment(updateEnq.getComment());
            return rivRepository.saveAndFlush(enq);
        }
        return rivRepository.save(enq);
    }
    
}
