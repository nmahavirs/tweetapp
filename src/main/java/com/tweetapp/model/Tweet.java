package com.tweetapp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tweets")
@JsonInclude(Include.NON_NULL)
public class Tweet {
	@Id
	private String id;
	@NotBlank(message = "Text must not be empty")
	private String text;
	private String tag;
	private String username;
	private LocalDateTime timestamp;
	private int likes;
	private List<Reply> replies;
}