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
		if(dao.userExists(user.getLoginId())) {
			throw new UserAlreadyExistsException("LoginId/Email already exists please Login or Register with different Email/LoginId");
		}
		return dao.addUser(user);
	}

	@Override
	public User login(String username, String password) {
		User user = dao.getUserByUsernameAndPassword(username, password);
		if (user == null) {
			throw new UserNotFoundException("Unable to login! Please enter valid credentials or Register");
		}		
		return user;
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
