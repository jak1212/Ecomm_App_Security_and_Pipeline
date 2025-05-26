package com.example.demo.model.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.persistence.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}
