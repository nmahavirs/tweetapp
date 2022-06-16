package com.tweetapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.dao.UserDao;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {
	@Autowired
	UserRepository repository;

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User getUser(String username) {
		return repository.findById(username).orElse(null);
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		return repository.findByEmailAndPassword(username, password).orElse(null);
	}

	@Override
	public User addUser(User user) {
		return repository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return repository.save(user);
	}
}
