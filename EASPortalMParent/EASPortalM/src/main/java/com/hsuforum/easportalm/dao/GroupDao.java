package com.hsuforum.easportalm.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.Group;

/**
 *
 * Group Data Access Object Interface
 *
 */
public interface GroupDao extends BaseDao<Group, String> {

	
	List<Group> findAllFetchRelation();

	
	Group findByPKFetchFunctions(String pk);

	
	Group findByPKFetchUsers(String pk);
}
