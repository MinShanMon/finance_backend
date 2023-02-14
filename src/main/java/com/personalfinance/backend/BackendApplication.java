package com.personalfinance.backend;

import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalfinance.backend.model.Bank;
import com.personalfinance.backend.model.Enquiry;
import com.personalfinance.backend.model.EnquiryTypeEnum;
import com.personalfinance.backend.model.FixedDeposits;
import com.personalfinance.backend.model.MonthlyTransaction;
import com.personalfinance.backend.model.RegisteredUsers;
import com.personalfinance.backend.model.Role;
import com.personalfinance.backend.model.SalutationEnum;
import com.personalfinance.backend.model.StatusEnum;
import com.personalfinance.backend.model.Ticket;
import com.personalfinance.backend.model.TicketStatusEnum;
import com.personalfinance.backend.model.Transaction;
import com.personalfinance.backend.repository.BankRepository;
import com.personalfinance.backend.repository.EnquiryRepository;
import com.personalfinance.backend.repository.FixedDepositsRepository;
import com.personalfinance.backend.service.MonthlyTransactionService;
import com.personalfinance.backend.service.RegisteredUsersService;
import com.personalfinance.backend.service.RoleService;
import com.personalfinance.backend.repository.MonthlyTransactionRepository;

import com.personalfinance.backend.repository.TicketRepository;
import com.personalfinance.backend.repository.TransactionRepository;

import org.checkerframework.checker.units.qual.min;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableScheduling
public class BackendApplication {

	
	
	@Autowired
    MonthlyTransactionService monthlyTransactionService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		// LocalDate date = LocalDate.now();
		// System.out.print(date.getDayOfMonth());

		// if(date.getDayOfMonth() == 1 ){

		// List<FixedDeposits> allFixedDeposits =
		// FixedDepositsService.findAllDeposits();

		// if(date.getMonthValue() == 12){

		// }

		// }
	}

	@Bean
	public CommandLineRunner run(BankRepository bankRepository, FixedDepositsRepository fixedDepositsRepository,
			RegisteredUsersService userService, RoleService roleService, EnquiryRepository enqRepository,
			TicketRepository tikRepository, TransactionRepository transactionRepository,
			MonthlyTransactionRepository monthlyTransactionRepo, MonthlyTransactionService monthlyTransactionService) {
		return args -> {

			Bank dbs = bankRepository.saveAndFlush(new Bank("dbs", "https://dbs.com"));
			Bank uob = bankRepository.saveAndFlush(new Bank("uob", "https://uob.com"));
			Bank ocbc = bankRepository.saveAndFlush(new Bank("ocbc", "https://ocbc.com"));
			Bank posb = bankRepository.saveAndFlush(new Bank("posb", "https://posb.com"));

			LocalDateTime now = LocalDateTime.now();
			FixedDeposits fd1 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6, 10, 999, 0.02, now, dbs));
			FixedDeposits fd2 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12, 10, 969, 0.02, now, dbs));
			FixedDeposits fd3 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6, 10, 95599, 0.02, now, uob));
			FixedDeposits fd4 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(8, 10, 239, 0.02, now, ocbc));
			FixedDeposits fd5 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12, 10, 2399, 0.02, now, posb));
			FixedDeposits fd6 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6, 10, 9199, 0.02, now, dbs));
			FixedDeposits fd7 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12, 10, 299, 0.02, now, dbs));
			FixedDeposits fd8 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6, 10, 909, 0.02, now, uob));
			FixedDeposits fd9 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(8, 10, 239, 0.02, now, ocbc));
			FixedDeposits fd0 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12, 10, 499, 0.02, now, posb));
			fixedDepositsRepository.saveAndFlush(fd1);
			fixedDepositsRepository.saveAndFlush(fd2);
			fixedDepositsRepository.saveAndFlush(fd3);
			fixedDepositsRepository.saveAndFlush(fd4);
			fixedDepositsRepository.saveAndFlush(fd5);
			fixedDepositsRepository.saveAndFlush(fd6);
			fixedDepositsRepository.saveAndFlush(fd7);
			fixedDepositsRepository.saveAndFlush(fd8);
			fixedDepositsRepository.saveAndFlush(fd9);
			fixedDepositsRepository.saveAndFlush(fd0);

			roleService.saveRole(new Role("Admin"));
			roleService.saveRole(new Role("User"));
			List<Role> role2 = new ArrayList<>();
			Role admin = roleService.findRoleByName("Admin");
			Role user = roleService.findRoleByName("User");
			role2.add(admin);
			role2.add(user);
			RegisteredUsers osc = new RegisteredUsers("shanmon", "oscar@gmail.com", "root", role2,
					StatusEnum.ACTIVATED);
			userService.saveUser(osc);

			Ticket tik1 = tikRepository
					.saveAndFlush(new Ticket("http://www.google.com", TicketStatusEnum.OPEN, LocalDateTime.now()));
			tikRepository.saveAndFlush(tik1);

			Ticket tik2 = tikRepository
					.saveAndFlush(new Ticket("FIND THIS", TicketStatusEnum.OPEN, LocalDateTime.now().minusDays(3)));
			tikRepository.saveAndFlush(tik2);

			Ticket tik3 = tikRepository.saveAndFlush(
					new Ticket("CALL THIS NUMBER", TicketStatusEnum.CLOSED, LocalDateTime.now().minusDays(8)));
			tikRepository.saveAndFlush(tik3);

			Ticket tik4 = tikRepository
					.saveAndFlush(new Ticket("GOOD", TicketStatusEnum.CLOSED, LocalDateTime.now().minusDays(10)));
			tikRepository.saveAndFlush(tik4);

			Ticket tik5 = tikRepository
					.saveAndFlush(new Ticket("GOOD", TicketStatusEnum.CLOSED, LocalDateTime.now().minusDays(18)));
			tikRepository.saveAndFlush(tik5);


			Enquiry enq1 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.PRODUCT, "Adele Tan",
			"blissyetbloom@gmail.com", "12345678", "how to buy fet",LocalDateTime.now(), 4,"good",tik1));
			enqRepository.saveAndFlush(enq1);

			Enquiry enq2 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.FEEDBACK, "KIM Jean",
			"blissyetbloom@gmail.com", "556789", "Good service", LocalDateTime.now().minusDays(3),5,"good",tik2));
			enqRepository.saveAndFlush(enq2);
			
			
			Enquiry enq3 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.ACCOUNT, "JOHN Tan",
			"blissyetbloom@gmail.com", "000000", "how to register account",LocalDateTime.now().minusDays(8), 3,"good",tik3));
			enqRepository.saveAndFlush(enq3);

			Enquiry enq4 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.FEEDBACK, "KIM Jean",
			"456@gmail.com", "556789", "bad service", LocalDateTime.now().minusDays(10),1,"good",tik4));
			enqRepository.saveAndFlush(enq4);

			Enquiry enq5 = enqRepository.saveAndFlush(new Enquiry(EnquiryTypeEnum.PRODUCT, "Davie Hans",
			"000@gmail.com", "000000", "how to buy fet",LocalDateTime.now().minusDays(20).minusYears(2), 4,"good",tik5));
			enqRepository.saveAndFlush(enq5);

			for (int i = 0; i < 200; i++) {
				Transaction randomTransaction = new Transaction();
				randomTransaction.setAmount(Math.floor(Math.random() * 10000) / 100);
				randomTransaction.setUser(osc);

				long minEpochDay = 358 * 53 + 19;
				long maxEpochDay = 358 * 54 + 25;
				long range = maxEpochDay - minEpochDay + 1;
				LocalDate randomDate = LocalDate.ofEpochDay((long) (Math.random() * range) + minEpochDay);
				randomTransaction.setDate(randomDate);
				randomTransaction.setTitle("Feast");
				randomTransaction.setCategory("Others");
				transactionRepository.save(randomTransaction);
			}

			// List<Transaction> allTransactions = transactionRepository.findAll();
			// Map<Month, Double> mockTransactionsMap = allTransactions.stream().collect(
			// 	Collectors.groupingBy(t -> t.getDate().getMonth(),
			// 		Collectors.summingDouble(Transaction::getAmount))
			// );
			// mockTransactionsMap.forEach(
			// 	(month, total) -> {
			// 		MonthlyTransaction monthlyTransaction = new MonthlyTransaction();
			// 		monthlyTransaction.setAmount(total);
			// 		monthlyTransaction.setDate(LocalDate.of(2022, month, 1));
			// 		monthlyTransaction.setUserId(1);
			// 		monthlyTransactionRepo.save(monthlyTransaction);
			// 	}
			// );
			
		};

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Scheduled(cron = "0 */1 * * * ?")
    private void updateMonthlyTransaction(){
        System.out.println("hello");
        monthlyTransactionService.updateMonthlyTransactions();
    }
}
