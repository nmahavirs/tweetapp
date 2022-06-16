package com.tweetapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.util.InputValidationUtil;

@Component
public class TweetComponent {
	@Autowired
	TweetService service;
	
	public void displayAllTweets() {
		System.out.println("\n---------");
		System.out.println("All Tweets:");
		System.out.println("-----------");
		
		service.viewAllTweets().forEach(System.out::println);
	}
	
	public void displayMyTweets(String username) {
		System.out.println("\n--------");
		System.out.println("My Tweets:");
		System.out.println("----------");
		
		service.viewMyTweets(username).forEach(System.out::println);
	}
	
	public void postATweet(User user) {
		service.postATweet(InputValidationUtil.getValidTweet(user));
	}
}
