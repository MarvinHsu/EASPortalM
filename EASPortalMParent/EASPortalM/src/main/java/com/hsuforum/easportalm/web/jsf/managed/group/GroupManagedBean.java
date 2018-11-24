package com.hsuforum.easportalm.web.jsf.managed.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.FunctionItem;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.GroupFunction;
import com.hsuforum.easportalm.entity.GroupFunctionPK;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.entity.User;
import com.hsuforum.easportalm.service.FunctionService;
import com.hsuforum.easportalm.service.GroupService;
import com.hsuforum.easportalm.service.SystemService;
import com.hsuforum.easportalm.service.UserService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.FunctionVo;
import com.hsuforum.easportalm.web.vo.GroupVo;
import com.hsuforum.easportalm.web.vowrapper.FunctionVoWrapper;
import com.hsuforum.easportalm.web.vowrapper.GroupVoWrapper;

@ManagedBean
@SessionScoped
public class GroupManagedBean extends TemplatePrimeDataTableManagedBean<Group, java.lang.String, GroupService> {

	private static final long serialVersionUID = 1096387523639795946L;

	private String mode;

	@ManagedProperty(value = "#{groupService}")
	private GroupService service;

	@ManagedProperty(value = "#{functionService}")
	private FunctionService functionService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	// SystemService
	@ManagedProperty(value = "#{systemService}")
	private SystemService systemService;
	private List<SelectItem> systemList;
	
	public GroupManagedBean() {

		super();

	}

	@PostConstruct
	public void init() {

		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new GroupVoWrapper());

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
	 * in update mode system can't change
	 * @return
	 */
	public boolean isDisabledSystem() {
		if(this.getMode().equals("Update")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initCreatingData()
	 */
	@Override
	protected void initCreatingData() {
		Group object = new Group();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		this.getUpdatingData().setUserList(this.getUserList());
		this.getUpdatingData().getEntity().setGroupFunctions(new HashSet<GroupFunction>());
		this.getUpdatingData().setFunctionVoList(new ArrayList<FunctionVo>());
		
		for (Function function : this.getFunctionList()) {
			FunctionVoWrapper functionVoWrapper = new FunctionVoWrapper();
			FunctionVo functionVo = functionVoWrapper.wrap(function);
			this.getUpdatingData().getFunctionVoList().add(functionVo);
		}
		
		this.setMode("Create");

	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<Group, java.lang.String> updatingData) {

		if (this.getUpdatingData().getEntity().getSystem() != null) {
			this.getUpdatingData().setSelectSystemId(this.getUpdatingData().getEntity().getSystem().getId().toString());

		}
		this.getUpdatingData().setUserList(this.getUserList());
		Set<User> userList = this.getUpdatingData().getEntity().getUsers();
		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getUserList().size(); i++) {

				User user = (User) this.getUpdatingData().getUserList().get(i);

				if (userList.contains(user)) {

					this.getUpdatingData().getUserSelectedRowKeys().add(user);
				}
			}
		}
		for (Function function : this.getFunctionList()) {
			FunctionVoWrapper functionVoWrapper = new FunctionVoWrapper();
			FunctionVo functionVo = functionVoWrapper.wrap(function);
			for (GroupFunction groupFunction : this.getUpdatingData().getEntity().getGroupFunctions()) {

				if (groupFunction.getFunction().equals(function)) {

					for (FunctionItem functionItem : function.getFunctionItems()) {
						FunctionItem functionItem2 = groupFunction.getFunctionItem();

						if (functionItem.getId().equals(functionItem2.getId())) {
							functionVo.getFunctionItemSelectedRowKeys().add(functionItem2);
						}

					}

				}
			}
			functionVo.setFunctionItemChecked(new String[functionVo.getFunctionItemSelectedRowKeys().size()]);
			for (int i = 0; i < functionVo.getFunctionItemChecked().length; i++) {
				functionVo.getFunctionItemChecked()[i] = functionVo.getFunctionItemSelectedRowKeys().get(i).getId();
			}

			this.getUpdatingData().getFunctionVoList().add(functionVo);
		}

		this.setMode("Update");

	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {

		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		findCriteriaMap.put("name", null);
		findCriteriaMap.put("code", null);
		findCriteriaMap.put("system.id", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("system.id", "eq");
		this.setFindOperMap(findOperMap);

		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("name", "ASC");
		findSortMap.put("code", "ASC");
		findSortMap.put("system.id", "ASC");
		this.setFindSortMap(findSortMap);
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public GroupVo getUpdatingData() {
		return (GroupVo) super.getUpdatingData();
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<Group, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getService()
	 */
	public GroupService getService() {

		return this.service;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(GroupService service) {
		this.service = service;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public List<Function> getFunctionList() {
		String systemId=this.getUpdatingData().getSelectSystemId();
		List<Function> functionList = new ArrayList<Function>();
		if(StringUtils.isNotBlank(systemId)){
			for (Function function : getFunctionService().findBySystemIdFetchRelation(systemId)) {
	
				functionList.add(function);
	
			}
		}
		return functionList;
	}

	public List<User> getUserList() {

		List<User> manyBoList = new ArrayList<User>();

		for (User manyBo : getUserService().findAllFetchRelation()) {
			manyBoList.add(manyBo);

		}
		return manyBoList;
	}
	public List<SelectItem> getSystemList() {

		if (this.systemList == null) {
			this.systemList = new ArrayList<SelectItem>();
			// 第一個SelectItem為預設為SelectHelper.EMPTY_SELECTITEM
			this.systemList.add(SelectHelper.EMPTY_SELECTITEM);
			for (System system : this.getSystemService().findAll()) {
				SelectItem item = new SelectItem();

				/**
				 * 由於不是每個Entity(Business
				 * Object)都有name屬性，所以有可能需要修正setLabel()，不然就是Entity的toString()必須實作需要的部分
				 *
				 */

				item.setValue(system.getId().toString());
				item.setLabel(system.getName());
				this.systemList.add(item);

			}
		}
		return systemList;
	}
	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	private void setupUser() {

		if (this.getUpdatingData().getUserList() != null) {
			List<User> userSelectedRowKeys = this.getUpdatingData().getUserSelectedRowKeys();
			this.getUpdatingData().getEntity().clearUsers();

			Iterator<User> userSelectedRowKeyIterator = userSelectedRowKeys.iterator();

			while (userSelectedRowKeyIterator.hasNext()) {
				User rowKey = userSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addUser(rowKey);
			}
		}
	}

	/**
	 * Setup System
	 */
	private void setupSystem() {

		if ((this.getUpdatingData().getSelectSystemId() != null)
				&& (this.getUpdatingData().getSelectSystemId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setSystem(getSystemService().findByPK(this.getUpdatingData().getSelectSystemId()));
		} else {
			this.getUpdatingData().getEntity().setSystem(null);
		}
	}

	private void setupGroupFunction() {
		this.getUpdatingData().getEntity().clearGroupFunctions();

		for (FunctionVo functionVo : this.getUpdatingData().getFunctionVoList()) {
			for (String functionItemId : functionVo.getFunctionItemChecked()) {
				GroupFunctionPK groupFunctionPK = new GroupFunctionPK();
				groupFunctionPK.setFunctionId(functionVo.getEntity().getId());
				groupFunctionPK.setFunctionItemId(functionItemId);
				groupFunctionPK.setGroupId(this.getUpdatingData().getEntity().getId());

				GroupFunction groupFunction = new GroupFunction();
				groupFunction.setId(groupFunctionPK);
				groupFunction.setGroup(this.getUpdatingData().getEntity());
				groupFunction.setFunction(functionVo.getEntity());
				for (FunctionItem functionItem : functionVo.getEntity().getFunctionItems()) {
					if (functionItemId.equals(functionItem.getId())) {
						groupFunction.setFunctionItem(functionItem);
					}
				}

				this.getUpdatingData().getEntity().addGroupFunction(groupFunction);
			}

		}

		if (this.getUpdatingData().getUserList() != null) {
			List<User> userSelectedRowKeys = this.getUpdatingData().getUserSelectedRowKeys();
			this.getUpdatingData().getEntity().clearUsers();

			Iterator<User> userSelectedRowKeyIterator = userSelectedRowKeys.iterator();

			while (userSelectedRowKeyIterator.hasNext()) {
				User rowKey = userSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addUser(rowKey);
			}
		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupSystem();
		this.setupUser();
		this.setupGroupFunction();
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Group> findAllData() {
		return this.getService().findAllFetchRelation();
	}
	public void handleChangeSystem(ValueChangeEvent event){  
	    String systemId=event.getNewValue().toString();
	    this.getUpdatingData().getFunctionVoList().clear();
	    this.getUpdatingData().setSelectSystemId(systemId);
	    for (Function function : this.getFunctionList()) {
			FunctionVoWrapper functionVoWrapper = new FunctionVoWrapper();
			FunctionVo functionVo = functionVoWrapper.wrap(function);
			this.getUpdatingData().getFunctionVoList().add(functionVo);
		}
	}
}
