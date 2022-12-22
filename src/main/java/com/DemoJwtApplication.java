package com;

import com.Entity.User;
import com.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoJwtApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoJwtApplication.class, args);
	}

	@Autowired
	IUserRepository repository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserName("Dolphin");
		user.setPassword(passwordEncoder.encode("Dolphin"));
		repository.save(user);
		System.out.println(user);
	}
}
