package com.personalfinance.backend.repo;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.personalfinance.backend.model.Ticket;

@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    // @Query("SELECT e from Enquiry e WHERE e.id =:id ")
    // Enquiry getDetail(@Param("id") Integer id);
}
