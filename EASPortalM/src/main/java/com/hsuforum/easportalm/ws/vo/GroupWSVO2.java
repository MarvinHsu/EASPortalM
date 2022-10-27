package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import com.hsuforum.easportalm.entity.Group;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class GroupWSVO2 implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String authority;

	public GroupWSVO2(Group group) {
		super();
		this.setId(group.getId());
		this.setCode(group.getCode());
		this.setName(group.getName());
		this.setAuthority(group.getAuthority());

	}

}
