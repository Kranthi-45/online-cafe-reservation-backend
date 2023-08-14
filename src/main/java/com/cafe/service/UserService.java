package com.cafe.service;

import java.util.List;
import java.util.Optional;

import com.cafe.entity.User;

public interface UserService {

	User createUser(User user);

	User getUserById(Long id);

	User updateUser(Long id, User user);

	User deleteUser(Long id);

	List<User> getAllUsers();

	Optional<User> login(String username, String password);
	
}

