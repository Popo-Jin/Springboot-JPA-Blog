package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성이 자동으로 됨.
// @DynamicInsert // null 값은 제외하고 insert
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따름.
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true) // null값이면 안됨
	private String username; //아이디
	
	@Column(nullable = false, length = 100) // 길이를 100 주는 이유 -> 해쉬를 이용하여 암호화하기 위함
	private String password; //비밀번호
	
	@Column(nullable = false, length = 50)
	private String email; //이메일
	
	// @ColumnDefault("'user'") //" ' user ' "
	@Enumerated(EnumType.STRING)// DB는 RoleType이 없으므로 해당 타입이 Enum이라는 것을 알려주어야 함
	private RoleType role; // 도메인 설정을 하는 Enum을 쓰는게 좋음. (role -> admin, manager, user), String으로 하면 오타가 날 수 있음
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
