package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.User;
import com.tweetapp.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
	@Autowired
	UserService service;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		return new ResponseEntity<User>(service.register(user), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	public User login() {
		return null;
	}

	@GetMapping("/users/all")
	public void allUsers() {
		service.viewAllUsers();
	}

	public User resetPassword() {
		return null;
	}

	public void forgotPassword() {
	}
}
