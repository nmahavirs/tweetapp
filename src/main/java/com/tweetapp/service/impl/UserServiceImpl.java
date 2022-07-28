package com.tweetapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.AlreadyExistsException;
import com.tweetapp.exception.NotFoundException;
import com.tweetapp.model.User;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.service.JwtTokenService;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Autowired
	JwtTokenService jwtTokenService;

	@Override
	public UserResponse register(User user) {
		if (dao.userExists(user.getEmail())) {
			throw new AlreadyExistsException(
					"LoginId/Email already exists please Login or Register with different Email/LoginId");
		}
		user = dao.saveUser(user);
		return new UserResponse(user.getFirstName(), user.getLastName(), user.getGender(), user.getDob(),
				user.getEmail(), null);
	}

	@Override
	public UserResponse login(String username, String password) {
		User user = dao.getUser(username);
		if (user != null && password.equals(user.getPassword())) {
			String accessToken = jwtTokenService.generateAcccessToken(username);
			return new UserResponse(user.getFirstName(), user.getLastName(), user.getGender(), user.getDob(),
					user.getEmail(), accessToken);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"Unable to login! Please enter valid credentials or Register");
	}

	@Override
	public User updatePassword(User user) {
		User updatedUser = dao.getUser(user.getEmail());
		if (null == updatedUser) {
			throw new NotFoundException("Unable to change password, email doesn't exist!");
		}
		updatedUser.setPassword(user.getPassword());
		user = dao.saveUser(updatedUser);
		user.setPassword("");
		return user;
	}

	@Override
	public List<User> viewAllUsers() {
		List<User> users = dao.getAllUsers().stream().peek(user -> user.setPassword("")).collect(Collectors.toList());
		if (users.isEmpty()) {
			throw new NotFoundException("No users found in the application");
		}
		return users;
	}

	@Override
	public List<User> searchByUsername(String username) {
		List<User> users = dao.getUsers(username).stream().peek(user -> user.setPassword(""))
				.collect(Collectors.toList());
		if (users.isEmpty()) {
			throw new NotFoundException("No users found for the username");
		}
		return users;
	}

	@Override
	public UserResponse refreshLogin(String username) {
		if (username != null && dao.userExists(username)) {
			String accessToken = jwtTokenService.generateAcccessToken(username);
			UserResponse userResponse = new UserResponse();
			userResponse.setAccessToken(accessToken);
			return userResponse;
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unable to refresh login, Please login again.");
	}
}