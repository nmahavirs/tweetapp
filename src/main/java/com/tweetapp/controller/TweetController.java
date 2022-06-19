package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;

@Controller
@RequestMapping("/api/v1.0/tweets")
public class TweetController {
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
//		service.postATweet(user);
	}
}
