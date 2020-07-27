package com.recepti.dto;

import java.io.Serializable;

import com.recepti.entity.User;

public class UserDTO implements Serializable{
	
    private Long id;
    private String name;
    private String last_name;
    private String username;
    private String password;
    
    public UserDTO() {
    }

	public UserDTO(Long id, String name, String last_name, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
	}

	public UserDTO(User user) {
		this(user.getId(),user.getName(),user.getLast_name(),user.getPassword(),user.getUsername());
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
