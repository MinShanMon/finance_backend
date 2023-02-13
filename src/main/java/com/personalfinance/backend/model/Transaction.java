package com.personalfinance.backend.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"user"})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    private String description;
    @NotNull

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @NotBlank
    private String category;
    private double amount;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private RegisteredUsers user;

    public Transaction(String title, String description, LocalDate date, String category, double amount) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

}
