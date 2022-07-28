package com.tweetapp.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.tweetapp.service.JwtTokenService;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = { "http://localhost:3000",
		"http://elasticbeanstalk-us-east-1-911861301997.s3-website-us-east-1.amazonaws.com" })
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	JwtTokenService jwtTokenService;

	@PostMapping("/register")
	public ResponseEntity<APIResponse> registerAsNewUser(@Valid @RequestBody User user) {
		APIResponse response = new APIResponse(userService.register(user), "User registered successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequest request) {
		String username = request.getUsername();
		APIResponse response = new APIResponse(userService.login(username, request.getPassword()),
				"User logged in successfully.", null);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, getRefreshTokenCookie(username).toString())
				.body(response);
	}

	@GetMapping("/users/all")
	public ResponseEntity<APIResponse> getAllUsers() {
		APIResponse response = new APIResponse(userService.viewAllUsers(), "Users retrieved successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/user/search/{username}")
	public ResponseEntity<APIResponse> searchByUsername(@PathVariable("username") String username) {
		APIResponse response = new APIResponse(userService.searchByUsername(username),
				"Users by username retrieved successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@PutMapping("/{username}/forgot")
	public ResponseEntity<APIResponse> forgotPassword(@Valid @RequestBody PasswordRequest request,
			@Email(message = "Provide a valid username") @PathVariable("username") String username) {
		User user = new User();
		user.setEmail(username);
		user.setPassword(request.getPassword());
		APIResponse response = new APIResponse(userService.updatePassword(user), "Updated password successfully.",
				null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/refresh")
	public ResponseEntity<APIResponse> refreshAccessToken(
			@CookieValue(value = "jwt", defaultValue = "") String jwtCookie) {
		String username = jwtTokenService.validateTokenAndGetUsername(jwtCookie);// .getValue());
		APIResponse response = new APIResponse(userService.refreshLogin(username),
				"Access token refreshed successfully.", null);
		return ResponseEntity.ok(response);
	}

	private ResponseCookie getRefreshTokenCookie(String username) {
		return ResponseCookie.from("jwt", jwtTokenService.generateRefreshToken(username)).httpOnly(true)
				.maxAge(24 * 60 * 60).sameSite("none").build();
	}
}