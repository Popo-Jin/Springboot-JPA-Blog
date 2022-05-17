package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC
public class UserService {
	
	@Autowired //객체 연결
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional // 트랜잭션화 -> 전체 성공 시 commit, 실패 시 rollback
	public void 회원가입(User user) {
			String rawPassword = user.getPassword(); //원문
			String encPassword = encoder.encode(rawPassword);
			user.setRole(RoleType.USER);
			user.setPassword(encPassword);
			userRepository.save(user);

	}
}
