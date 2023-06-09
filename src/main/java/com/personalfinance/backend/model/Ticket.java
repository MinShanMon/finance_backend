package com.personalfinance.backend.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reply;
    
    @Column(name="status",nullable = false, columnDefinition = "ENUM('OPEN','CLOSED')")
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum tikStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime reply_dateTime;

    @JsonIgnore
    @OneToOne(mappedBy = "ticket")
    private Enquiry enquiry;

    public Ticket(String reply,TicketStatusEnum tikStatus, LocalDateTime reply_dateTime){ 

        this.reply= reply;
        this.tikStatus = tikStatus;
        this.reply_dateTime = reply_dateTime;
    }

    public Ticket(String reply){
        this.reply = reply;
    }

}
