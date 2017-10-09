package com.hsuforum.easportalm.web.jsf.managed.groupFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.FunctionItem;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.GroupFunction;
import com.hsuforum.easportalm.entity.GroupFunctionPK;
import com.hsuforum.easportalm.service.FunctionItemService;
import com.hsuforum.easportalm.service.FunctionService;
import com.hsuforum.easportalm.service.GroupFunctionService;
import com.hsuforum.easportalm.service.GroupService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.GroupFunctionVo;
import com.hsuforum.easportalm.web.vowrapper.GroupFunctionVoWrapper;

@ManagedBean
@SessionScoped
public class GroupFunctionManagedBean extends
		TemplatePrimeDataTableManagedBean<GroupFunction, com.hsuforum.easportalm.entity.GroupFunctionPK, GroupFunctionService> {

	private static final long serialVersionUID = -4759945281183833719L;

	private String mode;

	@ManagedProperty(value = "#{groupFunctionService}")
	private GroupFunctionService service;

	@ManagedProperty(value = "#{groupService}")
	private GroupService groupService;
	private List<SelectItem> groupList;

	@ManagedProperty(value = "#{functionService}")
	private FunctionService functionService;
	private List<SelectItem> functionList;

	@ManagedProperty(value = "#{functionItemService}")
	private FunctionItemService functionItemService;
	private List<SelectItem> functionItemList;

	public GroupFunctionManagedBean() {

		super();

	}

	
	@PostConstruct
	public void init() {
		
		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new GroupFunctionVoWrapper());

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
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initCreatingData()
	 */
	@Override
	protected void initCreatingData() {
		GroupFunction object = new GroupFunction();
		GroupFunctionPK groupFunctionPK = new GroupFunctionPK();
		object.setId(groupFunctionPK);
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<GroupFunction, com.hsuforum.easportalm.entity.GroupFunctionPK> updatingData) {

		
		if (this.getUpdatingData().getEntity().getGroup() != null) {
			this.getUpdatingData().setSelectGroupId(this.getUpdatingData().getEntity().getGroup().getId().toString());

		}

		
		if (this.getUpdatingData().getEntity().getFunction() != null) {
			this.getUpdatingData().setSelectFunctionId(this.getUpdatingData().getEntity().getFunction().getId().toString());

		}

		
		if (this.getUpdatingData().getEntity().getFunctionItem() != null) {
			this.getUpdatingData()
					.setSelectFunctionItemId(this.getUpdatingData().getEntity().getFunctionItem().getId().toString());

		}

		this.setMode("Update");
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
		
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		findCriteriaMap.put("enable", null);
		this.setFindCriteriaMap(findCriteriaMap);
		
		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("enable", "eq");
		this.setFindOperMap(findOperMap);

		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("enable", "DESC");
		this.setFindSortMap(findSortMap);
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public GroupFunctionVo getUpdatingData() {
		return (GroupFunctionVo) super.getUpdatingData();
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<GroupFunction, com.hsuforum.easportalm.entity.GroupFunctionPK> vo) {
		super.setUpdatingData(vo);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getService()
	 */
	public GroupFunctionService getService() {

		return this.service;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(GroupFunctionService service) {
		this.service = service;
	}


	public List<SelectItem> getGroupList() {

		if (this.groupList == null) {
			this.groupList = new ArrayList<SelectItem>();
			this.groupList.add(SelectHelper.EMPTY_SELECTITEM);
			for (Group group : getGroupService().findAll()) {
				SelectItem item = new SelectItem();

				item.setValue(group.getId().toString());
				item.setLabel(group.getName());
				this.groupList.add(item);

			}
		}
		return groupList;
	}

	public GroupService getGroupService() {
		return this.groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	private void setupGroup() {
		if ((this.getUpdatingData().getSelectGroupId() != null)
				&& (this.getUpdatingData().getSelectGroupId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setGroup(getGroupService().findByPK(this.getUpdatingData().getSelectGroupId()));
			this.getUpdatingData().getEntity().getId().setGroupId(this.getUpdatingData().getSelectGroupId());
		} else {
			this.getUpdatingData().getEntity().setGroup(null);
			this.getUpdatingData().getEntity().getId().setGroupId(null);
		}
	}


	public List<SelectItem> getFunctionList() {

		if (this.functionList == null) {
			this.functionList = new ArrayList<SelectItem>();
			this.functionList.add(SelectHelper.EMPTY_SELECTITEM);
			for (Function function : getFunctionService().findAll()) {
				SelectItem item = new SelectItem();

				item.setValue(function.getId().toString());
				item.setLabel(function.getName());
				this.functionList.add(item);

			}
		}
		return functionList;
	}

	public FunctionService getFunctionService() {
		return this.functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	private void setupFunction() {

		if ((this.getUpdatingData().getSelectFunctionId() != null)
				&& (this.getUpdatingData().getSelectFunctionId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setFunction(getFunctionService().findByPK(this.getUpdatingData().getSelectFunctionId()));
			this.getUpdatingData().getEntity().getId().setFunctionId(this.getUpdatingData().getSelectFunctionId());
		} else {
			this.getUpdatingData().getEntity().setFunction(null);
			this.getUpdatingData().getEntity().getId().setFunctionId(null);
		}
	}

	public List<SelectItem> getFunctionItemList() {

		if (this.functionItemList == null) {
			this.functionItemList = new ArrayList<SelectItem>();
			
			this.functionItemList.add(SelectHelper.EMPTY_SELECTITEM);
			for (FunctionItem functionItem : getFunctionItemService().findAll()) {
				SelectItem item = new SelectItem();

				item.setValue(functionItem.getId().toString());
				item.setLabel(functionItem.getName());
				this.functionItemList.add(item);

			}
		}
		return functionItemList;
	}

	public FunctionItemService getFunctionItemService() {
		return this.functionItemService;
	}

	public void setFunctionItemService(FunctionItemService functionItemService) {
		this.functionItemService = functionItemService;
	}

	private void setupFunctionItem() {
		
		if ((this.getUpdatingData().getSelectFunctionItemId() != null)
				&& (this.getUpdatingData().getSelectFunctionItemId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity().setFunctionItem(
					getFunctionItemService().findByPK(this.getUpdatingData().getSelectFunctionItemId()));
			this.getUpdatingData().getEntity().getId().setFunctionItemId(this.getUpdatingData().getSelectFunctionItemId());
		} else {
			this.getUpdatingData().getEntity().setFunctionItem(null);
			this.getUpdatingData().getEntity().getId().setFunctionItemId(null);
		}
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupGroup();
		this.setupFunction();
		this.setupFunctionItem();

	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<GroupFunction> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
