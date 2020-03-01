package com.hsuforum.easportalm.web.jsf.managed.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.service.CategoryJpaService;
import com.hsuforum.easportalm.service.CategoryService;
import com.hsuforum.easportalm.web.vo.CategoryVo;
import com.hsuforum.easportalm.web.vowrapper.CategoryVoWrapper;

@ManagedBean
@SessionScoped
public class CategoryManagedBean
		extends TemplatePrimeJpaDataTableManagedBean<Category, java.lang.String, CategoryService, CategoryJpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real page to return in detail page
	private String mode;

	// Main serive in managedBean
	@ManagedProperty(value = "#{categoryService}")
	private CategoryService service;

	@ManagedProperty(value = "#{categoryJpaService}")
	private CategoryJpaService jpaService;

	/**
	 * Constructor
	 */
	public CategoryManagedBean() {

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
		this.setVoWrapper(new CategoryVoWrapper());

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
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		Category object = new Category();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");

	}

	/**
	 * If you need to process updating data after press create or update button, you need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Category, java.lang.String> updatingData) {

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

		this.setFindCriteriaMap(findCriteriaMap);
		// Set operation
		Map<String, String> findOperMap = new HashMap<String, String>();

		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("sequence", "eq");

		this.setFindOperMap(findOperMap);

		// Set sort
		Map<String, String> findSortMap = new HashMap<String, String>();

		findSortMap.put("name", "DESC");
		findSortMap.put("code", "DESC");
		findSortMap.put("sequence", "DESC");

		this.setFindSortMap(findSortMap);
	}

	/**


	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#getUpdatingData()
	 *
	 */
	@Override
	public CategoryVo getUpdatingData() {
		return (CategoryVo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Category, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public CategoryService getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(CategoryService service) {
		this.service = service;
	}

	public CategoryJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(CategoryJpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {

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
	protected List<Category> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
