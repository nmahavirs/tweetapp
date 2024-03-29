package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByEmailLike(String username);
}