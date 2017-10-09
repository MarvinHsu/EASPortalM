package com.hsuforum.easportalm.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.Module;

public interface ModuleDao extends BaseDao<Module, java.lang.String> {

	
	List<Module> findAllFetchRelation();

}
