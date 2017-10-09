package com.hsuforum.easportalm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tbcl_groups_functions database table.
 * 
 */
@Embeddable
public class GroupFunctionPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String groupId;
	private String functionId;
	private String functionItemId;

	public GroupFunctionPK() {
	}

	@Column(name = "TB_GROUPS_ID", insertable = false, updatable = false)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "TB_FUNCTIONS_ID", insertable = false, updatable = false)
	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Column(name = "TB_FUNCTIONS_ITEMS_ID", insertable = false, updatable = false)
	public String getFunctionItemId() {
		return functionItemId;
	}

	public void setFunctionItemId(String functionItemId) {
		this.functionItemId = functionItemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((functionItemId == null) ? 0 : functionItemId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupFunctionPK other = (GroupFunctionPK) obj;
		if (functionId == null) {
			if (other.functionId != null)
				return false;
		} else if (!functionId.equals(other.functionId))
			return false;
		if (functionItemId == null) {
			if (other.functionItemId != null)
				return false;
		} else if (!functionItemId.equals(other.functionItemId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}

}