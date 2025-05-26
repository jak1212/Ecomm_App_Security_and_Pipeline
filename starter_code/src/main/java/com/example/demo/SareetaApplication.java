package com.example.demo;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.demo.model.persistence.repositories")
@EntityScan("com.example.demo.model.persistence")
@SpringBootApplication
public class SareetaApplication {

	public static void main(String[] args) {
//		String secret = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
//		System.out.println("Secret key here: " + secret );
		SpringApplication.run(SareetaApplication.class, args);
	}

}
