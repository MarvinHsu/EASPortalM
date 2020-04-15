package com.hsuforum.easportalm.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.hsuforum.common.entity.SystemDateOperation;
import com.hsuforum.common.entity.impl.BaseEntityImpl;
import com.hsuforum.common.entity.impl.SystemDateEntityListener;

/**
 * The persistent class for the tbcl_functions database table.
 * 
 */

@Entity
@Table(name = "TB_FUNCTIONS")
@EntityListeners({ SystemDateEntityListener.class })
@NamedQuery(name = "Function.findAll", query = "SELECT f FROM Function f")
public class Function extends BaseEntityImpl<String> implements SystemDateOperation {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Date createDate;
	private String name;
	private String outcome;
	private Module module;
	private Date updateDate;
	private int sequence;
	private Boolean showed;
	private System system;
	private Set<FunctionItem> functionItems;
	private Set<GroupFunction> groupFunctions;

	public Function() {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	@Column(name = "OUTCOME")
	public String getOutcome() {
		return this.outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	@Basic()
	@Column(name = "SEQUENCE")
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	// bi-directional many-to-one association to Module
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_MODULES_ID")
	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "TB_SYSTEMS_id")
	public System getSystem() {
		return this.system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	// bi-directional many-to-one association to FunctionItem
	@OneToMany(mappedBy = "function", targetEntity = FunctionItem.class, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	public Set<FunctionItem> getFunctionItems() {
		return this.functionItems;
	}

	public void setFunctionItems(Set<FunctionItem> functionItems) {
		this.functionItems = functionItems;
	}

	public FunctionItem addFunctionItem(FunctionItem functionItem) {
		if (getFunctionItems() == null) {
			setFunctionItems(new LinkedHashSet<FunctionItem>());
		}
		getFunctionItems().add(functionItem);
		functionItem.setFunction(this);

		return functionItem;
	}

	public FunctionItem removeFunctionItem(FunctionItem functionItem) {
		if (getFunctionItems() != null) {
			getFunctionItems().remove(functionItem);
		}

		functionItem.setFunction(null);

		return functionItem;
	}

	public void clearFunctionItems() {
		this.setFunctionItems(null);

	}

	// bi-directional many-to-one association to GroupFunction
	@OneToMany(mappedBy = "function", targetEntity = GroupFunction.class)
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
		groupFunction.setFunction(this);

		return groupFunction;
	}

	public GroupFunction removeGroupFunction(GroupFunction groupFunction) {
		if (getGroupFunctions() != null) {
			getGroupFunctions().remove(groupFunction);
		}

		groupFunction.setFunction(null);

		return groupFunction;
	}

	@Transient()
	public Boolean getShowed() {
		return showed;
	}

	public void setShowed(Boolean showed) {
		this.showed = showed;
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
		Function other = (Function) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}