package com.personalfinance.backend;

import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalfinance.backend.service.RegisteredUsersService;
import com.personalfinance.backend.service.RoleService;

import com.personalfinance.backend.repository.RegisteredUsersRepository;
import com.personalfinance.backend.repository.TransactionRepository;


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


			Bank dbs = bankRepository.saveAndFlush(new Bank("dbs", "https://dbs.com"));
			Bank uob = bankRepository.saveAndFlush(new Bank("uob", "https://uob.com"));
			Bank ocbc = bankRepository.saveAndFlush(new Bank("ocbc", "https://ocbc.com"));
			Bank posb = bankRepository.saveAndFlush(new Bank("posb", "https://posb.com"));


			LocalDateTime now = LocalDateTime.now(); 
			FixedDeposits fd1 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,999,0.02,now,dbs));
			FixedDeposits fd2 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,969,0.02,now,dbs));
			FixedDeposits fd3 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,95599,0.02,now,uob));	
			FixedDeposits fd4 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(8,10,239,0.02,now,ocbc));	
			FixedDeposits fd5 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,2399,0.02,now,posb));	
			FixedDeposits fd6 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,9199,0.02,now,dbs));
			FixedDeposits fd7 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,299,0.02,now,dbs));
			FixedDeposits fd8 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(6,10,909,0.02,now,uob));	
			FixedDeposits fd9 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(8,10,239,0.02,now,ocbc));	
			FixedDeposits fd0 = fixedDepositsRepository.saveAndFlush(new FixedDeposits(12,10,499,0.02,now,posb));	
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

	   };

	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// CommandLineRunner run(RegisteredUsersService userService, RoleService roleService, RegisteredUsersRepository registeredUsersRepository, TransactionRepository transactionRepo){
	// 	return args -> {
			// String str = userService.asciiToHex("abcd");

			// String str2 = userService.asciiToHex("1234");
			// System.out.println(str+"and" + str2);
			// roleService.saveRole(new Role("Admin"));
			// roleService.saveRole(new Role("User"));
			// Role admin = roleService.findRoleByName("Admin");
			// Role user = roleService.findRoleByName("User");
			// List<Role> role1 = new ArrayList<>();

			// role1.add(admin);
			// role1.add(user);

			// List<Role> role2 = new ArrayList<>();
			// role2.add(user);

			// RegisteredUsers osc = new RegisteredUsers("oscar", "oscarshan2017@gmail.com", "root", role1, StatusEnum.ACTIVATED);
			// RegisteredUsers sh = new RegisteredUsers("shan", "shan@gmail.com", "root", role2, StatusEnum.ACTIVATED);
			// userService.saveUser(osc);
			// userService.saveUser(sh);

			// // Role r = roleService.findRoleByName("User");
			// System.out.println(admin.getName());
			// System.out.println(role1.get(1).getName());


			// admin

			
			
			// roleService.saveRole(new Role("Admin"));
			// roleService.saveRole(new Role("User"));
			// Role admin = roleService.findRoleByName("Admin");
			// Role user = roleService.findRoleByName("User");
			// List<Role> role1 = new ArrayList<>();
			// role1.add(user);
			// RegisteredUsers testUser = new RegisteredUsers("ivan", "ivan@test.com", "root", role1 , StatusEnum.ACTIVATED);
			// userService.saveUser(testUser);

			// List<Role> role2 = new ArrayList<>();
			// role2.add(admin);
			// role2.add(user);
			// RegisteredUsers osc = new RegisteredUsers("shanmon", "oscar@gmail.com", "root", role2, StatusEnum.ACTIVATED);
			// userService.saveUser(osc);

			
			// // //Month of February mock data
			
			// Transaction f1 = new Transaction("Breakfast", "Cafe",LocalDate.of(2023, 2, 28), "Food", -10.00);
			// Transaction f2 = new Transaction("Lunch", "Hawker", LocalDate.of(2023, 2, 28), "Food", -15.00);
			// Transaction f3 = new Transaction("Dinner", "Restaurant", LocalDate.of(2023, 2, 23), "Food", -20.00);

			// Transaction t1 = new Transaction("Bus/MRT", "", LocalDate.of(2023, 2, 28), "Transport", -6.00);
			// Transaction t2 = new Transaction("Grab", "", LocalDate.of(2023, 2, 28), "Transport", -25.20);

			// Transaction o1 = new Transaction("Retail Therapy", "Bag", LocalDate.of(2023, 2, 28), "Others", -120.00);
			// Transaction o2 = new Transaction("Retail Therapy", "Shoes", LocalDate.of(2023, 2, 28), "Others", -80.50);

			// //Month of January mock data
			// Transaction fj1 = new Transaction("Breakfast", "Cafe",LocalDate.of(2023, 1, 28), "Food", -10.00);
			// Transaction fj2 = new Transaction("Lunch", "Hawker", LocalDate.of(2023, 1, 28), "Food", -30.00);
			// Transaction fj3 = new Transaction("Dinner", "Restaurant", LocalDate.of(2023, 1, 23), "Food", -60.00);

			// Transaction tj1 = new Transaction("Bus/MRT", "", LocalDate.of(2023, 1, 28), "Transport", -12.00);
			// Transaction tj2 = new Transaction("Grab", "", LocalDate.of(2023, 1, 28), "Transport", -35.60);

			// Transaction oj1 = new Transaction("Retail Therapy", "Shirt", LocalDate.of(2023, 2, 28), "Others", -80.70);
			// Transaction oj2 = new Transaction("Retail Therapy", "Pants", LocalDate.of(2023, 2, 28), "Others", -150.50);

			// List<Transaction> transactions = new ArrayList<>(Arrays.asList(f1, f2, f3, t1, t2, o1, o2, fj1, fj2, fj3, tj1, tj2, oj1, oj2));
			// for (Transaction transaction : transactions) {
			// 	transaction.setUser(testUser);
			// }
			// registeredUsersRepository.saveAndFlush(testUser);
			// transactionRepo.saveAllAndFlush(transactions);

			// testUser.setTransactions(transactions);

			// List<Transaction> testList = transactionRepo.findAllTransactionsByUserId(1);
			// List<Transaction> testListMonth = transactionRepo.findAllTransactionsByUserIdAndMonth(1, 2);
			// System.out.println(testList.size());
			// System.out.println(testListMonth.size());
	// 	};
	// }
	
	// public CommandLineRunner cLineRunner(RegisteredUsersService userService, RoleService roleService, TransactionRepository transactionRepo, RegUserRepository userRepo) {
	// 	return args -> {




			


		// };

	// }

}
