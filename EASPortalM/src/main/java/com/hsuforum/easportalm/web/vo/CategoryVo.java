package com.hsuforum.easportalm.web.vo;


import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easportalm.entity.Category;


/**
 * Category's Value Object Implement
 * 
 */
public class CategoryVo extends ValueObjectImpl<Category, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		

	/**
	 * Constructor
	 * 
	 */
	public CategoryVo() {
		super(new Category());
	}

	/**
	 * Constructor
	 * @param bo Business Object
	 */
	public CategoryVo (Category bo) {
		super(bo);
	}


	



}
