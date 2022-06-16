package com.tweetapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tweetapp.component.MenuComponent;

@SpringBootApplication
public class ConsoleTweetappApplication implements CommandLineRunner {
	@Autowired
	private MenuComponent component;

	public static void main(String[] args) {
		SpringApplication.run(ConsoleTweetappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		component.showMenu();
	}
}
