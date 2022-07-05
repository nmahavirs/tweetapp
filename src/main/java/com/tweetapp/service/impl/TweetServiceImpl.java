package com.tweetapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.NotFoundException;
import com.tweetapp.model.Reply;
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
		Tweet updatedTweet = tweetDao.getTweet(tweet.getId());
		if (null == updatedTweet) {
			throw new NotFoundException("Unable to update the tweet, unknown tweet!");
		}
		updatedTweet.setText(tweet.getText());
		return tweetDao.saveTweet(updatedTweet);
	}

	@Override
	public void deleteTweet(String id) {
		if (!tweetDao.tweetExists(id)) {
			throw new NotFoundException("Unable to delete the tweet, unknown tweet!");
		}
		tweetDao.deleteTweet(id);
	}

	@Override
	public Tweet likeTweet(String id) {
		Tweet updatedTweet = tweetDao.getTweet(id);
		if (null == updatedTweet) {
			throw new NotFoundException("Unable to like the tweet, unknown tweet!");
		}
		updatedTweet.setLikes(updatedTweet.getLikes() + 1);
		return tweetDao.saveTweet(updatedTweet);
	}

	@Override
	public Tweet replyTweet(Reply reply, String id) {
		Tweet updatedTweet = tweetDao.getTweet(id);
		if (null == updatedTweet) {
			throw new NotFoundException("Unable to reply the tweet, unknown tweet!");
		}
		List<Reply> replies = updatedTweet.getReplies();
		if (replies != null) {
			replies.add(reply);
			updatedTweet.setReplies(replies);
		}
		return tweetDao.saveTweet(updatedTweet);
	}
}