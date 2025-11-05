package com.healthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entities.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
//derived query - findBy
	Optional<User>  findByEmailAndPassword(String em, String pass);
	
	
}
