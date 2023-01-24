package com.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoginUser extends org.springframework.security.core.userdetails.User  {
	
	private static final long serialVersionUID = -256740067874995659L;
	
	//Springの認証ユーザークラスに、user情報を保持させる。
	private final User user;
	
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		//Userクラスのコンストラクタを呼び出す。第1引数はユーザー名、第2引数はパスワード、第3引数は権限リスト
		super(user.getUsername(), user.getPassword(), authorityList);
		this.user = user;
	}
	
	//user情報のgetterを用意し、ログインユーザーのuserオブジェクトを取得する.
	public User getUser() {
		return user;
	}

}
