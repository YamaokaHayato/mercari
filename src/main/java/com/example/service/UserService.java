package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザー情報を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * ユーザー情報を挿入します.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.insert(user);
	}

}
