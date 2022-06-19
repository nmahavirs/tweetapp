package com.tweetapp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tweets")
public class Tweet {
	@Id
	private long id;
	private String text;
	private String tag;
	private String username;
	private Date timestamp;
}
