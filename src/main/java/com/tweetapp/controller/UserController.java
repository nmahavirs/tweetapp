package com.tweetapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.APIResponse;
import com.tweetapp.model.LoginRequest;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
	@Autowired
	UserService service;

	@PostMapping("/register")
	public ResponseEntity<APIResponse> register(@Valid @RequestBody User user) {
		APIResponse response = new APIResponse(service.register(user), null, null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequest request) {
		APIResponse response = new APIResponse(service.login(request.getUsername(), request.getPassword()), null, null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/users/all")
	public ResponseEntity<APIResponse> getAllUsers() {
		APIResponse response = new APIResponse(service.viewAllUsers(), null, null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/user/search/{username}")
	public ResponseEntity<APIResponse> searchByUsername(@PathVariable("username") String username) {
		APIResponse response = new APIResponse(service.searchByUsername(username), null, null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	public void forgotPassword() {
	}
}
