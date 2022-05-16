package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다.
@Repository // 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Naming 쿼리전략
	// SELECT * FROM user WHERE username = ?1param AND password = ?2param
	User findByUsernameAndPassword(String username, String password);
	
	//@Query(value="SELECT * FROM user WHERE username = ?1param AND password = ?2param", nativeQuery = true) //위와 동일
	//User login(String username, String password);
}
