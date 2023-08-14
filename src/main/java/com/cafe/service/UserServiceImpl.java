package com.cafe.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cafe.entity.Delicacy;
import com.cafe.entity.User;
import com.cafe.exception.ItemNotFoundException;
import com.cafe.exception.NoItemsFoundException;
import com.cafe.exception.GlobalExceptions.UserNotFoundException;
import com.cafe.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
	 	@Override
	    public User createUser(User user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public Optional<User> login(String username, String password) {
	        Optional<User> user = userRepository.findByUserName(username);
	        if (user.isPresent()) {
	            // Verify the password here
	            if (user.get().getPassword().equals(password)) {
	                return user;
	            } else {
	                return Optional.empty(); // Password is incorrect
	            }
	        } else {
	            return Optional.empty(); 	  // User not found
	        }
	    }
	    
	    @Override
	    public User getUserById(Long id) {
	        return userRepository.findById(id)
	        		.orElseThrow(() -> new ItemNotFoundException("User not found with ID: " + id));
	    }

	    @Override
	    public User updateUser(Long id, User user) {
	    	User existingUser = userRepository.findById(id)
	                .orElseThrow(() -> new ItemNotFoundException("User not found with ID: " + id));

	        if (user.getUserName() != null) {
	            existingUser.setUserName(user.getUserName());
	        }
	        if (user.getFirstName() != null) {
	            existingUser.setFirstName(user.getFirstName());
	        }
	        if (user.getLastName() != null) {
	            existingUser.setLastName(user.getLastName());
	        }
	        if (user.getEmail() != null) {
	            existingUser.setEmail(user.getEmail());
	        }
	        if (user.getPhone() != null) {
	            existingUser.setPhone(user.getPhone());
	        }
	        if (user.getGender() != null) {
	            existingUser.setGender(user.getGender());
	        }
	        if (user.getPassword() != null) {
	            existingUser.setPassword(user.getPassword());
	        }
	        if (user.getRole() != null) {
	            existingUser.setRole(user.getRole());
	        }
	        return userRepository.save(existingUser);
	    }

	    @Override
	    public User deleteUser(Long id) {
	    	User user =  userRepository.findById(id)
     		.orElseThrow(() -> new ItemNotFoundException("User not found with ID: " + id));
	        userRepository.deleteById(user.getUserId());
			return user;
	    }


		@Override
		public List<User> getAllUsers() {
	        List<User> users = userRepository.findAll();
	        if (users.isEmpty()) {
	            throw new NoItemsFoundException("No Users found.");
	        }
	        return users;
		}
		
		

}
