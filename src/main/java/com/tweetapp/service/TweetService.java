package com.tweetapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.model.Reply;
import com.tweetapp.model.Tweet;

@Service
public interface TweetService {
	public Tweet postNewTweet(Tweet tweet);

	public List<Tweet> viewAllTweets();

	public List<Tweet> viewMyTweets(String username);

	public Tweet updateTweet(Tweet tweet);

	public void deleteTweet(String id);

	public Tweet likeTweet(String id);

	public Tweet replyTweet(Reply reply, String id);
}