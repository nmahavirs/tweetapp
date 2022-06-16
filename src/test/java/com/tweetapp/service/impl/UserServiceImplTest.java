package com.tweetapp.service.impl;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.model.User;
import com.tweetapp.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	@Mock
	UserService userService;

	@Test
	void registerNewUserTest() {
		User user = new User();
		when(userService.getUser(user.getEmail())).thenReturn(null);
		when(userService.register(user)).thenReturn(user);
	}

}
