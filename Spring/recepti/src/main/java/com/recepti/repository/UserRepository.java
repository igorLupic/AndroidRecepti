package com.recepti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recepti.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
