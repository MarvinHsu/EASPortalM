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

/**
 * The persistent class for the TBSSO_GROUPS database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name = "tb_groups")
@NamedQueries({ @NamedQuery(name = "Group.findAll", query = "SELECT t FROM Group t"),
		@NamedQuery(name = "Group.findById", query = "SELECT t FROM Group t WHERE t.id = :id"),
		@NamedQuery(name = "Group.findByName", query = "SELECT t FROM Group t WHERE t.name = :name"),
		@NamedQuery(name = "Group.findByCode", query = "SELECT t FROM Group t WHERE t.code = :code") })
public class Group extends BaseEntityImpl<String> implements GrantedAuthority {

	private static final String MANAGE_USER_TYPE_ID = "10000005-0000-0000-0000-000000000001";
	public final static Group MANAGE_USER_TYPE = new Group(MANAGE_USER_TYPE_ID, "系統管理", "MANAGE");
	/**
	 * Portal User
	 */
	private static final String PORTAL_USER_TYPE_ID = "10000005-0000-0000-0000-000000000002";
	public final static Group PORTAL_USER_TYPE = new Group(PORTAL_USER_TYPE_ID, "Portal User", "PU");

	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private System system;
	private Set<GroupFunction> groupFunctions;
	private Set<User> users;

	public Group() {
	}

	public Group(String id, String name, String code) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
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

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "tb_systems_id")
	public System getSystem() {
		return this.system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	// bi-directional many-to-one association to GroupFunction
	@OneToMany(mappedBy = "group", targetEntity = GroupFunction.class, cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	public Set<GroupFunction> getGroupFunctions() {
		return this.groupFunctions;
	}

	public void setGroupFunctions(Set<GroupFunction> groupFunctions) {
		this.groupFunctions = groupFunctions;
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

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "groups", targetEntity = User.class, cascade = { CascadeType.ALL })
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}