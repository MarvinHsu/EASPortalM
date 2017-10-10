package com.hsuforum.easportalm.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

/**
 * The persistent class for the tb_systems database table.
 * 
 */

@Entity
@Table(name = "tb_systems")
@NamedQuery(name = "System.findAll", query = "SELECT s FROM System s")
public class System extends BaseEntityImpl<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8009056925658383576L;
	private String id;
	private String code;
	private String name;
	private int sequence;
	private String url;
	private boolean openWindow;
	private Category category;
	private Set<Module> modules;
	private Set<Function> functions;
	private Set<Group> groups;

	public System() {
	}

	@Id
	@Column(name = "ID", nullable = false)
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
	@Basic()
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Basic()
	@Column(name = "OPEN_WINDOW")
	public boolean isOpenWindow() {
		return openWindow;
	}

	public void setOpenWindow(boolean openWindow) {
		this.openWindow = openWindow;
	}

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "tb_categories_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// bi-directional many-to-one association to System
	@OneToMany(mappedBy = "system", targetEntity = Module.class)
	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

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

	// bi-directional many-to-one association to System
	@OneToMany(mappedBy = "system", targetEntity = Function.class)
	public Set<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
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

	// bi-directional many-to-one association to System
	@OneToMany(mappedBy = "system", targetEntity = Group.class)
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
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
		System other = (System) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}