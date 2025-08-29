package com.junha.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.foreign.Linker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.junha.app.board.BoardVO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
// @Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	@Test
	void test() {
		NoticeVO boardVO = new NoticeVO();
		boardVO.setBoardTitle("test title 3");
		boardVO.setBoardContent("test content");
		boardVO.setBoardWriter("Junha");

//		NoticeFileVO noticeFileVO = new NoticeFileVO();
//		noticeFileVO.setOriName("ori");
//		noticeFileVO.setSaveName("save");
//		boardVO.addNoticeFileVO(noticeFileVO);
		
		// return 타입이 매개변수로 줄 데이터의 파라미터의 타입
		boardVO = noticeRepository.save(boardVO);
		
//		noticeFileVO.setNoticeVO(boardVO);
//		noticeFileVO = noticeFileRepository.save(noticeFileVO);
		
//		assertNotNull(noticeFileVO);
	}
	
	@Test
	void test2() {
		// Read 해보기
		Optional<NoticeVO> result = noticeRepository.findById(1L); // Optional객체 - NPE를 검증
		NoticeVO boardVO = result.get();
		System.out.println(boardVO.getBoardNum());
		System.out.println(boardVO.getFiles().getFirst().getSaveName());
	}
	
	@Test
	void test3() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("boardNum").descending());
		List<NoticeVO> list = noticeRepository.findByBoardTitleLike("%%", pageable);
		list.forEach(VO -> {
			System.out.println(VO);
		});
	}
	
	

}