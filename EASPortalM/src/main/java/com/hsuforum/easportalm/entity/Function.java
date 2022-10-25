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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the tbcl_functions database table.
 * 
 */

@Entity
@Table(name = "TB_FUNCTIONS")
@EntityListeners({ SystemDateEntityListener.class })
@NamedQuery(name = "Function.findAll", query = "SELECT f FROM Function f")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Function extends BaseEntityImpl<String> implements SystemDateOperation {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false)
	@EqualsAndHashCode.Include()
	private String id;
	@Basic()
	@Column(name = "CODE", nullable = false)
	private String code;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Basic()
	@Column(name = "NAME", nullable = false)
	private String name;
	@Basic()
	@Column(name = "OUTCOME")
	private String outcome;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	@Basic()
	@Column(name = "SEQUENCE")
	private int sequence;
	@Column(name = "SHOWED")
	private Boolean showed;
	// bi-directional many-to-one association to Module
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_MODULES_ID")
	private Module module;
	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "TB_SYSTEMS_id")
	private System system;
	// bi-directional many-to-one association to FunctionItem
	@OneToMany(mappedBy = "function", targetEntity = FunctionItem.class, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private Set<FunctionItem> functionItems;
	// bi-directional many-to-one association to GroupFunction
	@OneToMany(mappedBy = "function", targetEntity = GroupFunction.class)
	private Set<GroupFunction> groupFunctions;

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

}