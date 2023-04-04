package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.User;

@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	UserRepository userRepository;
	

	@BeforeEach
	void setUpDatabase() {
		
//		jdbc.execute("INSERT INTO users VALUES (1, 'テスト', 'Test-test', null)");
		
	}
	
	@DisplayName("ユーザー情報を1件検索するテスト")
	@Test
	void load() {
		
		User user = new User();
		user = userRepository.load("テスト");
		
		assertEquals("テスト", user.getUsername(), "usernameが登録されていません");
		assertEquals("Test-test", user.getPassword(), "passwordが登録されていません");
		
	}
	
	@AfterEach
	void setUpAfterTransaction() {
//		
//		jdbc.execute("DELETE FROM users");
	}

}
