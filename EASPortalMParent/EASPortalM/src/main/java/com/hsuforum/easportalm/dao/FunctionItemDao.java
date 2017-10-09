package com.hsuforum.easportalm.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.FunctionItem;

public interface FunctionItemDao extends BaseDao<FunctionItem, java.lang.String> {

	
	List<FunctionItem> findAllFetchRelation();

}
