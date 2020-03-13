package com.hsuforum.easportalm.web.jsf.managed.function;

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
import com.hsuforum.common.web.jsf.utils.JSFUtils;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.entity.Module;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.FunctionJpaService;
import com.hsuforum.easportalm.service.FunctionService;
import com.hsuforum.easportalm.service.ModuleService;
import com.hsuforum.easportalm.service.SystemService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.FunctionVo;
import com.hsuforum.easportalm.web.vowrapper.FunctionVoWrapper;

@ManagedBean
@SessionScoped
public class FunctionManagedBean
		extends TemplatePrimeJpaDataTableManagedBean<Function, String, FunctionService, FunctionJpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real page to return in detail page
	private String mode;

	// Main serive in managedBean
	@ManagedProperty(value = "#{functionService}")
	private FunctionService service;
	@ManagedProperty(value = "#{functionJpaService}")
	private FunctionJpaService jpaService;

	// SystemService
	@ManagedProperty(value = "#{systemService}")
	private SystemService systemService;
	private List<SelectItem> systemList;
	// private Map<String, System> systemMap;

	// ModuleService
	@ManagedProperty(value = "#{moduleService}")
	private ModuleService moduleService;
	private List<SelectItem> moduleList;
	// private Map<String, Module> moduleMap;

	/**
	 * Constructor
	 */
	public FunctionManagedBean() {

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
		this.setVoWrapper(new FunctionVoWrapper());

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
	 * Init create object
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		Function object = new Function();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");
		// 因有master detail關係，故要先移除子層在session的快取
		JSFUtils.getFacesContext().getExternalContext().getSessionMap().remove("function_FunctionItemManagedBean");
		// 因有master detail關係，故要先移除子層在session的快取
		JSFUtils.getFacesContext().getExternalContext().getSessionMap().remove("function_GroupFunctionManagedBean");
	}

	/**
	 * If you need to process updating data after press create or update button, you need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Function, java.lang.String> updatingData) {

		// 設定頁面上的下拉式選單(drop Down List for Many-to-one)設定
		if (this.getUpdatingData().getEntity().getSystem() != null) {
			this.getUpdatingData().setSelectSystemId(this.getUpdatingData().getEntity().getSystem().getId().toString());

		}

		// 設定頁面上的下拉式選單(drop Down List for Many-to-one)設定
		if (this.getUpdatingData().getEntity().getModule() != null) {
			this.getUpdatingData().setSelectModuleId(this.getUpdatingData().getEntity().getModule().getId().toString());

		}

		this.setMode("Update");
		// 因有master detail關係，故要先移除子層在session的快取
		JSFUtils.getFacesContext().getExternalContext().getSessionMap().remove("function_FunctionItemManagedBean");
		// 因有master detail關係，故要先移除子層在session的快取
		JSFUtils.getFacesContext().getExternalContext().getSessionMap().remove("function_GroupFunctionManagedBean");
	}

	/**
	 * Init find criteria map, find oper map and find sort map
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
		
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();

		findCriteriaMap.put("name", null);

		findCriteriaMap.put("createDate", null);

		findCriteriaMap.put("updateDate", null);

		findCriteriaMap.put("sequence", null);

		findCriteriaMap.put("code", null);

		findCriteriaMap.put("outcome", null);

		this.setFindCriteriaMap(findCriteriaMap);
		// Set operation
		Map<String, String> findOperMap = new HashMap<String, String>();

		findOperMap.put("name", "eq");
		findOperMap.put("createDate", "eq");
		findOperMap.put("updateDate", "eq");
		findOperMap.put("sequence", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("outcome", "eq");

		this.setFindOperMap(findOperMap);

		// Set sort
		Map<String, String> findSortMap = new HashMap<String, String>();

		findSortMap.put("name", "DESC");
		findSortMap.put("createDate", "DESC");
		findSortMap.put("updateDate", "DESC");
		findSortMap.put("sequence", "DESC");
		findSortMap.put("code", "DESC");
		findSortMap.put("outcome", "DESC");

		this.setFindSortMap(findSortMap);
	}

	/**


	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getUpdatingData()
	 *
	 */
	@Override
	public FunctionVo getUpdatingData() {
		return (FunctionVo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Function, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public FunctionService getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(FunctionService service) {
		this.service = service;
	}

	public FunctionJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(FunctionJpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * 取得頁面中所使用的，下拉式選單SelectItem內容
	 * 
	 * @return 下拉式選單SelectItem內容
	 */
	public List<SelectItem> getSystemList() {

		if (this.systemList == null) {
			this.systemList = new ArrayList<SelectItem>();
			// 第一個SelectItem為預設為SelectHelper.EMPTY_SELECTITEM
			this.systemList.add(SelectHelper.EMPTY_SELECTITEM);
			for (System system : getSystemService().findAll()) {
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

	/**
	 * 回傳SystemMap
	 * 
	 * @return Map<String, System>
	 *//**
		 * public Map<String, System> getSystemMap() { if(this.systemMap==null){
		 * this.systemMap = new HashMap<String, System>();
		 * 
		 * for (System system : getSystemService().findAll()) {
		 * this.systemMap.put(system.getId().toString(), system); } } return
		 * this.systemMap; }
		 */

	/**
	 * 回傳SystemService
	 * 
	 * @return SystemService
	 */
	public SystemService getSystemService() {
		return this.systemService;
	}

	/**
	 * 設定SystemService
	 * 
	 * @param SystemService
	 */
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 設定System
	 */
	private void setupSystem() {
		// 所選取的id可能會為null或空字串("")，所以皆需檢核
		if ((this.getUpdatingData().getSelectSystemId() != null)
				&& (this.getUpdatingData().getSelectSystemId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setSystem(getSystemService().findByPK(this.getUpdatingData().getSelectSystemId()));
		} else {
			this.getUpdatingData().getEntity().setSystem(null);
		}
	}

	/**
	 * 取得頁面中所使用的，下拉式選單SelectItem內容
	 * 
	 * @return 下拉式選單SelectItem內容
	 */
	public List<SelectItem> getModuleList() {

		if (this.moduleList == null) {
			this.moduleList = new ArrayList<SelectItem>();
			// 第一個SelectItem為預設為SelectHelper.EMPTY_SELECTITEM
			this.moduleList.add(SelectHelper.EMPTY_SELECTITEM);
			for (Module module : getModuleService().findAll()) {
				SelectItem item = new SelectItem();

				/**
				 * Because entity may not have name property, so you may modify setLabel()
				 *
				 */

				item.setValue(module.getId().toString());
				item.setLabel(module.getName());
				this.moduleList.add(item);

			}
		}
		return moduleList;
	}



	/**
	 * Return ModuleService
	 * 
	 * @return ModuleService
	 */
	public ModuleService getModuleService() {
		return this.moduleService;
	}

	/**
	 * Set ModuleService
	 * 
	 * @param ModuleService
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * Setup Module
	 */
	private void setupModule() {
		// 所選取的id可能會為null或空字串("")，所以皆需檢核
		if ((this.getUpdatingData().getSelectModuleId() != null)
				&& (this.getUpdatingData().getSelectModuleId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setModule(getModuleService().findByPK(this.getUpdatingData().getSelectModuleId()));
		} else {
			this.getUpdatingData().getEntity().setModule(null);
		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupSystem();
		this.setupModule();

	}

	/**
	 * If entity has many-to-one or many-to-many relation then Code Generator will
	 * make this method for modifying. You can modify it for your need Method. The
	 * main function is in read page fetch all relational date to avoid update page
	 * occur error.
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Function> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
