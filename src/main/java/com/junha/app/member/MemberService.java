package com.junha.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public MemberVO register(MemberVO memberVO) {
		try {
			MemberVO result = memberRepository.save(memberVO);
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
