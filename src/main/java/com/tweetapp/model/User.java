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
@Document(collection = "users")
public class User {
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	@Id
	private String loginId;
	private String email;
	private String password;
}
