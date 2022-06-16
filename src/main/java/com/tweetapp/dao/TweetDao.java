package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.Tweet;

public interface TweetDao {
	public List<Tweet> getAllTweets();

	public List<Tweet> getTweetsByUserId(String userId);
	
	public Tweet postAtweet(Tweet tweet);
}
