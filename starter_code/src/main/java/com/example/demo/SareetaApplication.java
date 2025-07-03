package com.example.demo;

import com.example.demo.Configuration.Security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;
import java.util.Base64;

@EnableJpaRepositories("com.example.demo.model.persistence.repositories")
@EntityScan("com.example.demo.model.persistence")
@SpringBootApplication
public class SareetaApplication {
	@Autowired
	public PasswordEncoder passwordEncoder;



	public static void main(String[] args) {
////		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
////		String base64Secret = Encoders.BASE64.encode(key.getEncoded());
////		System.out.println("🔐 Paste this into your application.properties: " + base64Secret);
////String secret = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
////System.out.println("Secret key here: " + secret );
//		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTc1MTA0NTkzMywiZXhwIjoxNzUxMDQ5NTMzfQ.r0Urzv7dsrRH8ZuUREfCrdlW-Z5YuYK-3u6k_QjweZg";
//		String secretBase64 = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b"; // e.g. O85jZAnetT/fxWDRYeNY8OYMfsfW4RLkHBqpFwJjvAY=
//
//		byte[] decodedKey = Base64.getDecoder().decode(secretBase64);
//		Key key = Keys.hmacShaKeyFor(decodedKey);
//
//		Claims claims = Jwts.parserBuilder()
//				.setSigningKey(key)
//				.build()
//				.parseClaimsJws(jwt)
//				.getBody();
//
//		System.out.println("✅ Valid token for subject: " + claims.getSubject());
		SpringApplication.run(SareetaApplication.class, args);

	}

}
