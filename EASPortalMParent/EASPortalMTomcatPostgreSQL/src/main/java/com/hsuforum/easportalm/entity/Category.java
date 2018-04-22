package com.hsuforum.easportalm.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.hsuforum.common.entity.impl.BaseEntityImpl;


/**
 * The persistent class for the TB_CATEGORIES database table.
 * 
 */

@Entity
@Table(name="TB_CATEGORIES")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category extends BaseEntityImpl<String> {
	
	private static final long serialVersionUID = 4363113968058811799L;
	private String id;
	private String code;
	private String name;
	private Set<System> systems;
	private int sequence;
	public Category() {
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
	@Basic()
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
	//bi-directional many-to-one association to System
	@OneToMany(mappedBy="category", targetEntity = System.class)
	public Set<System> getSystems() {
		return systems;
	}


	public void setSystems(Set<System> systems) {
		this.systems = systems;
	}

	public System addSystem(System system) {
		if (getSystems() == null) {
			setSystems(new LinkedHashSet<System>());
		}
		getSystems().add(system);
		system.setCategory(this);

		return system;
	}




	public System removeSystem(System system) {
		if (getSystems() != null) {
			getSystems().remove(system);
		}
		system.setCategory(null);

		return system;
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}