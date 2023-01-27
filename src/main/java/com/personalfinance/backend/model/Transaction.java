package com.personalfinance.backend.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private LocalDate date;
    @NotBlank
    private String category;
    private double amount;

    @ManyToOne
    @NotNull
    private RegUser user;

    public Transaction(String title, String description, LocalDate date, String category, double amount) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }


}
