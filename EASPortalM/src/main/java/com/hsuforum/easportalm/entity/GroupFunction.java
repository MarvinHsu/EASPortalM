package com.hsuforum.easportalm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tbcl_groups_functions database table.
 * 
 */

@Entity
@Table(name = "TB_GROUPS_FUNCTIONS")
@NamedQuery(name = "GroupFunction.findAll", query = "SELECT g FROM GroupFunction g")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class GroupFunction extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	// bi-directional many-to-one association to Function
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ID", nullable = false)
	@EqualsAndHashCode.Include()
	private Function function;
	// bi-directional many-to-one association to FunctionItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ITEMS_ID", nullable = false)
	@EqualsAndHashCode.Include()
	private FunctionItem functionItem;
	// bi-directional many-to-one association to Group
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_GROUPS_ID", nullable = false)
	@EqualsAndHashCode.Include()
	private Group group;



}