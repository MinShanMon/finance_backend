package com.personalfinance.backend.service;

import com.personalfinance.backend.exception.ResourceNotFoundException;
import com.personalfinance.backend.model.Ticket;

public interface TicketService {

    Ticket saveTik(Ticket tik);
    Ticket updateTik(Ticket updateTik,Integer id);
    boolean sendEmail(Integer id) throws ResourceNotFoundException;
    
}