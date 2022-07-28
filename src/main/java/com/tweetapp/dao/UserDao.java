package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.User;

public interface UserDao {
	public List<User> getAllUsers();

	public List<User> getUsers(String username);

	public User getUser(String email);

	public User saveUser(User user);
	
	public boolean userExists(String username);
}
