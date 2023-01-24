package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserLoginService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	// ユーザ名をキーにユーザ情報を取得.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.load(username);
        if(user == null) {
            throw new UsernameNotFoundException("username and password not registered");
        }   
        Collection<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new LoginUser(user, authorityList);
	}

}
