package com.cafe.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.User;
import com.cafe.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:4200","*"}, allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService ms;
	
	@GetMapping("/home")
	public String home()
	{
		return "User Service";
	}
	
    @GetMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = ms.login(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password incorrect.");
        }
    }
    
	@GetMapping
	public List<User> getAllUsers()
	{
		return ms.getAllUsers();
	}
		
	@PostMapping
	public User addUser(@RequestBody User user)
	{
		System.out.println("What i have received: "+user);
		return ms.createUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user)
	{
		System.out.println("entering modify method "+ user);
		return ms.updateUser(id, user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id)
	{
		System.out.println("entering getUserbyId method "+ id);
		return ms.getUserById(id);
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable("id") Long id)
	{
		System.out.println("entering delete method "+ id);
		return ms.deleteUser(id);
	}
	

}

