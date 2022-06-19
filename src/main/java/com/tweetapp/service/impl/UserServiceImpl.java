package com.tweetapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.LoginRequest;
import com.tweetapp.model.LoginResponse;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;
import com.tweetapp.util.JwtTokenUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Override
	public User register(User user) {
		if (dao.userExists(user.getEmail())) {
			throw new UserAlreadyExistsException(
					"LoginId/Email already exists please Login or Register with different Email/LoginId");
		}
		return dao.addUser(user);
	}

	@Override
	public LoginResponse login(String username, String password) {
		User user = dao.getUser(username);
		if (user != null && password.equals(user.getPassword())) {
			String authToken = jwtTokenUtil.generateToken(new LoginRequest(username, password));
			return new LoginResponse(user.getFirstName(), user.getLastName(), user.getGender(), user.getDob(),
					user.getEmail(), authToken);
		}
		throw new UserNotFoundException("Unable to login! Please enter valid credentials or Register");
	}

	@Override
	public User updatePassword(User user) {
		return dao.updateUser(user);
	}

	@Override
	public List<User> viewAllUsers() {
		List<User> users = dao.getAllUsers();
		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found for the Application");
		}
		return users;
	}

	@Override
	public List<User> searchByUsername(String username) {
		List<User> users = dao.getUsers(username);
		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found for the username");
		}
		return users;
	}
}