package com.ethicalhackingblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class EthicalHackingBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(EthicalHackingBlogApplication.class, args);
	}


	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("adminPass"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
