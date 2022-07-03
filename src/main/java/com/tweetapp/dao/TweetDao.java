package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.Tweet;

public interface TweetDao {
	public List<Tweet> getAllTweets();

	public List<Tweet> getTweetsByUsername(String userId);

	public Tweet saveTweet(Tweet tweet);
	
	public boolean tweetExists(String id);

	public void deleteTweet(String id);
	
	public Tweet getTweet(String id);
}