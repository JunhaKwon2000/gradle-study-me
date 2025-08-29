package com.junha.app.member;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * 복합키 적용
 * 1) Serializable 구현 필요
 * 2) equals, hashcode 오버라이드 필요
 * 3) 기본 생성자 필수
 * 4) 클래스는 public이어야함
 * */
@Getter @Setter @EqualsAndHashCode
@Embeddable
public class MemberRolePK implements Serializable {
	private String username;
	private Long roleNum;
}
