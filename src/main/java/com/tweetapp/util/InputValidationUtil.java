package com.tweetapp.util;

import java.util.Date;
import java.util.Scanner;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public class InputValidationUtil {
	private static final Scanner SCAN = new Scanner(System.in);

	public static String getValidFirstName() {
		System.out.println("First Name (required):");
		String firstName = SCAN.nextLine();
		while (firstName.isEmpty() || !firstName.matches("^[a-zA-Z][a-zA-Z ]*$")) {
			System.out.println("Please enter a valid First Name:");
			firstName = SCAN.nextLine();
		}
		return firstName;
	}

	public static String getValidLastName() {
		System.out.println("Last Name (optional):");
		String lastName = SCAN.nextLine();
		while (!lastName.isEmpty() && !lastName.matches("^[a-zA-Z][a-zA-Z ]*$")) {
			System.out.println("Please enter a valid Last Name (optional):");
			lastName = SCAN.nextLine();
		}
		return lastName;
	}

	public static String getValidGender() {
		String gender = "";
		option: while (true) {
			System.out.println("Gender (required):");
			System.out.println("1. Male\n2. Female\n3. Other");
			final String choice = SCAN.nextLine();
			switch (choice) {
			case "1":
				gender = "Male";
				break option;
			case "2":
				gender = "Female";
				break option;
			case "3":
				gender = "Other";
				break option;
			default:
				System.out.println("Please select a valid option");
				continue option;
			}
		}

		return gender;
	}

	public static Date getValidDob() {
		System.out.println("DOB (optional, dd-MM-yyyy):");
		String dob = SCAN.nextLine();
		while (!dob.isEmpty() && !dob.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-\\d{4}$")) {
			System.out.println("Please enter a valid DOB (optional, dd-MM-yyyy):");
			dob = SCAN.nextLine();
		}
		if (dob.isEmpty()) {
			return null;
		}
		return DateUtil.convertToDate(dob);
	}

	public static String getValidEmail() {
		System.out.println("Email (required, login-id/username):");
		String email = SCAN.nextLine();
		while (email.isEmpty() || !email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			System.out.println("Please enter a valid Email (required, login-id/username):");
			email = SCAN.nextLine();
		}
		return email;
	}

	public static String getValidPassword() {
		System.out.println("Password (required):");
		String password = SCAN.nextLine();
		while (password.isEmpty() || password.length() < 8) {
			System.out.println("Please enter a valid Password min of 8 characters:");
			password = SCAN.nextLine();
		}
		return password;
	}

	public static Tweet getValidTweet(final User user) {
		final Tweet tweet = new Tweet();
		tweet.setUser(user);
		
		String input;
		
		System.out.println("Text:");
		input = SCAN.nextLine();
		while (input.isEmpty()) {
			System.out.println("Please enter some Text:");
			input = SCAN.nextLine();
		}
		tweet.setText(input);

		System.out.println("Tag:");
		input = SCAN.nextLine();
		tweet.setTag(input);

		return tweet;
	}
}
