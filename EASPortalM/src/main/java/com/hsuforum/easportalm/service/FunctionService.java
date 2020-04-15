package com.hsuforum.easportalm.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easportalm.entity.Function;

public interface FunctionService extends BaseService<Function, java.lang.String> {

	
	List<Function> findAllFetchRelation();
	/**
	 * find data by system id
	 * @param systemId
	 * @return
	 */
	List<Function> findBySystemIdFetchRelation(String systemId);
}
