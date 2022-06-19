package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.LoginResponse;
import com.tweetapp.model.User;

public interface UserService {
	public User register(User user);

	public LoginResponse login(String username, String password);

	public User updatePassword(User user);

	public List<User> viewAllUsers();

	public List<User> searchByUsername(String username);
}
