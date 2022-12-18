package com.hsuforum.easportalm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hsuforum.common.entity.SystemDateOperation;
import com.hsuforum.common.entity.impl.BaseEntityImpl;
import com.hsuforum.common.entity.impl.SystemDateEntityListener;
import com.hsuforum.common.web.jsf.utils.JSFUtils;
import com.hsuforum.common.web.util.TranslationUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
		@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name") })
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class User extends BaseEntityImpl<String> implements SystemDateOperation, UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "ACCOUNT", nullable = false, length = 40)
	private String account;
	@Basic()
	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "PASSWORD", nullable = true, length = 50)
	private String password;
	@Basic()
	@Column(name = "EMAIL", length = 30)
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	// bi-directional many-to-many association to Group
	@ManyToMany(targetEntity = Group.class, cascade = { CascadeType.MERGE })
	@JoinTable(name = "TB_USERS_GROUPS", joinColumns = {
			@JoinColumn(name = "TB_USERS_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "TB_GROUPS_ID", nullable = false) })
	private Set<Group> groups;
	@Transient
	private boolean accountNonExpired = true;
	@Transient
	private boolean accountNonLocked = true;
	@Transient
	private boolean credentialsNonExpired = true;


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

}