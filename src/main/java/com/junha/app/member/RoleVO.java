package com.junha.app.member;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity @Table(name="role")
public class RoleVO {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleNum;
	private String roleName;
	
	@OneToMany(mappedBy = "roleVO", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<MemberRoleVO> memberRoleVOs;
}
