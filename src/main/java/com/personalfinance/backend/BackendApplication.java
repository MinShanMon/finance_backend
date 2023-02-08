package com.personalfinance.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.personalfinance.backend.repository.RegUserRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner cLineRunner(TransactionRepository transactionRepo, RegUserRepository userRepo) {
		return args -> {
			RegUser testUser = new RegUser("ivan", "ivan", "ivan@test.com", "ivaneng");

			//Month of March mock data
			Transaction fm1 = new Transaction("Breakfast", "Cafe",LocalDate.of(2023, 3, 28), "Food", -20.00);
			Transaction fm2 = new Transaction("Lunch", "Hawker", LocalDate.of(2023, 3, 28), "Food", -60.00);
			Transaction fm3 = new Transaction("Dinner", "Restaurant", LocalDate.of(2023, 3, 23), "Food", -120.00);

			Transaction tm1 = new Transaction("Bus/MRT", "", LocalDate.of(2023, 3, 28), "Transport", -24.00);
			Transaction tm2 = new Transaction("Grab", "", LocalDate.of(2023, 3, 28), "Transport", -70.60);

			Transaction om1 = new Transaction("Retail Therapy", "Shirt", LocalDate.of(2023, 3, 28), "Others", -160.70);
			Transaction om2 = new Transaction("Retail Therapy", "Pants", LocalDate.of(2023, 3, 28), "Others", -300.50);
			
			//Month of February mock data
			Transaction f1 = new Transaction("Breakfast", "Cafe",LocalDate.of(2023, 2, 28), "Food", -10.00);
			Transaction f2 = new Transaction("Lunch", "Hawker", LocalDate.of(2023, 2, 28), "Food", -15.00);
			Transaction f3 = new Transaction("Dinner", "Restaurant", LocalDate.of(2023, 2, 23), "Food", -20.00);

			Transaction t1 = new Transaction("Bus/MRT", "", LocalDate.of(2023, 2, 28), "Transport", -6.00);
			Transaction t2 = new Transaction("Grab", "", LocalDate.of(2023, 2, 28), "Transport", -25.20);

			Transaction o1 = new Transaction("Retail Therapy", "Bag", LocalDate.of(2023, 2, 28), "Others", -120.00);
			Transaction o2 = new Transaction("Retail Therapy", "Shoes", LocalDate.of(2023, 2, 28), "Others", -80.50);

			//Month of January mock data
			Transaction fj1 = new Transaction("Breakfast", "Cafe",LocalDate.of(2023, 1, 28), "Food", -10.00);
			Transaction fj2 = new Transaction("Lunch", "Hawker", LocalDate.of(2023, 1, 28), "Food", -30.00);
			Transaction fj3 = new Transaction("Dinner", "Restaurant", LocalDate.of(2023, 1, 23), "Food", -60.00);

			Transaction tj1 = new Transaction("Bus/MRT", "", LocalDate.of(2023, 1, 28), "Transport", -12.00);
			Transaction tj2 = new Transaction("Grab", "", LocalDate.of(2023, 1, 28), "Transport", -35.60);

			Transaction oj1 = new Transaction("Retail Therapy", "Shirt", LocalDate.of(2023, 2, 28), "Others", -80.70);
			Transaction oj2 = new Transaction("Retail Therapy", "Pants", LocalDate.of(2023, 2, 28), "Others", -150.50);

			List<Transaction> transactions = new ArrayList<>(Arrays.asList(f1, f2, f3, t1, t2, o1, o2, fj1, fj2, fj3, tj1, tj2, oj1, oj2, fm1, fm2, fm3, tm1, tm2, om1, om2));
			for (Transaction transaction : transactions) {
				transaction.setUser(testUser);
			}
			userRepo.saveAndFlush(testUser);
			transactionRepo.saveAllAndFlush(transactions);

			testUser.setTransactions(transactions);

			List<Transaction> testList = transactionRepo.findAllTransactionsByUserId(1);
			List<Transaction> testListMonth = transactionRepo.findAllTransactionsByUserIdAndMonth(1, 2);
			System.out.println(testList.size());
			System.out.println(testListMonth.size());


		};

	}
}
