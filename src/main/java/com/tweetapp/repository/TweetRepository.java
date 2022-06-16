package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tweetapp.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
	@Query(value = "select t from Tweet t where user_id = ?1")
	public List<Tweet> findbyUser(String userId);
}
