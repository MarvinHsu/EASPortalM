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

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the TB_CATEGORIES database table.
 * 
 */

@Entity
@Table(name="TB_CATEGORIES")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Category extends BaseEntityImpl<String> {
	
	private static final long serialVersionUID = 4363113968058811799L;
	@Id
	@Column(name = "ID", nullable = false)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 20)
	private String code;
	@Basic()
	@Column(name = "NAME", nullable = false)
	private String name;
	@Basic()
	@Column(name = "SEQUENCE")
	private int sequence;
	//bi-directional many-to-one association to System
	@OneToMany(mappedBy="category", targetEntity = System.class)
	private Set<System> systems;
	
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

}