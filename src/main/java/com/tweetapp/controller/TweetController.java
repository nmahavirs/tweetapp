package com.tweetapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.Reply;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.response.APIResponse;
import com.tweetapp.service.TweetProducerService;
import com.tweetapp.service.TweetService;

@RestController
@Validated
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = { "http://localhost:3000",
		"http://elasticbeanstalk-us-east-1-911861301997.s3-website-us-east-1.amazonaws.com" })
public class TweetController {
	@Autowired
	TweetService service;

	@Autowired
	TweetProducerService producerService;

	@GetMapping("/all")
	public ResponseEntity<APIResponse> getAllTweets() {
		APIResponse response = new APIResponse(service.viewAllTweets(), "All tweets retrieved successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/{username}")
	public ResponseEntity<APIResponse> getAllTweetsOfUser(
			@Email(message = "Please provide a valid username") @PathVariable("username") String username) {
		APIResponse response = new APIResponse(service.viewMyTweets(username), "User tweets retrieved successfully.",
				null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/{username}/add")
	public ResponseEntity<APIResponse> postNewTweet(
			@Email(message = "Please provide a valid username") @PathVariable("username") String username,
			@Valid @RequestBody Tweet tweet) {
		tweet.setId(generateTweetId());
		tweet.setUsername(username);
		tweet.setTimestamp(LocalDateTime.now());
		tweet.setReplies(new ArrayList<>());
		producerService.sendMessage(tweet);
		APIResponse response = new APIResponse(service.postNewTweet(tweet), "Tweet posted successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.CREATED);
	}

	private String generateTweetId() {
		return UUID.randomUUID().toString();
	}

	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<APIResponse> updateTweet(
			@Email(message = "Please provide a valid username") @PathVariable("username") String username,
			@PathVariable("id") String id, @Valid @RequestBody Tweet tweet) {
		tweet.setId(id);
		tweet.setUsername(username);
		tweet.setTimestamp(LocalDateTime.now());
		APIResponse response = new APIResponse(service.updateTweet(tweet), "Tweet updated successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<APIResponse> deleteTweet(
			@Email(message = "Please provide a valid username") @PathVariable("username") String username,
			@PathVariable("id") String id) {
		APIResponse response = new APIResponse(service.deleteTweet(id), "Tweet deleted successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<APIResponse> likeTweet(
			@Email(message = "Please provide a valid username") @PathVariable("username") String username,
			@PathVariable("id") String id) {
		APIResponse response = new APIResponse(service.likeTweet(id), "Updated like for tweet successfully.", null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/{username}/reply/{id}")
	public ResponseEntity<APIResponse> replyTweet(
			@Email(message = "Please provide a valid username") @PathVariable("username") String username,
			@PathVariable("id") String id, @Valid @RequestBody Reply reply) {
		reply.setUsername(username);
		reply.setTimestamp(LocalDateTime.now());
		APIResponse response = new APIResponse(service.replyTweet(reply, id), "Updated reply to tweet successfully.",
				null);
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
}