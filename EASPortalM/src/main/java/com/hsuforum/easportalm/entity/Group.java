package com.hsuforum.easportalm.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the TBSSO_GROUPS database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name = "TB_GROUPS")
@NamedQueries({ @NamedQuery(name = "Group.findAll", query = "SELECT t FROM Group t"),
		@NamedQuery(name = "Group.findById", query = "SELECT t FROM Group t WHERE t.id = :id"),
		@NamedQuery(name = "Group.findByName", query = "SELECT t FROM Group t WHERE t.name = :name"),
		@NamedQuery(name = "Group.findByCode", query = "SELECT t FROM Group t WHERE t.code = :code") })
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Group extends BaseEntityImpl<String> implements GrantedAuthority {

	private static final String MANAGE_USER_TYPE_ID = "10000005-0000-0000-0000-000000000001";
	public final static Group MANAGE_USER_TYPE = new Group(MANAGE_USER_TYPE_ID, "系統管理", "MANAGE");
	/**
	 * Portal User
	 */
	private static final String PORTAL_USER_TYPE_ID = "10000005-0000-0000-0000-000000000002";
	public final static Group PORTAL_USER_TYPE = new Group(PORTAL_USER_TYPE_ID, "Portal User", "PU");

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 20)
	private String code;
	@Column(name = "NAME", nullable = false)
	private String name;
	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "TB_SYSTEMS_id")
	private System system;
	// bi-directional many-to-one association to GroupFunction
	@OneToMany(mappedBy = "group", targetEntity = GroupFunction.class, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private Set<GroupFunction> groupFunctions;
	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "groups", targetEntity = User.class, cascade = { CascadeType.ALL })
	private Set<User> users;

	public Group(String id, String name, String code) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public GroupFunction addGroupFunction(GroupFunction groupFunction) {
		if (getGroupFunctions() == null) {
			setGroupFunctions(new LinkedHashSet<GroupFunction>());
		}
		getGroupFunctions().add(groupFunction);
		groupFunction.setGroup(this);

		return groupFunction;
	}

	public GroupFunction removeGroupFunction(GroupFunction groupFunction) {
		if (getGroupFunctions() != null) {
			getGroupFunctions().remove(groupFunction);
		}
		groupFunction.setGroup(null);

		return groupFunction;
	}

	public void clearGroupFunctions() {

		this.setGroupFunctions(null);

	}

	public User addUser(User user) {
		if (getUsers() == null) {
			setUsers(new LinkedHashSet<User>());
		}
		getUsers().add(user);
		user.getGroups().add(this);

		return user;
	}

	public User removeUser(User user) {
		if (getUsers() != null) {
			getUsers().remove(user);
		}
		user.getGroups().remove(this);

		return user;
	}

	public void clearUsers() {
		this.setUsers(null);
	}

	@Transient
	@Override
	public String getAuthority() {
		return "ROLE_" + this.getName();
	}

}