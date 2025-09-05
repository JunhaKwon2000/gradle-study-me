package com.junha.app.board.notice;

import java.util.ArrayList;
import java.util.List;

import com.junha.app.board.BoardVO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // 해당 객체가 JPA에서 관리하고 있다라는 것을 정의, 필수임
@Table(name = "notice") // DB에 존재하는 테이블 이름을 매핑, 만약 name 속성을 안쓰면 클래스명이 테이블명이 됨
@Getter
@Setter
public class NoticeVO extends BoardVO {
	@OneToMany(mappedBy = "noticeVO", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<NoticeFileVO> files = new ArrayList<>();
	
	public void addNoticeFileVO(NoticeFileVO noticeFileVO) {
		files.add(noticeFileVO);
		noticeFileVO.setNoticeVO(this);
	}
}
