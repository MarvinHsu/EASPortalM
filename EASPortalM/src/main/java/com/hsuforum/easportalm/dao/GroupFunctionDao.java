package com.hsuforum.easportalm.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.GroupFunction;

public interface GroupFunctionDao extends BaseDao<GroupFunction, String> {

	
	List<GroupFunction> findAllFetchRelation();

}
