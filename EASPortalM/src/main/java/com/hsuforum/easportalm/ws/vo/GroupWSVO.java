package com.hsuforum.easportalm.ws.vo;

import java.io.Serializable;
import java.util.Iterator;

import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.GroupFunction;

public class GroupWSVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private GroupFunctionWSVO[] groupFunctionWSVOs;
	
	public GroupWSVO() {
		super();
		
	}
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GroupFunctionWSVO[] getGroupFunctionWSVOs() {
		return groupFunctionWSVOs;
	}
	public void setGroupFunctionWSVOs(GroupFunctionWSVO[] groupFunctionWSVOs) {
		this.groupFunctionWSVOs = groupFunctionWSVOs;
	}
}
