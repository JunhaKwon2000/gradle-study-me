package com.junha.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController // 모든 메서드에 ResponseBody 적용
@RequestMapping("/api/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("{boardNum}")
	public NoticeVO detail(@PathVariable("boardNum") Long boardNum) {
		NoticeVO result = noticeService.detail(boardNum);
		System.out.println(result.getBoardWriter());
		return result;
	}
	
	// https://juntcom.tistory.com/219
	@GetMapping("") // ?page=${번호}로 쿼리스트링으로 데이터를 줘서 페이징 처리를 간단하게 가능! 근데 밑에 페이지 번호 1, 2, 3 ... 이런거 뜨는 것은 직접 해야하긴함, 근데 이전 다음은 last / first 키를 통해서 접근가능하긴함(활성화하는것)
	public Page<NoticeVO> list(@PageableDefault(size = 10, sort = "boardNum", direction= Sort.Direction.DESC)
	Pageable pageable) {
		return noticeService.list(pageable);
	}
	
    // 이거 만약 json으로 보내면 @requestBody 붙여야댐
	@PostMapping("write")
	public boolean write(NoticeVO noticeVO) {
        NoticeVO result = noticeService.write(noticeVO);
        if (result != null) {
            return true;
        } else {
            return false;
        }
		// return noticeService.write(noticeVO);
	} 
	
}
