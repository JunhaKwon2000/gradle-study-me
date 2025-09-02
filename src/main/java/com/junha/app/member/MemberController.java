package com.junha.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/*")
@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("register")
	public MemberVO register(@RequestBody MemberVO memberVO) {
		System.out.println(memberVO);
		MemberVO result = memberService.register(memberVO);
		if (result != null) {			
			return result;
		} else {
			return null;
		}
	}
	
}
