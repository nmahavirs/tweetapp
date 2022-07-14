/*
 * package com.tweetapp.service;
 * 
 * import java.util.concurrent.ExecutionException;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.kafka.core.KafkaTemplate; import
 * org.springframework.stereotype.Service;
 * 
 * import com.tweetapp.model.Tweet;
 * 
 * @Service public class TweetProducerService {
 * 
 * @Autowired private KafkaTemplate<String, Tweet> kafkaTemplate;
 * 
 * public boolean produceTweet(Tweet tweet) { try { return
 * kafkaTemplate.send("tweet-topic", tweet.getId(),
 * tweet).get().getProducerRecord().value().equals(tweet); } catch
 * (InterruptedException | ExecutionException e) { e.printStackTrace(); } return
 * false; } }
 */