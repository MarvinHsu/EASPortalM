package com.hsuforum.easportalm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hsuforum.common.entity.SystemDateOperation;
import com.hsuforum.common.entity.impl.BaseEntityImpl;
import com.hsuforum.common.entity.impl.SystemDateEntityListener;
import com.hsuforum.common.web.jsf.utils.JSFUtils;
import com.hsuforum.common.web.util.TranslationUtils;

/**
 * The persistent class for the TBSSO_USERS database table.
 * 
 */

@Entity
@Table(name = "TB_USERS")
@EntityListeners({ SystemDateEntityListener.class })
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByAccount", query = "SELECT u FROM User u WHERE u.account = :account"),
		@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password") })
public class User extends BaseEntityImpl<String> implements SystemDateOperation, UserDetails {
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private boolean enabled;
	private String name;
	private String password;
	private String email;
	private Date createDate;
	private Date updateDate;
	private Set<Group> groups;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;

	public User() {
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT", nullable = false, length = 40)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Basic()
	@Column(name = "ENABLED", nullable = false)
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD", nullable = true, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic()
	@Column(name = "EMAIL", length = 30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	// bi-directional many-to-many association to Group
	@ManyToMany(targetEntity = Group.class, cascade = { CascadeType.MERGE })
	@JoinTable(name = "TB_USERS_GROUPS", joinColumns = {
			@JoinColumn(name = "TB_USERS_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "TB_GROUPS_ID", nullable = false) })
	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Group addGroup(Group group) {
		if (getGroups() == null) {
			setGroups(new LinkedHashSet<Group>());
		}
		getGroups().add(group);
		group.getUsers().add(this);

		return group;
	}

	public Group removeGroup(Group group) {
		if (getGroups() != null) {
			getGroups().remove(group);
		}
		group.getUsers().remove(this);

		return group;
	}

	public void clearGroups() {
		this.setGroups(null);
	}



	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> arrayList = new ArrayList<GrantedAuthority>();

		for (Group group : this.getGroups()) {
			GrantedAuthority ga = (GrantedAuthority) group;
			arrayList.add(ga);
		}

		return arrayList;
	}

	@Transient
	@Override
	public String getUsername() {
		return this.account;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Transient()
	public String getDisplayName() throws NullPointerException {
		Locale locale = JSFUtils.getRequestLocale();
		if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
			StringBuffer name = new StringBuffer(this.getName());
			TranslationUtils.translate(name, "tradition2Simple");
			return name.toString();

		} else {
			return this.getName();
		}

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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}