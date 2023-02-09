package com.personalfinance.backend;

import org.springframework.boot.CommandLineRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalfinance.backend.model.Bank;
import com.personalfinance.backend.model.FixedDeposits;
import com.personalfinance.backend.model.RegisteredUsers;
import com.personalfinance.backend.model.Role;
import com.personalfinance.backend.model.StatusEnum;
import com.personalfinance.backend.repository.BankRepository;
import com.personalfinance.backend.repository.FixedDepositsRepository;
import com.personalfinance.backend.service.RegisteredUsersService;
import com.personalfinance.backend.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class BackendApplication {



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
	RegisteredUsersService userService, RoleService roleService) {
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

		};

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
