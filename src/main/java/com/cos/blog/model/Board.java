package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment 
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨 -> 용량이 커짐
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many == Many , User = One
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK(Foreign Key), 자바는 오브젝트를 저장할 수 있음
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아님. (FK가 아님) DB에 컬럼을 만들지 않게
																										// FetchType이 LAZY가 mappedBy는 default, LAZY는 있으면 가져오는 것
																										// EAGER로 바꾸는 이유 : 화면에 댓글을 항상 표시할 것이기 때문에, board를 가져오면 user랑 reply를 가져옴
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
