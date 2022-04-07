package com.kanto.ecommerce;

import com.kanto.ecommerce.entity.AppRole;
import com.kanto.ecommerce.entity.AppUser;
import com.kanto.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringEcommerceApplication implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEcommerceApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
				accountService.saveUser(new AppUser(1, "admin", "1234", null)) ;
				accountService.saveUser(new AppUser(2, "user", "1234", null)) ;
				accountService.saveRole(new AppRole(1,"ADMIN" ));
				accountService.saveRole(new AppRole(2,"USER" ));
				accountService.addRoleToUser("admin", "ADMIN");
				accountService.addRoleToUser("admin", "USER");
				accountService.addRoleToUser("user", "USER");
	}


}
