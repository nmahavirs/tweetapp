package com.tweetapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByEmailAndPassword(String email, String password);
}
