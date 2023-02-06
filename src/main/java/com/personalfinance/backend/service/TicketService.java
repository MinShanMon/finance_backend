package com.personalfinance.backend.service;

import com.personalfinance.backend.exception.ResourceNotFoundException;
import com.personalfinance.backend.model.Ticket;

public interface TicketService {

    boolean sendEmail(Integer id) throws ResourceNotFoundException;
    
    Ticket updateReply(Ticket tik, Integer id);
}