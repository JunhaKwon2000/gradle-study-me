package com.junha.app.board.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name="notice_files")
public class NoticeFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNum;
	// private Long boardNum;
	private String oriName;
	private String saveName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardNum")
	@JsonIgnore // JSON 구성 시 직렬화 무시
	private NoticeVO noticeVO;
	
}
