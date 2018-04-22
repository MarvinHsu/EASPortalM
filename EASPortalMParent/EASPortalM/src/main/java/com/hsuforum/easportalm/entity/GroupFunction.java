package com.hsuforum.easportalm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;


/**
 * The persistent class for the tbcl_groups_functions database table.
 * 
 */

@Entity
@Table(name = "TB_GROUPS_FUNCTIONS")
@NamedQuery(name = "GroupFunction.findAll", query = "SELECT g FROM GroupFunction g")
public class GroupFunction extends BaseEntityImpl<GroupFunctionPK> {
	private static final long serialVersionUID = 1L;
	private GroupFunctionPK id;
	private Function function;
	private FunctionItem functionItem;
	private Group group;

	public GroupFunction() {
	}

	@EmbeddedId
	public GroupFunctionPK getId() {
		return this.id;
	}

	public void setId(GroupFunctionPK id) {
		this.id = id;
	}

	// bi-directional many-to-one association to Function
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ID", nullable = false)
	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	// bi-directional many-to-one association to FunctionItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ITEMS_ID", nullable = false)
	public FunctionItem getFunctionItem() {
		return this.functionItem;
	}

	public void setFunctionItem(FunctionItem functionItem) {
		this.functionItem = functionItem;
	}

	// bi-directional many-to-one association to Group
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_GROUPS_ID", nullable = false)
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GroupFunction other = (GroupFunction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}