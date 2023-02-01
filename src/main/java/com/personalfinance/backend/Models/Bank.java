package com.personalfinance.backend.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banks")

@JsonIgnoreProperties({"b_fixedDeposits"})
public class Bank {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long b_id;

    private String bankName;

    private String bankLink;

    @OneToMany(mappedBy = "fd_bank", cascade = CascadeType.ALL)
    private List<FixedDeposits> b_fixedDeposits;


    public Bank(String bankName, String bankLink){
        this.bankName = bankName;
        this.bankLink = bankLink;
    }

    public Bank(String bankName, String bankLink, List<FixedDeposits> fixedDeposits){
        this.bankName = bankName;
        this.bankLink = bankLink;
        this.b_fixedDeposits = fixedDeposits;
    }


}
