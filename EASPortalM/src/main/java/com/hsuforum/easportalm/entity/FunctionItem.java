package com.hsuforum.easportalm.entity;

import java.util.Date;
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

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the tbcl_functions_items database table.
 * 
 */

@Entity
@Table(name = "TB_FUNCTIONS_ITEMS")
@NamedQuery(name = "FunctionItem.findAll", query = "SELECT f FROM FunctionItem f")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class FunctionItem extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false)
	@EqualsAndHashCode.Include()
	private String id;
	@Basic()
	@Column(name = "CODE", nullable = false)
	private String code;
	@Basic()
	@Column(name = "NAME", nullable = false)
	private String name;
	@Basic()
	@Column(name = "URL", nullable = false)
	private String url;
	// bi-directional many-to-one association to Function
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ID")
	private Function function;
	// bi-directional many-to-one association to GroupFunction
	@OneToMany(mappedBy = "functionItem")
	private Set<GroupFunction> groupFunctions;

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

}