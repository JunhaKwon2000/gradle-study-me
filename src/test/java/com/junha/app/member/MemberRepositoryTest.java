package com.junha.app.member;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	void test() {
		MemberVO memberVO = new MemberVO();
		memberVO.setName("Junha Kwon");
		memberVO.setUsername("junharoket");
		memberVO.setPassword("123qweasd");
		memberVO.setEmail("junharoket@naver.com");
		memberVO.setBirth(LocalDate.now());
		
		
		List<MemberRoleVO> list = new ArrayList<>();
		MemberRoleVO memberRoleVO = new MemberRoleVO();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleNum(3L);
		memberRoleVO.setMemberVO(memberVO);
		memberRoleVO.setRoleVO(roleVO);
		list.add(memberRoleVO);
		memberVO.setMemberRoleVOs(list);
		
		memberVO = memberRepository.save(memberVO);
	}
	
	@Test
	void test2() {
		List<String> list = new ArrayList<>();
		list.add("ROLE_ADMIN");
		list.add("ROLE_MANAGER");
		list.add("ROLE_MEMBER");
		
		for (String role : list) {
			RoleVO roleVO = new RoleVO();
			roleVO.setRoleName(role);
			roleVO = roleRepository.save(roleVO);
			System.out.println(roleVO);
		}
	}

}
