package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.User;

public interface UserService {
	public User register(User user);

	public User login(String username, String password);

	public User updatePassword(User user);

	public List<User> viewAllUsers();

	public User getUser(String username);
}
