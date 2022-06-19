package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	@Query("{loginId : ?0, password : ?1}")
	User findByUsernameAndPassword(String username, String password);

	List<User> findByUsernameContaining(String username);
}