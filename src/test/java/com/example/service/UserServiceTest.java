package com.example.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@SpringBootTest
class UserServiceTest {
	
	@Mock
	UserRepository userRepostory;
	AutoCloseable closeable;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	UserService userService;
	
	User user;

	@BeforeEach
	void openMocks() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("ユーザー登録のテスト")
	@Test
	void test() {
		
		user = new User();
		user.setUsername("テスト");
		user.setPassword("Test-test");
		user.setAuthority(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user);
		userService.insert(user);
		
		verify(userRepostory, times(1)).insert(user);
	}
	
	@AfterEach
	void closeMocks() throws Exception {
		closeable.close();
	}

}
