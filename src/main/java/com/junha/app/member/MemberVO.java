package com.junha.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter @Setter @ToString
@Entity @Table(name = "member")
public class MemberVO implements UserDetails {
	@Id
	private String username;
	private String password;
	private String name;
	private String email;
	@Temporal(TemporalType.DATE)
	private LocalDate birth;
	
	@OneToMany(mappedBy = "memberVO", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	List<MemberRoleVO> memberRoleVOs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.memberRoleVOs.forEach(memberRoleVO -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(memberRoleVO.getRoleVO().getRoleName());
            authorities.add(simpleGrantedAuthority);
        });

        return authorities;
    }
}
