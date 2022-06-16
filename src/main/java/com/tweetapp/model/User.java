package com.tweetapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	@Id
	private String email;
	private String password;
	private boolean isLoggedIn;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	List<Tweet> tweets;

	@Override
	public String toString() {
		return "Username: @" + email.split("@")[0] + "\n" + "Name: " + firstName + " " + lastName + "\n";
	}
}
