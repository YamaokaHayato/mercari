package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.UserLoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserLoginService userLoginService;
	
	// cssファイルなどへのアクセス制限に関する記述.
		@Bean
		public WebSecurityCustomizer webSecurityCustomizer() {
			return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**");
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
						.mvcMatchers("/userLogin").permitAll().mvcMatchers("/userLogin/login").permitAll()
						.mvcMatchers("/user/toInsert").permitAll().mvcMatchers("/user/insert").permitAll()
						.mvcMatchers("/showItemList").permitAll().mvcMatchers("/selectPage").permitAll()
						.mvcMatchers("/showItemDetail").permitAll()
						.mvcMatchers("/add").permitAll()
						.mvcMatchers("/search/searchItemAfterPage").permitAll()
						.mvcMatchers("/edit").permitAll().anyRequest()
						.authenticated());
				

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
