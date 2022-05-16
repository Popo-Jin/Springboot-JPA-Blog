package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC
public class UserService {
	
	@Autowired //객체 연결
	private UserRepository userRepository;
	
	@Transactional // 트랜잭션화 -> 전체 성공 시 commit, 실패 시 rollback
	public void 회원가입(User user) {

			userRepository.save(user);

	}
	@Transactional(readOnly = true) //SELECT할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
	public User 로그인(User user) {

		return	userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());

	}
}
