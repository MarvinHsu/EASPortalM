package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.User;

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
	public UserWSVO(){
		super();
	}
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public GroupWSVO[] getGroupWSVOs() {
		return groupWSVOs;
	}
	public void setGroupWSVOs(GroupWSVO[] groupWSVOs) {
		this.groupWSVOs = groupWSVOs;
	}
	
	
}
