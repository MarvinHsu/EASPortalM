package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserWSVO implements Serializable,UserDetails{
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private boolean enabled;
	private String name;
	private String password;
	private String email;
	private Date createDate;
	private Date updateDate;
	private GroupWSVO[] groupWSVOs;

	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	
	public UserWSVO(User user){
		super();
		this.setId(user.getId());
		this.setAccount(user.getAccount());
		this.setEnabled(user.isEnabled());
		this.setName(user.getName());
		user.setPassword(user.getPassword());
		user.setEmail(user.getEmail());
		if(user.getCreateDate()!=null){
			this.setCreateDate(user.getCreateDate());
		}
		if(user.getUpdateDate()!=null){
			this.setUpdateDate(user.getUpdateDate());
		}
		if(user.getGroups()!=null&&user.getGroups().size()>0){
			this.setGroupWSVOs(new GroupWSVO[user.getGroups().size()]);
			Iterator<Group> iterator=user.getGroups().iterator();
			int i=0;
			while(iterator.hasNext()){
				this.getGroupWSVOs()[i]=new GroupWSVO(iterator.next());
				i++;
			}
		}
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> arrayList = new ArrayList<GrantedAuthority>();
		for (GroupWSVO groupWSVO : this.getGroupWSVOs()) {
			GrantedAuthority ga = (GrantedAuthority) groupWSVO;
			arrayList.add(ga);
		}

		return arrayList;
	}
	@Override
	public String getUsername() {
		return this.account;
	}

		
}
