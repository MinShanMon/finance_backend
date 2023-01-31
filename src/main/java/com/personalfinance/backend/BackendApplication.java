package com.personalfinance.backend;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.personalfinance.backend.Models.Bank;
import com.personalfinance.backend.Models.FixedDeposits;
import com.personalfinance.backend.Repository.BankRepository;
import com.personalfinance.backend.Repository.FixedDepositsRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(BankRepository bankRepository, FixedDepositsRepository fixedDepositsRepository) {
		return args -> {
			Bank dbs = bankRepository.saveAndFlush(new Bank("dbs", "https://dbs.com"));
			Bank posb = bankRepository.saveAndFlush(new Bank("posb", "https://posb.com"));
			Bank uob = bankRepository.saveAndFlush(new Bank("uob", "https://uob.com"));

			bankRepository.saveAndFlush(dbs);
			bankRepository.saveAndFlush(posb);
			bankRepository.saveAndFlush(uob);

			
			LocalDateTime now = LocalDateTime.now(); 
			FixedDeposits fd1 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,999,0.02,now,dbs));
			FixedDeposits fd2 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,999,0.02,now,dbs));
			FixedDeposits fd3 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,999,0.02,now,uob));	
			fixedDepositsRepository.saveAndFlush(fd1);
			fixedDepositsRepository.saveAndFlush(fd2);
			fixedDepositsRepository.saveAndFlush(fd3);

	   };

	}

}
