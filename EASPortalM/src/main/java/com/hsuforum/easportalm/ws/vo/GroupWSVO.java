package com.hsuforum.easportalm.ws.vo;

import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;

import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.GroupFunction;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class GroupWSVO implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private GroupFunctionWSVO[] groupFunctionWSVOs;
	private String authority;

	public GroupWSVO(Group group) {
		super();
		this.setId(group.getId());
		this.setCode(group.getCode());
		this.setName(group.getName());

		if(group.getGroupFunctions()!=null&&group.getGroupFunctions().size()>0){
			this.setGroupFunctionWSVOs(new GroupFunctionWSVO[group.getGroupFunctions().size()]);
			Iterator<GroupFunction> iterator=group.getGroupFunctions().iterator();
			int i=0;
			while(iterator.hasNext()){
				this.getGroupFunctionWSVOs()[i]=new GroupFunctionWSVO(iterator.next());
				i++;
			}
		}
	}
	
	@Override
	public String getAuthority() {
		return "ROLE_" + this.getName();
	}
}
