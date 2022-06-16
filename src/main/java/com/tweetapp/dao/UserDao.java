package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.User;

public interface UserDao {
	public List<User> getAllUsers();

	public User getUser(String username);

	public User getUserByUsernameAndPassword(String username, String password);

	public User addUser(User user);

	public User updateUser(User user);
}
