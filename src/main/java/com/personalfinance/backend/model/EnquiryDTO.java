package com.personalfinance.backend.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class EnquiryDTO {
    
    private String enquiryType;
    private String fullName;
    private String email;
    private String question;
    private LocalDateTime enquiryDateTime;

    public EnquiryDTO() {}

    public String getEnquiryType() {
        return enquiryType;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }

    public LocalDateTime getEnquiryDateTime() {
        return enquiryDateTime;
    }

    

}
