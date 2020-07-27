package com.recepti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recepti.entity.User;
import com.recepti.repository.UserRepository;
import com.recepti.service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
    UserRepository userRepository;

    @Override
    public User findOne(Integer userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
    	User user = userRepository.findByUsername(username);
        if(user.getUsername().equals(username) && user.getPassword().equals(password))
            return user;
        else
            return null;
    }

    @Override
    public User findByUsername(String username) {
    	User user = userRepository.findByUsername(username);
        if(user.getUsername().equals(username))
            return user;
        else
            return null;
    }
	
}
