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
import javax.persistence.Transient;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

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
public class Module extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int sequence;
	private System system;
	private Set<Function> functions;

	// 是否在選單顯示
	private Boolean showed = false;

	public Module() {
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODE", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic()
	@Column(name = "SEQUENCE")
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	// bi-directional many-to-one association to Function
	@OneToMany(mappedBy = "module", targetEntity = Function.class, cascade = { CascadeType.ALL })
	@OrderBy("sequence ASC")
	public Set<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}

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

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "TB_SYSTEMS_id")
	public System getSystem() {
		return this.system;
	}

	public void setSystem(System system) {
		this.system = system;
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
		Module other = (Module) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}