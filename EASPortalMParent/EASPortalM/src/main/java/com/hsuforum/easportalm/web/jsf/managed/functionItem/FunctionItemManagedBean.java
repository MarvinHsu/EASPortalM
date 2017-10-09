package com.hsuforum.easportalm.web.jsf.managed.functionItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.FunctionItem;
import com.hsuforum.easportalm.service.FunctionItemService;
import com.hsuforum.easportalm.service.FunctionService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.FunctionItemVo;
import com.hsuforum.easportalm.web.vowrapper.FunctionItemVoWrapper;

@ManagedBean
@SessionScoped
public class FunctionItemManagedBean
		extends TemplatePrimeDataTableManagedBean<FunctionItem, java.lang.String, FunctionItemService> {

	private static final long serialVersionUID = -3869631270660154620L;


	private String mode;

	@ManagedProperty(value = "#{functionItemService}")
	private FunctionItemService service;

	@ManagedProperty(value = "#{functionService}")
	private FunctionService functionService;
	private List<SelectItem> functionList;

	public FunctionItemManagedBean() {

		super();

	}

	@PostConstruct
	public void init() {

		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new FunctionItemVoWrapper());

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
		FunctionItem object = new FunctionItem();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<FunctionItem, java.lang.String> updatingData) {

		if (this.getUpdatingData().getEntity().getFunction() != null) {
			this.getUpdatingData().setSelectFunctionId(this.getUpdatingData().getEntity().getFunction().getId().toString());

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
		findCriteriaMap.put("url", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("url", "eq");
		this.setFindOperMap(findOperMap);

		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("name", "DESC");
		findSortMap.put("code", "DESC");
		findSortMap.put("url", "DESC");
		this.setFindSortMap(findSortMap);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public FunctionItemVo getUpdatingData() {
		return (FunctionItemVo) super.getUpdatingData();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<FunctionItem, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getService()
	 */
	public FunctionItemService getService() {

		return this.service;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(FunctionItemService service) {
		this.service = service;
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

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupFunction();

	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<FunctionItem> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
