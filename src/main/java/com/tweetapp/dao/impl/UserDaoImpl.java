package com.tweetapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tweetapp.dao.UserDao;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Component
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	UserRepository repository;

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public List<User> getUsers(String username) {
		return repository.findByEmailLike(username);
	}

	@Override
	public User getUser(String email) {
		return repository.findById(email).orElse(null);
	}

	@Override
	public User addUser(User user) {
		return repository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return repository.save(user);
	}

	@Override
	public boolean userExists(String username) {
		return repository.existsById(username);
	}
}
