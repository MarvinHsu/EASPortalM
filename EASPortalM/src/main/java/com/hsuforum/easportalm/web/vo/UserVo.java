package com.hsuforum.easportalm.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.User;

/**
 * User's Value Object Implement
 * 
 */
public class UserVo extends ValueObjectImpl<User, java.lang.String> {

	private static final long serialVersionUID = 6138174031000398081L;
	private List<Group> groupList = null;
	private List<Group> groupSelectedRowKeys;


	/**
	 * Constructor
	 * 
	 */
	public UserVo() {
		super(new User());
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public UserVo(User entity) {
		super(entity);
	}

	/**
	 * Get group list
	 * 
	 * @return List<group>
	 */
	public List<Group> getGroupList() {
		return this.groupList;
	}

	/**
	 * Set group list
	 * 
	 * @param groupList
	 */
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	/**
	 * Get checked Group collection
	 * 
	 * @return List<Object>
	 */
	public List<Group> getGroupSelectedRowKeys() {
		if (this.groupSelectedRowKeys == null) {
			this.groupSelectedRowKeys = new ArrayList<Group>();
		}
		return this.groupSelectedRowKeys;
	}

	/**
	 * Set checked Group collection 
	 * 
	 * @param GroupSelectedRowKeys
	 */
	public void setGroupSelectedRowKeys(List<Group> groupSelectedRowKeys) {
		this.groupSelectedRowKeys = groupSelectedRowKeys;
	}




}
