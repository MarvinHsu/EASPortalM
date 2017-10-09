package com.hsuforum.easportalm.service.impl;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easportalm.dao.CategoryDao;
import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category, java.lang.String, CategoryDao>
	implements CategoryService {

	private static final long serialVersionUID = -8629505666445530146L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(CategoryServiceImpl.class);

	@Autowired
	private CategoryDao dao;

 	/**
	 * 回傳Data Access Object
	 *
	 * @return categoryDao
	 */
	public CategoryDao getDao() {
		return this.dao;
	}

	/**
	 * 設定Data Access Object
	 *
	 * @param baseDao
	 */
	public void setDao(final CategoryDao baseDao) {
		this.dao = baseDao;
	}
 


	
	
	/**
	 * @see com.hsuforum.easportalm.service.CategoryService#findAllFetchRelation()
	 */
	public List<Category> findAllFetchRelation(){
		
		return this.getDao().findAllFetchRelation();
	}



}