package com.tweetapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;

@Service
public interface TweetService {
	public void postATweet(Tweet tweet);
	
	public List<Tweet> viewAllTweets();
	
	public List<Tweet> viewMyTweets(String username);
}
