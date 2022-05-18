package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;


// 스프링 시큐리티가 로그인 요청 가로채서 UserDetails 타입 오브젝트를
// 시큐리티의 고유 세션저장소에 저장
public class PrincipalDetail implements UserDetails{
	private User user; //Composition

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴 (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 (true: 안잠김)
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	//비밀번호가 만료되었는지 (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//계정 활성화인지 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()-> {
			return "ROLE_"+user.getRole();
		});
		
		return collectors;
	}
	
}
