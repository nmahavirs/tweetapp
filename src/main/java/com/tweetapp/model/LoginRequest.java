package com.tweetapp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@NotBlank(message = "Username must not be empty")
	@Email(message = "Invalid username, it must be an email")
	private String username;
	@NotEmpty(message = "Password must not be empty")
	@Length(min = 8, max = 25, message = "Password should be min 8 characters and max 25 characters")
	private String password;
}
