package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;

@Service
public class TweetProducerService {
	private static final String TOPIC = "tweets";

	@Autowired
	private KafkaTemplate<String, Tweet> kafkaTemplate;

	public void sendMessage(Tweet tweet) {
		this.kafkaTemplate.send(TOPIC, tweet);
	}
}