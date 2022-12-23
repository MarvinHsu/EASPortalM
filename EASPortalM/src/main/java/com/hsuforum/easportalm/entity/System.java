package com.hsuforum.easportalm.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the TB_SYSTEMS database table.
 * 
 */

@Entity
@Table(name = "TB_SYSTEMS")
@NamedQuery(name = "System.findAll", query = "SELECT s FROM System s")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class System extends BaseEntityImpl<String> {

	private static final long serialVersionUID = -8009056925658383576L;
	@Id
	@Column(name = "ID", nullable = false)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 20)
	private String code;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Basic()
	@Column(name = "SEQUENCE")
	private int sequence;
	@Basic()
	@Column(name = "SHOWED")
	private Boolean showed;
	@Basic()
	@Column(name = "URL")
	private String url;
	@Basic()
	@Column(name = "OPEN_WINDOW")
	private boolean openWindow;
	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "TB_CATEGORIES_id")
	private Category category;
	// bi-directional many-to-one association to System
	@OneToMany(mappedBy = "system", targetEntity = Module.class)
	private Set<Module> modules;
	// bi-directional many-to-one association to System
	@OneToMany(mappedBy = "system", targetEntity = Function.class)	
	private Set<Function> functions;
	// bi-directional many-to-one association to System
	@OneToMany(mappedBy = "system", targetEntity = Group.class)
	private Set<Group> groups;

	public Module addModule(Module module) {
		if (getModules() == null) {
			setModules(new LinkedHashSet<Module>());
		}
		getModules().add(module);
		module.setSystem(this);

		return module;
	}

	public Module removeModule(Module module) {
		if (getModules() != null) {
			getModules().remove(module);
		}
		module.setSystem(null);

		return module;
	}

	public Function addFunction(Function function) {
		if (getFunctions() == null) {
			setFunctions(new LinkedHashSet<Function>());
		}
		getFunctions().add(function);
		function.setSystem(this);

		return function;
	}

	public Function removeFunction(Function function) {
		if (getFunctions() != null) {
			getFunctions().remove(function);
		}
		function.setSystem(null);

		return function;
	}

	public Group addGroup(Group group) {
		if (getGroups() == null) {
			setGroups(new LinkedHashSet<Group>());
		}
		getGroups().add(group);
		group.setSystem(this);

		return group;
	}

	public Group removeGroup(Group group) {
		if (getGroups() != null) {
			getGroups().remove(group);
		}
		group.setSystem(null);

		return group;
	}
	
}