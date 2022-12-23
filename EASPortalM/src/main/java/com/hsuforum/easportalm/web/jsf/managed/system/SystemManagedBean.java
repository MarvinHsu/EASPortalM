package com.hsuforum.easportalm.web.jsf.managed.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.CategoryService;
import com.hsuforum.easportalm.service.SystemJpaService;
import com.hsuforum.easportalm.service.SystemService;
import com.hsuforum.easportalm.web.util.SelectHelper;
import com.hsuforum.easportalm.web.vo.SystemVo;
import com.hsuforum.easportalm.web.vowrapper.SystemVoWrapper;

import jakarta.annotation.PostConstruct;
import jakarta.faces.model.SelectItem;

@Component
@SessionScope
public class SystemManagedBean extends TemplatePrimeDataTableManagedBean<System, java.lang.String, SystemService, SystemJpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real page to return in detail page
	private String mode;

	// Main serive in managedBean
	@Autowired
	private SystemService service;
	@Autowired
	private SystemJpaService jpaService;
	// CategoryService
	@Autowired
	private CategoryService categoryService;
	private List<SelectItem> categoryList;
	// private Map<String, Category> categoryMap;

	/**
	 * Constructor
	 */
	public SystemManagedBean() {

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
		this.setVoWrapper(new SystemVoWrapper());

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
	 * Init create object
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		System object = new System();
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
	protected void initUpdatingData(ValueObject<System, java.lang.String> updatingData) {

		// Set drop down list for many-to-one
		if (this.getUpdatingData().getEntity().getCategory() != null) {
			this.getUpdatingData()
					.setSelectCategoryId(this.getUpdatingData().getEntity().getCategory().getId().toString());

		}

		this.setMode("Update");

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
		findCriteriaMap.put("code", null);
		findCriteriaMap.put("sequence", null);
		findCriteriaMap.put("url", null);

		this.setFindCriteriaMap(findCriteriaMap);
		// Set operation
		Map<String, String> findOperMap = new HashMap<String, String>();

		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("sequence", "eq");
		findOperMap.put("url", "eq");

		this.setFindOperMap(findOperMap);

		// Set sort
		Map<String, String> findSortMap = new HashMap<String, String>();

		findSortMap.put("name", "DESC");
		findSortMap.put("code", "DESC");
		findSortMap.put("sequence", "DESC");
		findSortMap.put("url", "DESC");

		this.setFindSortMap(findSortMap);
	}

	/**
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getUpdatingData()
	 *
	 */
	@Override
	public SystemVo getUpdatingData() {
		return (SystemVo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<System, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public SystemService getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(SystemService service) {
		this.service = service;
	}

	public SystemJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(SystemJpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * Get drop down list select item in edit page
	 * 
	 * @return 
	 */
	public List<SelectItem> getCategoryList() {

		if (this.categoryList == null) {
			this.categoryList = new ArrayList<SelectItem>();
			// First SelectItem default is SelectHelper.EMPTY_SELECTITEM
			this.categoryList.add(SelectHelper.EMPTY_SELECTITEM);
			for (Category category : getCategoryService().findAll()) {
				SelectItem item = new SelectItem();

				/**
				 * Because entity may not have name property, so you may modify setLabel()
				 *
				 */

				item.setValue(category.getId().toString());
				item.setLabel(category.getName());
				this.categoryList.add(item);

			}
		}
		return categoryList;
	}



	/**
	 * 回傳CategoryService
	 * 
	 * @return CategoryService
	 */
	public CategoryService getCategoryService() {
		return this.categoryService;
	}

	/**
	 * 設定CategoryService
	 * 
	 * @param CategoryService
	 */
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 設定Category
	 */
	private void setupCategory() {
		// Because select id may be null or empty string, so you need to check
		if ((this.getUpdatingData().getSelectCategoryId() != null)
				&& (this.getUpdatingData().getSelectCategoryId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setCategory(getCategoryService().findByPK(this.getUpdatingData().getSelectCategoryId()));
		} else {
			this.getUpdatingData().getEntity().setCategory(null);
		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupCategory();

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
	protected List<System> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
