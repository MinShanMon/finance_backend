package com.personalfinance.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enquiries")
@JsonIgnoreProperties({"ticket"})
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="enquiryType",nullable = false, columnDefinition = "ENUM('ACCOUNT','PRODUCT','FEEDBACK','OTHER')")
    @Enumerated(EnumType.STRING)
    private EnquiryTypeEnum enquiryType;

    @Column(name="salutation",nullable = false, columnDefinition = "ENUM('MS','MRS','MDM','MR')")
    @Enumerated(EnumType.STRING)
    private SalutationEnum title;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "surname")
    private String surName;

    private String email;

    @Column(name = "contact_number")
    private String phoneNum;

    private String question;

    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    
    private int star;

    @OneToOne
    @JoinColumn(name="ticket_id")
    private Ticket ticket;

    public Enquiry(EnquiryTypeEnum enquiryType,SalutationEnum title, String givenName,String surName,String email,
    String phoneNum,String question, LocalDateTime dateTime,int star,Ticket ticket){ 

        this.enquiryType= enquiryType;
        this.title = title;
        this.givenName = givenName;
        this.surName = surName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.question = question;
        this.dateTime = dateTime;
        this.star = star;
        this.ticket = ticket;
    }
}

