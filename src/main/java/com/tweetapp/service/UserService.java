package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.User;
import com.tweetapp.model.response.UserResponse;

public interface UserService {
	public UserResponse register(User user);

	public UserResponse login(String username, String password);

	public User updatePassword(User user);

	public List<User> viewAllUsers();

	public List<User> searchByUsername(String username);
	
	public UserResponse refreshLogin(String username);
}
