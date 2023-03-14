package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/html/" ,"/css/", "/js/", "/fonts/");
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/userLogin") //ユーザー名、パスワードの送信先
				.loginPage("/userLogin") //ログイン画面のパス
				.defaultSuccessUrl("/showItemList", true) //ログイン成功後のリダイレクトのパス
				.failureUrl("/login?error").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/userLogin"))
				.authorizeHttpRequests(authz -> authz
						// staticの中のファイルを使用できるようにする記述
					.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						//下記の記述はログインなしでもアクセスを可能にする
						.antMatchers("/userLogin").permitAll().antMatchers("/userLogin/login").permitAll()
						.antMatchers("/user/toInsert").permitAll().antMatchers("/user/insert").permitAll()
						.antMatchers("/showItemList").permitAll().antMatchers("/selectPage").permitAll()
						.antMatchers("/showItemDetail").permitAll()
						.antMatchers("/add").permitAll().antMatchers("/add/mediumCategory").permitAll().antMatchers("/add/smallCategory").permitAll()
						.antMatchers("/search/searchItemAfterPage").permitAll().antMatchers("/search/searchItem").permitAll()
						.antMatchers("/edit").permitAll().antMatchers("/edit/editItem").permitAll().anyRequest()
						.authenticated()).csrf(csrf -> csrf.disable());
				

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
