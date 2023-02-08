package com.personalfinance.backend.service;

import com.personalfinance.backend.model.Enquiry;

public interface ReviewService {
    Enquiry getOneReview(Integer id);
    Enquiry updateReview(Enquiry updateEnq,Integer id);
}
