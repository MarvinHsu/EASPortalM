package com.hsuforum.easportalm.web.jsf.managed.groupFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.FunctionItem;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.entity.GroupFunction;
import com.hsuforum.easportalm.service.FunctionItemService;
import com.hsuforum.easportalm.service.FunctionService;
import com.hsuforum.easportalm.service.GroupFunctionJpaService;
import com.hsuforum.easportalm.service.GroupFunctionService;
import com.hsuforum.easportalm.service.GroupService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.GroupFunctionVo;
import com.hsuforum.easportalm.web.vowrapper.GroupFunctionVoWrapper;

@Component
@SessionScope
public class GroupFunctionManagedBean extends
		TemplatePrimeDataTableManagedBean<GroupFunction, String, GroupFunctionService, GroupFunctionJpaService> {

	private static final long serialVersionUID = -4759945281183833719L;

	private String mode;

	@Autowired
	private GroupFunctionService service;
	@Autowired
	private GroupFunctionJpaService jpaService;
	@Autowired
	private GroupService groupService;
	private List<SelectItem> groupList;

	@Autowired
	private FunctionService functionService;
	private List<SelectItem> functionList;

	@Autowired
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
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initCreatingData()
	 */
	@Override
	protected void initCreatingData() {
		GroupFunction object = new GroupFunction();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<GroupFunction, String> updatingData) {

		if (this.getUpdatingData().getEntity().getGroup() != null) {
			this.getUpdatingData().setSelectGroupId(this.getUpdatingData().getEntity().getGroup().getId().toString());

		}

		if (this.getUpdatingData().getEntity().getFunction() != null) {
			this.getUpdatingData()
					.setSelectFunctionId(this.getUpdatingData().getEntity().getFunction().getId().toString());

		}

		if (this.getUpdatingData().getEntity().getFunctionItem() != null) {
			this.getUpdatingData()
					.setSelectFunctionItemId(this.getUpdatingData().getEntity().getFunctionItem().getId().toString());

		}

		this.setMode("Update");
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#initFindCriteriaMap()
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
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public GroupFunctionVo getUpdatingData() {
		return (GroupFunctionVo) super.getUpdatingData();
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<GroupFunction, String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getService()
	 */
	public GroupFunctionService getService() {

		return this.service;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(GroupFunctionService service) {
		this.service = service;
	}

	public GroupFunctionJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(GroupFunctionJpaService jpaService) {
		this.jpaService = jpaService;
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

		} else {
			this.getUpdatingData().getEntity().setGroup(null);

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

		} else {
			this.getUpdatingData().getEntity().setFunction(null);

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

		} else {
			this.getUpdatingData().getEntity().setFunctionItem(null);

		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupGroup();
		this.setupFunction();
		this.setupFunctionItem();

	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<GroupFunction> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
