package com.tweetapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;
import com.tweetapp.util.InputValidationUtil;

@Component
public class UserComponent {
	@Autowired
	UserService service;

	private User user;

	public User registerUser() {
		user = new User();
		user.setFirstName(InputValidationUtil.getValidFirstName());
		user.setLastName(InputValidationUtil.getValidLastName());
		user.setGender(InputValidationUtil.getValidGender());
		user.setDob(InputValidationUtil.getValidDob());
		user.setEmail(InputValidationUtil.getValidEmail());
		user.setPassword(InputValidationUtil.getValidPassword());

		try {
			user = service.register(user);
			return user;
		} catch (UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void displayAllUsers() {
		System.out.println("\n--------");
		System.out.println("All Users:");
		System.out.println("----------");

		service.viewAllUsers().forEach(System.out::println);
	}

	public User loginUser() {
		try {
			user = service.login(InputValidationUtil.getValidEmail(), InputValidationUtil.getValidPassword());

			/*if (user == null) {
				System.out.println("\nUnable to login, please enter the valid credentials!!!");
				return null;
			}*/

			return user;
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean logoutUser() {
		if (user != null && user.isLoggedIn()) {
			return service.logout(user.getEmail());
		}
		return false;
	}

	public User resetPassword() {
		System.out.println("Please enter your old");

		if (user != null && user.getPassword().equals(InputValidationUtil.getValidPassword())) {
			System.out.println("Enter new password:");
			user.setPassword(InputValidationUtil.getValidPassword());
			user = service.updatePassword(user);
			System.out.println("Password Reset Successful");
		} else {
			System.out.println("Wrong password! Please try again.");
		}

		return user;
	}

	public void forgotPassword() {
		System.out.println("Please enter");
		user = service.getUser(InputValidationUtil.getValidEmail());
		if (user != null && user.getFirstName().equalsIgnoreCase(InputValidationUtil.getValidFirstName())) {
			user.setPassword(InputValidationUtil.getValidPassword());
			user = service.updatePassword(user);
			System.out.println("Changed password successfully");
		} else {
			System.out.println("Incorrect details! Please try again.");
		}
	}
}
