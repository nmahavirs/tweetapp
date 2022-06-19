package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private String firstName;
	private String lastName;
	private String gender;
	private String dob;
	private String email;
	private String authToken;
}
