package com.tweetapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {
	@Autowired
	TweetDao dao;

	@Override
	public void postATweet(Tweet tweet) {
		dao.postAtweet(tweet);
	}

	@Override
	public List<Tweet> viewAllTweets() {
		return dao.getAllTweets();
	}

	@Override
	public List<Tweet> viewMyTweets(String username) {
		return dao.getTweetsByUserId(username);
	}

}
