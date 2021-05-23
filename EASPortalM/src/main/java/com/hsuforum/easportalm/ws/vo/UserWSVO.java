package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserWSVO implements Serializable{
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
	
}
