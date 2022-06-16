package com.tweetapp.util;

import java.io.Console;
import java.util.Date;

public class InputValidationUtil2 {
	private static final Console CONSOLE = System.console();

	public static String getValidFirstName() {
		String firstName = CONSOLE.readLine("First Name (required): ");

		while (firstName.isEmpty() || !firstName.matches("^[a-zA-Z][a-zA-Z ]*$")) {
			firstName = CONSOLE.readLine("Please enter a valid First Name: ");
		}

		return firstName;
	}

	public static String getValidLastName() {
		String lastName = CONSOLE.readLine("Last Name (optional): ");

		while (!lastName.isEmpty() && !lastName.matches("^[a-zA-Z][a-zA-Z ]*$")) {
			lastName = CONSOLE.readLine("Please enter a valid Last Name (optional): ");
		}

		return lastName;
	}

	public static String getValidGender() {
		String gender = "";

		option: while (true) {
			String choice = CONSOLE.readLine("Gender (required):\n1. Male\n2. Female\n3. Other\n");

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
		String dob = CONSOLE.readLine("DOB (optional, dd-MM-yyyy): ");

		while (!dob.isEmpty() && !dob.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
			dob = CONSOLE.readLine("Please enter a valid DOB (optional, dd-MM-yyyy):");
		}

		if (dob.isEmpty()) {
			return null;
		}

		return DateUtil.convertToDate(dob);
	}

	public static String getValidEmail() {
		String email = CONSOLE.readLine("Email (required, login-id/username): ");

		while (email.isEmpty() || !email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			System.out.println("Please enter a valid Email (required, login-id/username): ");
			email = CONSOLE.readLine();
		}

		return email;
	}

	public static String getValidPassword() {
		String password = CONSOLE.readPassword("Password (required): ").toString();

		while (password.isEmpty() || password.trim().length() < 8) {
			password = CONSOLE.readPassword("Please enter a valid Password min of 8 non whitespace characters: ")
					.toString();
		}

		return password.trim();
	}
}
