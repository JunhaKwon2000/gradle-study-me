package com.junha.app.member;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity @Table(name="member_role")
// @IdClass(MemberRolePK.class) // 이것도 복합키 설정할 떄 가능
public class MemberRoleVO {
	
//	@EmbeddedId
//	private MemberRolePK memberRolePK;
	
//	private String username; // 애는 해제해줘야함 IdClass 쓸 때는 + @ID
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", insertable = false, updatable = false) // EmbeddedId 쓸 때는 insertable,updatable false로
	private MemberVO memberVO;
	
//	private Long roleNum; // 애는 해제해줘야함 IdClass 쓸 때는 + @ID
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleNum", insertable = false, updatable = false) // EmbeddedId 쓸 때는 insertable,updatable false로 
	private RoleVO roleVO;
}
