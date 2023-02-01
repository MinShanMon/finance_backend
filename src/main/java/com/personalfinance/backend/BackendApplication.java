package com.personalfinance.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalfinance.backend.model.RegisteredUsers;
import com.personalfinance.backend.model.Role;
import com.personalfinance.backend.service.RegisteredUsersService;
import com.personalfinance.backend.service.RoleService;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(RegisteredUsersService userService, RoleService roleService){
		return args -> {
			// Role admin = roleService.saveRole(new Role("Admin"));
			// Role user = roleService.saveRole(new Role("User"));
			

			// List<Role> role1 = new ArrayList<>();
			// role1.add(new Role("Admin"));
			// role1.add(new Role("User"));


			// List<Role> role2 = new ArrayList<>();
			// role2.add(new Role("User"));

			// userService.saveUser(new RegisteredUsers("oscar", "oscar@gmail.com", "root", role1));
			// userService.saveUser(new RegisteredUsers("shan", "shan@gmail.com", "root", role2));
			
		};
	}

}
