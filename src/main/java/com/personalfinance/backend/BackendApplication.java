package com.personalfinance.backend;

import org.springframework.boot.CommandLineRunner;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalfinance.backend.model.Bank;
import com.personalfinance.backend.model.FixedDeposits;
import com.personalfinance.backend.repository.BankRepository;
import com.personalfinance.backend.repository.FixedDepositsRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class BackendApplication {

	@Autowired
    
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		// LocalDate date = LocalDate.now();
		// System.out.print(date.getDayOfMonth());
		


		// if(date.getDayOfMonth() == 1 ){

		// 	List<FixedDeposits> allFixedDeposits = FixedDepositsService.findAllDeposits();

		// 	if(date.getMonthValue() == 12){

		// 	}

		// }
	}

	@Bean
	public CommandLineRunner run(BankRepository bankRepository, FixedDepositsRepository fixedDepositsRepository) {
		return args -> {


			// Bank dbs = bankRepository.saveAndFlush(new Bank("dbs", "https://dbs.com"));
			// Bank uob = bankRepository.saveAndFlush(new Bank("uob", "https://uob.com"));
			// Bank ocbc = bankRepository.saveAndFlush(new Bank("ocbc", "https://ocbc.com"));
			// Bank posb = bankRepository.saveAndFlush(new Bank("posb", "https://posb.com"));


			// LocalDateTime now = LocalDateTime.now(); 
			// FixedDeposits fd1 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,999,0.02,now,dbs));
			// FixedDeposits fd2 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,969,0.02,now,dbs));
			// FixedDeposits fd3 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,95599,0.02,now,uob));	
			// FixedDeposits fd4 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(8,10,239,0.02,now,ocbc));	
			// FixedDeposits fd5 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,2399,0.02,now,posb));	
			// FixedDeposits fd6 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,9199,0.02,now,dbs));
			// FixedDeposits fd7 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,299,0.02,now,dbs));
			// FixedDeposits fd8 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,909,0.02,now,uob));	
			// FixedDeposits fd9 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(8,10,239,0.02,now,ocbc));	
			// FixedDeposits fd0 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,499,0.02,now,posb));	
			// fixedDepositsRepository.saveAndFlush(fd1);
			// fixedDepositsRepository.saveAndFlush(fd2);
			// fixedDepositsRepository.saveAndFlush(fd3);
			// fixedDepositsRepository.saveAndFlush(fd4);
			// fixedDepositsRepository.saveAndFlush(fd5);

			// fixedDepositsRepository.saveAndFlush(fd6);
			// fixedDepositsRepository.saveAndFlush(fd7);
			// fixedDepositsRepository.saveAndFlush(fd8);
			// fixedDepositsRepository.saveAndFlush(fd9);
			// fixedDepositsRepository.saveAndFlush(fd0);

	   };

	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
