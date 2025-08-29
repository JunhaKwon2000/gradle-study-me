package com.junha.app.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MappedSuperclass // 상속 관계(애는 테이블 생성 안함)일 경우 부모라는 것을 명시
// @Entity // 해당 객체가 JPA에서 관리하고 있다라는 것을 정의, 필수임
// @Table(name="notice") // DB에 존재하는 테이블 이름을 매핑, 만약 name 속성을 안쓰면 클래스명이 테이블명이 됨 
public class BoardVO {
	@Id // PK로 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment - MySql
	private Long boardNum;
	
	@Column(nullable = false, unique = false, length = 255, insertable = true, updatable = true) // 제약
	private String boardTitle;
	private String boardWriter;
	@Column(columnDefinition = "LONGTEXT")
	// @Lob
	private String boardContent;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private LocalDateTime boardDate;
	// @Column(columnDefinition = "bigint default 0", insertable = false, nullable = false)
	@Column
	@ColumnDefault(value="0")
	private Long boardHit = 0L;
	
	@Transient // DB에는 안들어감
	private String kind;
}