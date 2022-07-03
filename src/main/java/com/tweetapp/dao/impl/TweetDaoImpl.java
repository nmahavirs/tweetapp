package com.tweetapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepository;

@Component
public class TweetDaoImpl implements TweetDao {
	@Autowired
	TweetRepository repository;

	@Override
	public List<Tweet> getAllTweets() {
		return repository.findAll();
	}

	@Override
	public List<Tweet> getTweetsByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Tweet saveTweet(Tweet tweet) {
		return repository.save(tweet);
	}

	@Override
	public boolean tweetExists(String id) {
		return repository.existsById(id);
	}

	@Override
	public void deleteTweet(String id) {
		repository.deleteById(id);
	}

	@Override
	public Tweet getTweet(String id) {
		return repository.findById(id).orElse(null);
	}
}