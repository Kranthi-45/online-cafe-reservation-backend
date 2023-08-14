package com.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data

public class User {
	 public User() {  
	    }
   public User(String string) {
		// TODO Auto-generated constructor stub
	}
  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
    private String firstName;
    private String lastName;
	private String email;
	private String phone;
	private String gender;
	private String password;
	private String role;
}
