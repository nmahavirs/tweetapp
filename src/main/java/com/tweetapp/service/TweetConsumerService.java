/*
 * package com.tweetapp.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.kafka.annotation.KafkaListener; import
 * org.springframework.stereotype.Service;
 * 
 * import com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.ObjectMapper; import com.tweetapp.model.Tweet;
 * 
 * import lombok.extern.slf4j.Slf4j;
 * 
 * @Service
 * 
 * @Slf4j public class TweetConsumerService {
 * 
 * @Autowired private TweetService service;
 * 
 * @Autowired private ObjectMapper mapper;
 * 
 * @KafkaListener(topics = "tweet-topic") public void consumeTweet(Tweet tweet)
 * { try { log.debug("Received Tweet: " + mapper.writeValueAsString(tweet)); }
 * catch (JsonProcessingException e) { log.debug("Received Tweet: " + tweet); }
 * service.postNewTweet(tweet); } }
 */