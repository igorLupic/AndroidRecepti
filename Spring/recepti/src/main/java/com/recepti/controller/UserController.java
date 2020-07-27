package com.recepti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recepti.dto.UserDTO;
import com.recepti.entity.User;
import com.recepti.service.impl.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserController {

	@Autowired
    UserService userService;
	
	@GetMapping(value="/all")
    public ResponseEntity<List<UserDTO>> geUsers(){
        List<User> users = userService.findAll();
        List<UserDTO> userDTO = new ArrayList<UserDTO>();
        for (User a : users) {
        	userDTO.add(new UserDTO(a));
        }
        return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
    }

	@GetMapping(value="/all/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id){
		User user = userService.findOne(id);
        if(user != null) {
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }
	
	@GetMapping(value="{username}/{password}")
    public ResponseEntity<UserDTO> login(@PathVariable("username") String username, @PathVariable("password") String password){
		User user = userService.findByUsernameAndPassword(username, password);
        if(user != null) {
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }

	@GetMapping(value="{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
        if(user != null) {
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }

	
	
}
