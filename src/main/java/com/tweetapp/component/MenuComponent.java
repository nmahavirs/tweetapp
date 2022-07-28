package com.tweetapp.component;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.model.User;

@Component
public class MenuComponent {
	private final Scanner scanner = new Scanner(System.in);

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private TweetComponent tweetComponent;

	private User user;

	public void showMenu() {
		System.out.println("\n=================");
		System.out.println("Console Tweet App");
		System.out.println("=================");

		while (true) {
			if (user != null && user.isLoggedIn()) {
				showLoggedInUserMenu();
			} else {
				showMainMenu();
			}
		}
	}

	private void showLoggedInUserMenu() {
		System.out.println("\nWelcome " + user.getFirstName()
				+ "\nEnter a number of your choice from the below:\n1. Post a tweet\n2. View my tweets\n3. View all tweets\n4. View All Users\n"
				+ "5. Reset Password\n6. Logout");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			tweetComponent.postATweet(user);
			break;
		case "2":
			tweetComponent.displayMyTweets(user.getEmail());
			break;
		case "3":
			tweetComponent.displayAllTweets();
			break;
		case "4":
			userComponent.displayAllUsers();
			break;
		case "5":
			user = userComponent.resetPassword();
			break;
		case "6":
			user.setLoggedIn(userComponent.logoutUser());
			break;
		default:
			System.out.println("\nYou've entered an invalid number!");
			break;
		}
	}

	private void showMainMenu() {
		System.out.println(
				"\nEnter a number of your choice from the below:\n1. Register\n2. Login\n3. Forgot Password\n4. Exit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			user = userComponent.registerUser();
			break;
		case "2":
			user = userComponent.loginUser();
			break;
		case "3":
			userComponent.forgotPassword();
			break;
		case "4":
			System.out.println("Buh Bye, Thank you! Hope we see you again.");
			System.exit(0);
			break;
		default:
			System.out.println("\nYou've entered an invalid number!");
			break;
		}
	}

	/*
	 * private void clearScreen() { try { final String os =
	 * System.getProperty("os.name"); if (os.contains("Windows")) {
	 * Runtime.getRuntime().exec("cls"); } else {
	 * Runtime.getRuntime().exec("clear"); } } catch (final Exception e) {
	 * e.printStackTrace(); } }
	 */
}