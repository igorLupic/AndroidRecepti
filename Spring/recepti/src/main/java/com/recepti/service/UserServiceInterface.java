package com.recepti.service;

import java.util.List;

import com.recepti.entity.User;

public interface UserServiceInterface{

	User findOne(Integer userId);
	List<User> findAll();
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
}
