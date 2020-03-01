package com.hsuforum.easportalm.web.jsf.managed.module;

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

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Module;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.ModuleJpaService;
import com.hsuforum.easportalm.service.ModuleService;
import com.hsuforum.easportalm.service.SystemService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.ModuleVo;
import com.hsuforum.easportalm.web.vowrapper.ModuleVoWrapper;

@ManagedBean
@SessionScoped
public class ModuleManagedBean extends TemplatePrimeJpaDataTableManagedBean<Module, String, ModuleService, ModuleJpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real page to return in detail page
	private String mode;

	// Main serive in managedBean
	@ManagedProperty(value = "#{moduleService}")
	private ModuleService service;
	@ManagedProperty(value = "#{moduleJpaService}")
	private ModuleJpaService jpaService;
	// SystemService
	@ManagedProperty(value = "#{systemService}")
	private SystemService systemService;
	private List<SelectItem> systemList;
	// private Map<String, System> systemMap;

	/**
	 * Constructor
	 */
	public ModuleManagedBean() {

		super();

	}

	/**
	 * Init config
	 */
	@PostConstruct
	public void init() {
		// Set show data in read page
		this.setInitShowListData(true);
		// Init find criteria
		this.initFindCriteriaMap();
		// Set vo wrapper
		this.setVoWrapper(new ModuleVoWrapper());

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
	 * Init create object
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		Module object = new Module();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");

	}

	/**
	 * If you need to process updating data after press create or update button, you
	 * need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Module, java.lang.String> updatingData) {

		// Set drop down list for many-to-one
		if (this.getUpdatingData().getEntity().getSystem() != null) {
			this.getUpdatingData().setSelectSystemId(this.getUpdatingData().getEntity().getSystem().getId().toString());

		}

		this.setMode("Update");

	}

	/**
	 * Init find criteria map, find oper map and find sort map
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
		
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();

		findCriteriaMap.put("name", null);
		findCriteriaMap.put("code", null);
		findCriteriaMap.put("sequence", null);
		findCriteriaMap.put("system.id", null);
		this.setFindCriteriaMap(findCriteriaMap);
		// Set operation
		Map<String, String> findOperMap = new HashMap<String, String>();

		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("sequence", "eq");
		findOperMap.put("system.id", "eq");
		this.setFindOperMap(findOperMap);

		// Set sort
		Map<String, String> findSortMap = new HashMap<String, String>();

		findSortMap.put("name", "ASC");
		findSortMap.put("code", "ASC");
		findSortMap.put("sequence", "ASC");
		findSortMap.put("system.id", "ASC");
		this.setFindSortMap(findSortMap);
	}

	/**
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#getUpdatingData()
	 *
	 */
	@Override
	public ModuleVo getUpdatingData() {
		return (ModuleVo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Module, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public ModuleService getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(ModuleService service) {
		this.service = service;
	}

	public ModuleJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(ModuleJpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * Get drop down list select item in edit page
	 * 
	 * @return 
	 */
	public List<SelectItem> getSystemList() {

		if (this.systemList == null) {
			this.systemList = new ArrayList<SelectItem>();
			// First SelectItem default is SelectHelper.EMPTY_SELECTITEM
			this.systemList.add(SelectHelper.EMPTY_SELECTITEM);
			for (System system : getSystemService().findAll()) {
				SelectItem item = new SelectItem();

				/**
				 * Because entity may not have name property, so you may modify setLabel()
				 *
				 */

				item.setValue(system.getId().toString());
				item.setLabel(system.getName());
				this.systemList.add(item);

			}
		}
		return systemList;
	}


	/**
	 * Return SystemService
	 * 
	 * @return SystemService
	 */
	public SystemService getSystemService() {
		return this.systemService;
	}

	/**
	 * Set SystemService
	 * 
	 * @param SystemService
	 */
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * Setup System
	 */
	private void setupSystem() {
		// Because select id may be null or empty string, so you need to check
		if ((this.getUpdatingData().getSelectSystemId() != null)
				&& (this.getUpdatingData().getSelectSystemId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setSystem(getSystemService().findByPK(this.getUpdatingData().getSelectSystemId()));
		} else {
			this.getUpdatingData().getEntity().setSystem(null);
		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupSystem();

	}

	/**
	 * If entity has many-to-one or many-to-many relation then Code Generator will
	 * make this method for modifying. You can modify it for your need Method. The
	 * main function is in read page fetch all relational date to avoid update page
	 * occur error.
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Module> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
