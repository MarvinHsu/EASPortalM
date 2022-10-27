package com.hsuforum.easportalm.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the TBSSO_MODULES database table.
 * 
 */

@Entity
@Table(name = "TB_MODULES")
@NamedQueries({ @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
		@NamedQuery(name = "Module.findById", query = "SELECT m FROM Module m WHERE m.id = :id"),
		@NamedQuery(name = "Module.findByName", query = "SELECT m FROM Module m WHERE m.name = :name"),
		@NamedQuery(name = "Module.findByCode", query = "SELECT m FROM Module m WHERE m.code = :code") })
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Module extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 20)
	private String code;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Basic()
	@Column(name = "SEQUENCE")
	private int sequence;
	@Column(name = "SHOWED")
	private Boolean showed;
	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "TB_SYSTEMS_id")
	private System system;
	// bi-directional many-to-one association to Function
	@OneToMany(mappedBy = "module", targetEntity = Function.class, cascade = { CascadeType.ALL })
	@OrderBy("sequence ASC")
	private Set<Function> functions;

	public Function addFunction(Function function) {
		if (getFunctions() == null) {
			setFunctions(new LinkedHashSet<Function>());
		}
		getFunctions().add(function);
		function.setModule(this);

		return function;
	}

	public Function removeFunction(Function function) {
		if (getFunctions() != null) {
			getFunctions().remove(function);
		}
		function.setModule(null);

		return function;
	}

	public void clearFunctions() {

		this.setFunctions(null);

	}

}