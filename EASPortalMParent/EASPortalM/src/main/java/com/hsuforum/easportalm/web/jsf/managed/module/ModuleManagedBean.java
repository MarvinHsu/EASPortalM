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

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Module;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.ModuleService;
import com.hsuforum.easportalm.service.SystemService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.ModuleVo;
import com.hsuforum.easportalm.web.vowrapper.ModuleVoWrapper;

@ManagedBean
@SessionScoped
public class ModuleManagedBean extends TemplatePrimeDataTableManagedBean<Module, java.lang.String, ModuleService> {

	private static final long serialVersionUID = 1L;

	// 新增或修改狀態，新增為Create，修改為Update，用於detail時返回正確路徑用
	private String mode;

	// managedBean的主要使用service
	@ManagedProperty(value = "#{moduleService}")
	private ModuleService service;

	// SystemService
	@ManagedProperty(value = "#{systemService}")
	private SystemService systemService;
	private List<SelectItem> systemList;
	// private Map<String, System> systemMap;

	/**
	 * 建構子
	 */
	public ModuleManagedBean() {

		super();

	}

	/**
	 * 初始設定
	 */
	@PostConstruct
	public void init() {
		// 設定是否一進頁面，即秀出資料
		this.setInitShowListData(true);
		// 初始話查詢條件
		this.initFindCriteriaMap();
		// 設定VoWrapper
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
	 * 初始新增物件
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
	 * 如需要再create or update按鈕按下後，對updating
	 * date進行處理，則需要撰寫此部分。但不管怎樣都需要override此method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Module, java.lang.String> updatingData) {

		// 設定頁面上的下拉式選單(drop Down List for Many-to-one)設定
		if (this.getUpdatingData().getEntity().getSystem() != null) {
			this.getUpdatingData().setSelectSystemId(this.getUpdatingData().getEntity().getSystem().getId().toString());

		}

		this.setMode("Update");

	}

	/**
	 * 在read頁面下，如有查詢輸入時，則續在建構子呼叫此函數來設定findCriteriaMap 如需要可斟酌修改
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
		// 目前只能先用String
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();

		findCriteriaMap.put("name", null);
		findCriteriaMap.put("code", null);
		findCriteriaMap.put("sequence", null);
		findCriteriaMap.put("system.id", null);
		this.setFindCriteriaMap(findCriteriaMap);
		// 設定操作
		Map<String, String> findOperMap = new HashMap<String, String>();

		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("sequence", "eq");
		findOperMap.put("system.id", "eq");
		this.setFindOperMap(findOperMap);

		// 設定排序
		Map<String, String> findSortMap = new HashMap<String, String>();

		findSortMap.put("name", "ASC");
		findSortMap.put("code", "ASC");
		findSortMap.put("sequence", "ASC");
		findSortMap.put("system.id", "ASC");
		this.setFindSortMap(findSortMap);
	}

	/**
	 * getUpdatingData 和 setUpdatingData 其實可以不需要, 因為 super class 已經有了 存在的目的,
	 * 只是為了讓 IDE 知道它的確切的type為何, 讓 jsf 的頁面比較好拖拉
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#getUpdatingData()
	 *
	 */
	@Override
	public ModuleVo getUpdatingData() {
		return (ModuleVo) super.getUpdatingData();
	}

	/**
	 * 設定UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Module, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * 取得Service物件
	 */
	@Override
	public ModuleService getService() {

		return this.service;
	}

	/**
	 * 設定該ManagedBean的主要service
	 * 
	 * @param service
	 */
	@Override
	public void setService(ModuleService service) {
		this.service = service;
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
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupSystem();

	}

	/**
	 * 假如Entity(Business Object)，有Many-to-One或Many-to-Many的狀況， 則Code
	 * Generator會自動Override以供修改使用，可自行改成需要的Method，
	 * 主要作用是在read頁面就Fetch所有相關關聯的資料，免得再update頁面出現問題
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Module> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
