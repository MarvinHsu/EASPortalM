package com.hsuforum.easportalm.web.vo;


import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.System;


/**
 * System's Value Object Implement
 * 
 */
public class SystemVo extends ValueObjectImpl<System, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		
	private String selectCategoryId;

	/**
	 * Constructor
	 * 
	 */
	public SystemVo() {
		super(new System());
	}

	/**
	 * Constructor
	 * @param bo Business Object
	 */
	public SystemVo (System bo) {
		super(bo);
	}


	

	/**
	 * Get selected System's id
	 * 
	 * @return selectCategoryId
	 */
	public String getSelectCategoryId() {
		if (this.selectCategoryId == null && this.getEntity().getCategory() != null) {
			this.selectCategoryId = this.getEntity().getCategory().getId().toString();
		}
		return this.selectCategoryId;
		
	}

	/**
	 * Set selected System's id
	 * @param selectSystemId
	 */
	public void setSelectCategoryId(String selectCategoryId) {
		
		this.selectCategoryId = selectCategoryId;
	}
	



}
