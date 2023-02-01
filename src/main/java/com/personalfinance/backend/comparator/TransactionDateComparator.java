package com.personalfinance.backend.comparator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.personalfinance.backend.model.Transaction;

@Component
public class TransactionDateComparator implements Comparator<Transaction> {
    
    @Override
    public int compare(Transaction t0, Transaction t1) {
        LocalDate date0 = t0.getDate();
        LocalDate date1 = t1.getDate();

        if (date0.isBefore(date1))
            return 1;
        if (date0.isAfter(date1))
            return -1;
        else
            return 0;
    }
}
