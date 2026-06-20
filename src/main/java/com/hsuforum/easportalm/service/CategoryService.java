package com.hsuforum.easportalm.service;


import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easportalm.entity.Category;

public interface CategoryService extends BaseService<Category, java.lang.String> {



	/**
	 * If entity has many-to-one or many-to-many relation
	 * then Code Generator will make this interface for modifying.
	 * You can modify it for your need Method.
	 * The main function is in read page fetch all 
	 * relational date to avoid update page occur error.
	 * @return List<${classUnit.boClassName}>
	 */
	List<Category> findAllFetchRelation();



}
