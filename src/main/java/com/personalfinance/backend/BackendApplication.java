package com.personalfinance.backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.model.RegUser;
import com.personalfinance.backend.repository.TransactionRepository;
import com.personalfinance.backend.repository.UserRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner cLineRunner(TransactionRepository transactionRepo, UserRepository userRepo) {
		return args -> {
			RegUser testUser = new RegUser("ivan", "ivan", "ivan@test.com", "ivaneng");
			

			Transaction f1 = new Transaction("Breakfast", "Cafe",LocalDate.now(), "Food", 10.00);
			Transaction f2 = new Transaction("Lunch", "Hawker", LocalDate.now(), "Food", 15.00);
			Transaction f3 = new Transaction("Dinner", "Restaurant", LocalDate.now(), "Food", 20.00);

			Transaction t1 = new Transaction("Bus/MRT", "", LocalDate.now(), "Transport", 6.00);
			Transaction t2 = new Transaction("Grab", "", LocalDate.now(), "Transport", 25.20);

			List<Transaction> transactions = new ArrayList<>(Arrays.asList(f1, f2, f3, t1, t2));
			for (Transaction transaction : transactions) {
				transaction.setUser(testUser);
			}
			userRepo.saveAndFlush(testUser);
			transactionRepo.saveAllAndFlush(transactions);

			testUser.setTransactions(transactions);


		};

	}
}
