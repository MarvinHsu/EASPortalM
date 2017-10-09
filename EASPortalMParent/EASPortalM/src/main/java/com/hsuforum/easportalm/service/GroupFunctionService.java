package com.hsuforum.easportalm.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easportalm.entity.GroupFunction;

public interface GroupFunctionService extends BaseService<GroupFunction, com.hsuforum.easportalm.entity.GroupFunctionPK> {

	
	List<GroupFunction> findAllFetchRelation();

}
