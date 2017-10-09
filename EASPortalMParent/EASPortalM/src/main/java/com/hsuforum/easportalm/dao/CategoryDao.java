package com.hsuforum.easportalm.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.Category;

public interface CategoryDao extends BaseDao<Category, java.lang.String> {


	/**
	 * 假如Entity(Business Object)，有Many-to-One或Many-to-Many的狀況，
	 * 則Code Generator會產生此Interface以供修改使用，可自行改成需要的Method，
	 * 主要作用是在read頁面就Fetch所有相關關聯的資料，免得再update頁面出現問題
	 * @return List<Category>
	 */
	List<Category> findAllFetchRelation();

}
