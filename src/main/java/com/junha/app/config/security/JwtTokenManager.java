package com.junha.app.config.security;

import com.junha.app.member.MemberRepository;
import com.junha.app.member.MemberVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {

    @Value("${jwt.accessTokenExpireTime}")
    private Long accessTokenExpireTime;

    @Value("${jwt.refreshTokenExpireTime}")
    private Long refreshTokenExpireTime;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secretKey}")
    private String secretKey;

    private SecretKey key;

    @Autowired
    private MemberRepository memberRepository;

    @PostConstruct // 생성자 호출 전에 실행(생성자 호출 전에 초기화하고 싶은 것이 있을 경우 해주세용)
    public void init() {
        String k = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
        key = Keys.hmacShaKeyFor(k.getBytes());
    }

    private String generateToken(Authentication authentication, Long expireTime) {
        return Jwts
                .builder()
                .subject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .issuer(issuer)
                .signWith(key)
                .compact();
    }

    public String generateAccessToken(Authentication authentication) {
        return this.generateToken(authentication, accessTokenExpireTime);
    }

    public String generateRefreshToken(Authentication authentication) {
        return this.generateToken(authentication, refreshTokenExpireTime);
    }

    public Authentication getAuthentication(String token) throws Exception {
        Claims claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Optional<MemberVO> result = memberRepository.findById(claims.getSubject());
        MemberVO memberVO = result.get();
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
        return authentication;
    }
}
