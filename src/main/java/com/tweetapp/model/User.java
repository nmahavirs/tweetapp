package com.tweetapp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
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
@Document(collection = "users")
@JsonInclude(Include.NON_EMPTY)
public class User {
	@NotEmpty(message = "First Name must not be empty")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$", message = "First Name is Invalid")
	private String firstName;
	private String lastName;
	@NotEmpty(message = "Gender must not be empty")
	private String gender;
	private String dob;
	@Id
	@NotEmpty(message = "Email must not be empty")
	@Email(message = "Email is Invalid")
	private String email;
	@NotEmpty(message = "Password must not be empty")
	@Length(min = 8, max = 25, message = "Password should be min 8 characters and max 25 characters")
	private String password;
}
