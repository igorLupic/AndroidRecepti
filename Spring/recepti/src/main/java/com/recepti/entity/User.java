package com.recepti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User implements Serializable{

	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Long id;

	    @Column(name = "name", unique = false, nullable = false)
	    private String name;

	    @Column(name = "last_name", unique = false, nullable = false)
	    private String last_name;

	    @Column(name = "username", unique = true, nullable = false)
	    private String username;

	    @Column(name = "password", unique = false, nullable = false)
	    private String password;
	    
	    public User() {
	    }

		public User(Long id, String name, String last_name, String username, String password) {
			super();
			this.id = id;
			this.name = name;
			this.last_name = last_name;
			this.username = username;
			this.password = password;
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

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", last_name=" + last_name + ", username=" + username
					+ ", password=" + password + "]";
		}

	
	    
	    
}
