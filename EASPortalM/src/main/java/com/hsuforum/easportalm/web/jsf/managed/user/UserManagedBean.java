package com.hsuforum.easportalm.web.jsf.managed.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.User;
import com.hsuforum.easportalm.service.GroupService;
import com.hsuforum.easportalm.service.UserJpaService;
import com.hsuforum.easportalm.service.UserService;
import com.hsuforum.easportalm.web.vo.UserVo;
import com.hsuforum.easportalm.web.vowrapper.UserVoWrapper;

import jakarta.annotation.PostConstruct;

@Component
@SessionScope
public class UserManagedBean extends TemplatePrimeDataTableManagedBean<User, java.lang.String, UserService, UserJpaService> {

	private static final long serialVersionUID = 4704000081629878950L;

	private String mode;

	@Autowired
	private UserService service;

	@Autowired
	private UserJpaService jpaService;

	@Autowired
	private GroupService groupService;


	public UserManagedBean() {

		super();

	}

	@PostConstruct
	public void init() {
	
		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new UserVoWrapper());

	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initCreatingData()
	 */
	@Override
	protected void initCreatingData() {
		User object = new User();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		
		this.getUpdatingData().setGroupList(this.getGroupList());
		this.setMode("Create");
	}


	/**
	 * If you need to process updating data after press create or update button, you
	 * need override this method 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<User, java.lang.String> updatingData) {

		
		
		this.getUpdatingData().setGroupList(this.getGroupList());
		Set<Group> groupList = this.getUpdatingData().getEntity().getGroups();
		if (groupList != null && groupList.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getGroupList().size(); i++) {

				Group group = (Group) this.getUpdatingData().getGroupList().get(i);
				
				if (groupList.contains(group)) {
					
					this.getUpdatingData().getGroupSelectedRowKeys().add(group);
				}
			}
		}

		this.setMode("Update");
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
	
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		findCriteriaMap.put("email", null);
		findCriteriaMap.put("name", null);
		findCriteriaMap.put("account", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("email", "eq");
		findOperMap.put("name", "eq");
		findOperMap.put("account", "eq");
		this.setFindOperMap(findOperMap);

		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("email", "DESC");
		findSortMap.put("name", "DESC");
		findSortMap.put("account", "DESC");
		this.setFindSortMap(findSortMap);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public UserVo getUpdatingData() {
		return (UserVo) super.getUpdatingData();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<User, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getService()
	 */
	public UserService getService() {

		return this.service;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(UserService service) {
		this.service = service;
	}


	public UserJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(UserJpaService jpaService) {
		this.jpaService = jpaService;
	}

	public List<Group> getGroupList() {

		List<Group> manyBoList = new ArrayList<Group>();

		for (Group manyBo : getGroupService().findAllFetchRelation()) {
			manyBoList.add(manyBo);

		}
		return manyBoList;
	}

	public GroupService getGroupService() {
		return this.groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}


	private void setupGroup() {

		if (this.getUpdatingData().getGroupList() != null) {
			List<Group> groupSelectedRowKeys = this.getUpdatingData().getGroupSelectedRowKeys();
			this.getUpdatingData().getEntity().clearGroups();
			
			Iterator<Group> groupSelectedRowKeyIterator = groupSelectedRowKeys.iterator();

			while (groupSelectedRowKeyIterator.hasNext()) {
				Group rowKey = groupSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addGroup(rowKey);
			}
		}
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.getUpdatingData().getEntity().setAccount(this.getUpdatingData().getEntity().getAccount().toUpperCase());
		this.setupGroup();
	}


	/**
	 * If entity has many-to-one or many-to-many relation then Code Generator will
	 * make this method for modifying. You can modify it for your need Method. The
	 * main function is in read page fetch all relational date to avoid update page
	 * occur error.
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<User> findAllData() {
		return this.getService().findAllFetchRelation();
	}
	


}
