package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/userLogin") //ユーザー名、パスワードの送信先
				.loginPage("/userLogin") //ログイン画面のパス
				.defaultSuccessUrl("/showList") //ログイン成功後のリダイレクトのパス
				.failureUrl("/login?error").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/"))
				.authorizeHttpRequests(authz -> authz
						// staticの中のファイルを使用できるようにする記述
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						//下記の記述はログインなしでもアクセスを可能にする
						.antMatchers("/userLogin").permitAll().antMatchers("/userLogin/login").permitAll()
						.antMatchers("/user").permitAll().antMatchers("/user/insert").permitAll()
						.antMatchers("/showItemList").permitAll()
						.antMatchers("/showItemDetail").permitAll()
						.antMatchers("/add").permitAll()
						.antMatchers("/search").permitAll()
						.antMatchers("/edit").permitAll().anyRequest()
						.authenticated())
				//csrf対策
				.csrf(csrf -> csrf.disable());

		return http.build();
	}
	
	
	/**
	 * パスワードをハッシュ化させる.
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
