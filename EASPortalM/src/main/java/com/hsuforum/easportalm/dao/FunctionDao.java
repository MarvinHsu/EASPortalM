package com.hsuforum.easportalm.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.Function;

public interface FunctionDao extends BaseDao<Function, java.lang.String> {

	
	List<Function> findAllFetchRelation();
	/**
	 * find data by system id
	 * @param systemId
	 * @return
	 */
	List<Function> findBySystemIdFetchRelation(String systemId);
}
