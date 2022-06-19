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
	public List<Tweet> getTweetsByUserId(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Tweet postAtweet(Tweet tweet) {
		return repository.save(tweet);
	}
}
