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
			// roleService.saveRole(new Role("Admin"));
			// roleService.saveRole(new Role("User"));
			// Role admin = roleService.findRoleByName("Admin");
			// Role user = roleService.findRoleByName("User");
			// List<Role> role1 = new ArrayList<>();

			// role1.add(admin);
			// role1.add(user);

			// List<Role> role2 = new ArrayList<>();
			// role2.add(user);

			// RegisteredUsers osc = new RegisteredUsers("oscar", "oscarshan2017@gmail.com", "root", role1);
			// RegisteredUsers sh = new RegisteredUsers("shan", "shan@gmail.com", "root", role2);
			// userService.saveUser(osc);
			// userService.saveUser(sh);



			// // Role r = roleService.findRoleByName("User");
			// System.out.println(admin.getName());
			// System.out.println(role1.get(1).getName());
		};
	}

}
