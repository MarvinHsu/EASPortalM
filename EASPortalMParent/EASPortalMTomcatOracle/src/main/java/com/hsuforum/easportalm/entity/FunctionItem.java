package com.hsuforum.easportalm.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

/**
 * The persistent class for the tbcl_functions_items database table.
 * 
 */

@Entity
@Table(name = "tb_functions_items")
@NamedQuery(name = "FunctionItem.findAll", query = "SELECT f FROM FunctionItem f")
public class FunctionItem extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String url;
	private Function function;
	private Set<GroupFunction> groupFunctions;

	public FunctionItem() {
	}

	@Id
	@Column(name = "ID", nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic()
	@Column(name = "CODE", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Basic()
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic()
	@Column(name = "URL", nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// bi-directional many-to-one association to Function
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ID")
	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	// bi-directional many-to-one association to GroupFunction
	@OneToMany(mappedBy = "functionItem")
	public Set<GroupFunction> getGroupFunctions() {
		return this.groupFunctions;
	}

	public void setGroupFunctions(Set<GroupFunction> groupFunctions) {
		this.groupFunctions = groupFunctions;
	}

	public GroupFunction addGroupFunction(GroupFunction groupFunction) {
		if (getGroupFunctions() == null) {
			setGroupFunctions(new LinkedHashSet<GroupFunction>());
		}
		getGroupFunctions().add(groupFunction);
		groupFunction.setFunctionItem(this);

		return groupFunction;
	}

	public GroupFunction removeGroupFunction(GroupFunction groupFunction) {
		if (getGroupFunctions() != null) {
			getGroupFunctions().remove(groupFunction);
		}

		groupFunction.setFunctionItem(null);

		return groupFunction;
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
		FunctionItem other = (FunctionItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}