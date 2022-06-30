package com.tweetapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.User;
import com.tweetapp.model.response.LoginResponse;
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
		user = dao.addUser(user);
		user.setPassword("");
		return user;
	}

	@Override
	public LoginResponse login(String username, String password) {
		User user = dao.getUser(username);
		if (user != null && password.equals(user.getPassword())) {
//			String authToken = jwtTokenUtil.generateToken(new LoginRequest(username, password));
			return new LoginResponse(user.getFirstName(), user.getLastName(), user.getGender(), user.getDob(),
					user.getEmail(), "");
		}
		throw new UserNotFoundException("Unable to login! Please enter valid credentials or Register");
	}

	@Override
	public User updatePassword(User user) {
		User updatedUser = dao.getUser(user.getEmail());
		if (null == updatedUser) {
			throw new UserNotFoundException("Unable to change password, email doesn't exist!");
		}
		updatedUser.setPassword(user.getPassword());
		return dao.updateUser(updatedUser);
	}

	@Override
	public List<User> viewAllUsers() {
		List<User> users = dao.getAllUsers().stream().peek(user -> user.setPassword("")).collect(Collectors.toList());
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