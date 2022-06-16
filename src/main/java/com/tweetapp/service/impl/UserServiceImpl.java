package com.tweetapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Override
	public User register(User user) {
		if(getUser(user.getEmail()) != null ) {
			throw new UserAlreadyExistsException("Username/Email already exists please try to Login or Register with different email/username");
		}
		user.setLoggedIn(true);
		return dao.addUser(user);
	}

	@Override
	public User login(String username, String password) {
		User user = dao.getUserByUsernameAndPassword(username, password);

		if (user != null) {
			user.setLoggedIn(true);
			user = dao.updateUser(user);
			return user;
		}

		throw new UserNotFoundException("Unable to login! Please enter valid credentials or Register");
	}

	@Override
	public boolean logout(String username) {
		User user = getUser(username);

		if (user != null && user.isLoggedIn()) {
			user.setLoggedIn(false);
			dao.updateUser(user);
		}

		return false;
	}

	@Override
	public User updatePassword(User user) {
		return dao.updateUser(user);
	}

	@Override
	public List<User> viewAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public User getUser(String username) {
		return dao.getUser(username);
	}
}
