package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	//username, password 가로챌 때 password 부분은 알아서 함
	//username이 DB에 있는지만 확인해주면 됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUsername(username)
		.orElseThrow(()-> {
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: " + username);
		});
		return new PrincipalDetail(principal);
	}
	
}
