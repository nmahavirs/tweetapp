package com.tweetapp.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.User;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.request.PasswordRequest;
import com.tweetapp.model.response.APIResponse;
import com.tweetapp.service.UserService;

//@CrossOrigin(origins = "http://localhost:9731")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<APIResponse> registerAsNewUser(@Valid @RequestBody User user) {
		APIResponse response = new APIResponse(userService.register(user), "User registered successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequest request) {
		APIResponse response = new APIResponse(userService.login(request.getUsername(), request.getPassword()),
				"User logged in successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/users/all")
	public ResponseEntity<APIResponse> getAllUsers() {
		APIResponse response = new APIResponse(userService.viewAllUsers(), "Users retrieved successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/user/search/{username}")
	public ResponseEntity<APIResponse> searchByUsername(@PathVariable("username") String username) {
		APIResponse response = new APIResponse(userService.searchByUsername(username), "Users by username retrieved successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@PutMapping("/{username}/forgot")
	public ResponseEntity<APIResponse> forgotPassword(@Valid @RequestBody PasswordRequest request,
			@Email(message = "Provide a valid username") @PathVariable("username") String username) {
		User user = new User();
		user.setEmail(username);
		user.setPassword(request.getPassword());
		APIResponse response = new APIResponse(userService.updatePassword(user), "Updated password successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
}