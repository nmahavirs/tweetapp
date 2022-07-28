package com.tweetapp.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Tweet;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TweetConsumerService {
	@KafkaListener(topics = "tweets", groupId = "group_id")
	public void consume(Tweet tweet) throws IOException {
		log.info("consumed tweets " + tweet.toString());
	}
}