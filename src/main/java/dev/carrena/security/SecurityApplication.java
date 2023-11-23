package dev.carrena.security;

import dev.carrena.security.util.SecretKeyGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


//@Bean
	CommandLineRunner command(){
		return args -> {
			var key = SecretKeyGenerator.generateKey();
			System.out.println(key);
		};
	}
}
