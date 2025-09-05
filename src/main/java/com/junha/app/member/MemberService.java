package com.junha.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public MemberVO register(MemberVO memberVO) {
		try {
            memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
			MemberVO result = memberRepository.save(memberVO);
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberVO> result = memberRepository.findById(username);
        return result.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
