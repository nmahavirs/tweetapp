package com.tweetapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.NotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {
	@Autowired
	private TweetDao tweetDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Tweet postNewTweet(Tweet tweet) {
		if (!userDao.userExists(tweet.getUsername())) {
			throw new NotFoundException("Unable to post the tweet, unknown user!");
		}
		return tweetDao.saveTweet(tweet);
	}

	@Override
	public List<Tweet> viewAllTweets() {
		List<Tweet> tweets = tweetDao.getAllTweets();
		if (tweets.isEmpty()) {
			throw new NotFoundException("No tweets found in the application");
		}
		return tweets;
	}

	@Override
	public List<Tweet> viewMyTweets(String username) {
		if (!userDao.userExists(username)) {
			throw new NotFoundException("Unable to retrieve tweets, unknown user!");
		}
		List<Tweet> tweetsByUsername = tweetDao.getTweetsByUsername(username);
		if (tweetsByUsername.isEmpty()) {
			throw new NotFoundException("No tweets found for the username");
		}
		return tweetsByUsername;
	}

	@Override
	public Tweet updateTweet(Tweet tweet) {
		if (!tweetDao.tweetExists(tweet.getId())) {
			throw new NotFoundException("Unable to update the tweet, unknown tweet!");
		}
		return tweetDao.saveTweet(tweet);
	}

	@Override
	public void deleteTweet(String id) {
		if (!tweetDao.tweetExists(id)) {
			throw new NotFoundException("Unable to delete the tweet, unknown tweet!");
		}
		tweetDao.deleteTweet(id);
	}

	@Override
	public Tweet likeTweet(Tweet tweet) {
		if (!tweetDao.tweetExists(tweet.getId())) {
			throw new NotFoundException("Unable to like the tweet, unknown tweet!");
		}
		tweet.setLikes(tweet.getLikes() + 1);
		return tweetDao.saveTweet(tweet);
	}

	@Override
	public Tweet replyTweet(Tweet tweet) {
		if (!tweetDao.tweetExists(tweet.getId())) {
			throw new NotFoundException("Unable to reply the tweet, unknown tweet!");
		}
		return tweetDao.saveTweet(tweet);
	}
}