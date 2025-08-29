package com.junha.app.board.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public NoticeVO detail(Long boardNum) {
		Optional<NoticeVO> result = noticeRepository.findById(boardNum);
		return result.orElseThrow();
	}

	public Page<NoticeVO> list(Pageable pageable) {
		Page<NoticeVO> result = noticeRepository.findAll(pageable); // Page 객체 자체에 모든 정보가 들어 있으므로 이 객체를 return
		return result;
	}
	
}
